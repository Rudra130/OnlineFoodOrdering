package com.food.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.OnlineFoodOrdering.Model.IngredientsItem;
import java.util.List;


public interface IngredientItemRepository extends JpaRepository<IngredientsItem,Long> {

    List<IngredientsItem> findByRestaurantId(Long id);
    
}
