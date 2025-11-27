package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.infrastucture.BookRecommendorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final BookRecommendorRepository repository;
    public UserDetailsServiceImpl(BookRecommendorRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (repository.getUserByUsername(username).isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        UserModel user = repository.getUserByUsername(username).get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.emptyList()
        );
    }

}
