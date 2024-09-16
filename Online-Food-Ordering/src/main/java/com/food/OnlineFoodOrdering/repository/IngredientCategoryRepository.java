package com.food.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.food.OnlineFoodOrdering.Model.IngredientCategory;
import java.util.List;


public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory ,Long>{


     
    List<IngredientCategory> findByRestaurantId(Long id);
    
}
