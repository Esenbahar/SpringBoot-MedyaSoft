package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.demo.models.Category;

public class CategoryRepository {

    HashMap<String, Category> categories = new HashMap<>();

    public void addCategory(Category category){ categories.put(category.getCategoryid(), category); }

    public Category getCategory(String id){
        return categories.get(id);
    }

    public List<Category> getAllCategories(){
        return new ArrayList<>(categories.values());
    }



   
    

}
