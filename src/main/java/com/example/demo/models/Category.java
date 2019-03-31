package com.example.demo.models;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.ResourceSupport;

public class Category extends ResourceSupport{
	
	@NotBlank
    private String categoryid;
	
	 @NotBlank
	    private String name;

	 

		public Category() {
	    }

	    public Category(String id,String name) {
	    	this.categoryid = id;
	        this.name = name;
	        

	    }


	    public String getCategoryid() {
			return categoryid;
		}

		public void setCategoryid(String categoryid) {
			this.categoryid = categoryid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}


		@Override
	    public String toString() {
	        return "Category{" + "name='" + name + '\'' +  '}';
	    }



	

}
