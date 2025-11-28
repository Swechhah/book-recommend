package com.platform.recommendor.app.presentation;

import com.platform.recommendor.app.application.dto.user.LoginResponse;
import com.platform.recommendor.app.application.usecases.BookDataCSVImporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/import")
public class BookImportController {
    private final BookDataCSVImporter importer;
    public BookImportController(BookDataCSVImporter importer) {
        this.importer = importer;
    }
    @PostMapping("/books")
    public LoginResponse uploadBooks(@RequestParam("file") MultipartFile file) {
        try {
            importer.importBooks(file);
            return new LoginResponse("Books imported successfully.");
        } catch (Exception e) {
            return new LoginResponse("Books could not be imported.");
        }
    }
}
