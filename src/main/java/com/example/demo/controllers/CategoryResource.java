package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.models.Category;

public interface CategoryResource {

	ResponseEntity<Category> getCategoryById(String id);
    ResponseEntity<List<Category>> getAllCategories();
	ResponseEntity<List<Category>> getCategoriesByName(String name);

}
