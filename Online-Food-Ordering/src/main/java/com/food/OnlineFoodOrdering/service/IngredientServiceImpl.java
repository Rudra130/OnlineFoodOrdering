package com.food.OnlineFoodOrdering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.OnlineFoodOrdering.Model.IngredientCategory;
import com.food.OnlineFoodOrdering.Model.IngredientsItem;
import com.food.OnlineFoodOrdering.Model.Restaurant;
import com.food.OnlineFoodOrdering.repository.IngredientCategoryRepository;
import com.food.OnlineFoodOrdering.repository.IngredientItemRepository;



@Service
public class IngredientServiceImpl implements IngredientsService {


    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository  ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        
        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);
        

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("ingredient not found");
        }


        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
      

        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
            throws Exception {
                
                Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
                IngredientCategory category = findIngredientCategoryById(categoryId);
        
                IngredientsItem item = new IngredientsItem();
                item.setName(ingredientName);
                item.setRestaurant(restaurant);
                item.setCategory(null);
                IngredientsItem ingredient = ingredientItemRepository.save(item);

                category.getIngredients().add(ingredient);


                return ingredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long RestaurantId) {
       
        return ingredientItemRepository.findByRestaurantId(RestaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        
        Optional<IngredientsItem> optionalIngredientItem = ingredientItemRepository.findById(id);

        if(optionalIngredientItem.isEmpty()){
            throw new Exception("ingredient not found");
        }

        IngredientsItem ingredientsItem = optionalIngredientItem.get();
        ingredientsItem.setInStoke(!ingredientsItem.isInStoke());

        return ingredientItemRepository.save(ingredientsItem);
    }  
}
