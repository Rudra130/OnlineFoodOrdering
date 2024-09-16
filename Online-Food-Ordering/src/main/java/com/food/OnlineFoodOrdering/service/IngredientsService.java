package com.food.OnlineFoodOrdering.service;

import java.util.List;



import com.food.OnlineFoodOrdering.Model.IngredientCategory;
import com.food.OnlineFoodOrdering.Model.IngredientsItem;


public interface IngredientsService {

    public IngredientCategory createIngredientCategory(String name ,Long restaurantId)throws Exception;
    public IngredientCategory findIngredientCategoryById(Long id)throws Exception;
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName,
                Long category) throws Exception;
    public List<IngredientsItem> findRestaurantIngredients(Long RestaurantId);
    public IngredientsItem updateStock(Long id) throws Exception;
    


}
