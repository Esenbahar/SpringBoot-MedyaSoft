package com.example.demo;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import com.example.demo.models.Book;
import com.example.demo.models.Category;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;

@ComponentScan
@EnableAutoConfiguration

public class DemoApplication {

	@Autowired
    private BookRepository booksRepo;
	
	@Autowired
    private CategoryRepository categoryRepo;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@PostConstruct
	public void initApplication() throws IOException {

		    booksRepo.addBook(new Book("1","Kumsalda ","edebiyat"));
	        booksRepo.addBook(new Book("2","1984","edebiyat"));
			booksRepo.addBook(new Book("3","Anısız","edebiyat"));

			booksRepo.addBook(new Book("4","Yatırım","ekonomi"));

			booksRepo.addBook(new Book("5","Orhun","kültür"));

			booksRepo.addBook(new Book("6","Python","bilgisayar"));
			booksRepo.addBook(new Book("7","Java","bilgisayar"));
			booksRepo.addBook(new Book("8","Ardunio","bilgisayar"));


			categoryRepo.addCategory(new Category("1","edebiyat"));
	        categoryRepo.addCategory(new Category("2","ekonomi"));
	        categoryRepo.addCategory(new Category("3","kültür"));
			categoryRepo.addCategory(new Category("4","bilgisayar"));





	}

}
