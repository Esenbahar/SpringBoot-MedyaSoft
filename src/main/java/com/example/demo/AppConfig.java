package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.BookRepository;


@Configuration
public class AppConfig {

    @Bean
    public BookRepository booksRepo() {
        return new BookRepository();
    }

    @Bean
    public CategoryRepository categoryRepo() {
        return new CategoryRepository();
    }
}
