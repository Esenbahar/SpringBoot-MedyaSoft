package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.example.demo.models.Category;
import com.example.demo.repository.CategoryRepository;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(value = "Category", description = "Operations about Category")

public class CategoryResourceImpl implements CategoryResource {

	 @Autowired
	    private CategoryRepository categoryRepo;
	 
	


	
	 @Override
	    @RequestMapping(value="/category/{id}",
	            method = RequestMethod.GET,
	            produces = APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    @ResponseBody
	    @ApiOperation(value = "Get a category using its ID")

	public ResponseEntity<Category> getCategoryById( @PathVariable String id) {
		 Category result = categoryRepo.getCategory(id);
	        HttpStatus httpStatus;
	        if(result != null) {
	            result.removeLinks();
	            result.add(linkTo(methodOn(CategoryResourceImpl.class).getCategoryById(id)).withSelfRel());
	            httpStatus = HttpStatus.OK;
	        }else{
	            httpStatus = HttpStatus.NOT_FOUND;
	        }
	        
	        return new ResponseEntity<Category>(result, httpStatus);

	}



	 @RequestMapping(value="/categories",
	            method = RequestMethod.GET,
	            produces = APPLICATION_JSON_VALUE)
	    @ResponseBody
	    @Override
	    @ApiOperation(value = "List all categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		 List<Category> category = categoryRepo.getAllCategories();
		 category.forEach(categories -> {
			 
			 categories.add(linkTo(methodOn(CategoryResourceImpl.class).getCategoryById(categories.getCategoryid())).withSelfRel());
	        });
	        return new ResponseEntity<>(category, HttpStatus.OK);

	}










	 @Override
	    @RequestMapping(value="search/category/{name}",
	            method = RequestMethod.GET,
	            produces = APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    @ResponseBody
	    @ApiOperation(value = "Get a category using its ID")

	public ResponseEntity<List<Category>> getCategoriesByName(@PathVariable String name) {
		 Category categorysearch =  new Category();
	        List<Category> search = new ArrayList<Category>();
	        HttpStatus httpStatus;
	        
	        List<Category> categories = categoryRepo.getAllCategories();
     	
	        categories.forEach(category -> {
        	 if(category.getName().equals(name))
     		{
		 
        		 categorysearch.setName(category.getName());
        		 categorysearch.setCategoryid(category.getCategoryid());
     			
     			search.add(categorysearch);
     		}
        	 
	 
        });
    	 
        
         
    
	        
	        
	        if(search.size() > 0){
	        	    httpStatus = HttpStatus.OK;
	        	    categorysearch.add(linkTo(methodOn(CategoryResourceImpl.class).getCategoryById(name)).withSelfRel());
	        	    categorysearch.add(linkTo(methodOn(CategoryResourceImpl.class).getAllCategories()).withRel("Categories"));
	        } else {
	            httpStatus = HttpStatus.NOT_FOUND;
	        }

	        return new ResponseEntity<List<Category>>(search, httpStatus);

	    }


}
