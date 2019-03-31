package com.example.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;

@RestController
@RequestMapping("/api")
@Api(value = "Book", description = "Operations about Books")

public class BooksResourceImpl implements BooksResource {

	@Autowired
    private BookRepository booksRepo;

	@Override
    @RequestMapping(value="/book/{id}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get a book using its ID")

	
	
	public ResponseEntity<Book> getBookById(  @PathVariable String id) {
		Book result = booksRepo.getBook(id);
		HttpStatus httpStatus;
        if(result != null) {
            result.removeLinks();
            result.add(linkTo(methodOn(BooksResourceImpl.class).getBookById(id)).withSelfRel());
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
        }

		return new ResponseEntity<Book>(result, httpStatus);
	}


	    @RequestMapping(value="/books",
	            method = RequestMethod.GET,
	            produces = APPLICATION_JSON_VALUE)
	    @ResponseBody
	    @Override
	    @ApiOperation(value = "List all books")

	
	public ResponseEntity<List<Book>> getAllBooks() {
		 List<Book> books = booksRepo.getAllBooks();
	        books.forEach(book -> {
	            book.removeLinks();
	            book.add(linkTo(methodOn(BooksResourceImpl.class).getBookById(book.getid())).withSelfRel());
	        });
	        return new ResponseEntity<>(books, HttpStatus.OK);
	 }




	        @Override
	        @RequestMapping(value="search/book/{name}",
	                method = RequestMethod.GET,
	                produces = APPLICATION_JSON_VALUE)
	        @ResponseStatus(HttpStatus.OK)
	        @ResponseBody
	        @ApiOperation(value = "Get a category using its id")
	        public ResponseEntity<List<Book>> getBooksByTitleCategory (@PathVariable String name) {
	            Book booksearch =  new Book();
	            List<Book> search = new ArrayList<Book>();
	            HttpStatus httpStatus;
	            
	            List<Book> books = booksRepo.getAllBooks();
	        	
	            books.forEach(book -> {
	           	 if(book.getName().equals(name) || book.getCategory().equals(name))
	        		{
	    		 
	        			booksearch.setName(book.getName());
	        			booksearch.setCategory(book.getCategory());
	        			booksearch.setId(book.getid());
	        			
	        			search.add(book);
	        		}
	           	 
	    	 
	           });
	       	 

	            if(search.size() > 0){
	            	    httpStatus = HttpStatus.OK;
	                booksearch.add(ControllerLinkBuilder.linkTo(methodOn(CategoryResourceImpl.class).getCategoryById(name)).withSelfRel());
	                booksearch.add(linkTo(methodOn(CategoryResourceImpl.class).getAllCategories()).withRel("Categories"));
	            } else {
	                httpStatus = HttpStatus.NOT_FOUND;
	            }

	            return new ResponseEntity<List<Book>>(search, httpStatus);
        
	}
	
	

}
