package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.domain.model.BookModel;
import com.platform.recommendor.app.infrastucture.ports.BookRecommendorRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class BookDataCSVImporter {

    private final BookRecommendorRepository repository;
    private final Executor csvExecutor;

    public BookDataCSVImporter(BookRecommendorRepository repository,
                               @Qualifier("csvExecutor") Executor csvExecutor) {
        this.repository = repository;
        this.csvExecutor = csvExecutor;
    }

    private static final int CHUNK_SIZE = 2000; // process 2000 rows per thread

    @Async("csvExecutor")
    public CompletableFuture<Void> processChunk(List<CSVRecord> chunk) {

        List<BookModel> books = new ArrayList<>(chunk.size());

        for (CSVRecord record : chunk) {
            BookModel book = new BookModel();
            book.setIsbn(record.get("ISBN"));
            book.setTitle(record.get("Book-Title"));
            book.setAuthor(record.get("Book-Author"));
            book.setPublisher(record.get("Publisher"));
            book.setImage_url(record.get("Image-URL-L"));

            books.add(book);
        }

        repository.saveAllBooks(books);
        return CompletableFuture.completedFuture(null);
    }


    public void importBooks(MultipartFile file) throws IOException {

        Reader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build();

        Iterable<CSVRecord> records = format.parse(reader);

        List<CSVRecord> chunk = new ArrayList<>(CHUNK_SIZE);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (CSVRecord record : records) {

            chunk.add(record);

            if (chunk.size() == CHUNK_SIZE) {
                // Submit chunk to async executor
                futures.add(processChunk(new ArrayList<>(chunk)));
                chunk.clear();
            }
        }

        // leftover chunk
        if (!chunk.isEmpty()) {
            futures.add(processChunk(chunk));
        }

        // Wait for all async threads to finish
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
}
