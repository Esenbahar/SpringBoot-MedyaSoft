package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.models.Book;

@Component
public class BookRepository {
	
		HashMap<String, Book> books = new HashMap<>();


		public void addBook(Book book){ books.put(book.getid(), book); }

		public Book getBook(String id){
				return books.get(id);
			}

	    public List<Book> getAllBooks(){
	        return new ArrayList<>(books.values());
	    }



}
