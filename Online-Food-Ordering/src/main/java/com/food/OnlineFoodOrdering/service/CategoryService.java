package com.food.OnlineFoodOrdering.service;

import java.util.List;

import com.food.OnlineFoodOrdering.Model.Category;

public interface CategoryService {
    public Category createCategory(String name,long userId) throws Exception;

    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;
    
    public Category findCategoryById(long id) throws Exception;

        
    }
    

