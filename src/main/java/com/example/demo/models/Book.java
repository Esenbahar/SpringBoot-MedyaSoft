package com.example.demo.models;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.ResourceSupport;


public class Book extends ResourceSupport{

    private String id;

    @NotBlank
    private String name;

    private String category;


    
    public Book() {
    }

	public Book(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category =category;
       
    }

    public String getid() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
   

    @Override
    public String toString() {
        return "Book{" + "İd='" + id + '\'' + ", name='" + name + '\'' + ", category=' " + category + '\'' + '}';
    }
}

