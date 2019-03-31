package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.models.Book;

public interface BooksResource {
	
	ResponseEntity<Book> getBookById(String id);
    ResponseEntity<List<Book>> getAllBooks();
    ResponseEntity<List<Book>> getBooksByTitleCategory(String name);




}
