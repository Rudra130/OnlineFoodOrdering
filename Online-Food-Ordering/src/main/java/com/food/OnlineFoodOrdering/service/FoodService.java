package com.food.OnlineFoodOrdering.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.food.OnlineFoodOrdering.Model.Category;
import com.food.OnlineFoodOrdering.Model.Food;
import com.food.OnlineFoodOrdering.Model.Restaurant;
import com.food.OnlineFoodOrdering.request.CreateFoodRequest;



@Service
public interface FoodService {

    public Food createFood(CreateFoodRequest req , 
    Category category , Restaurant restaurant);

    void deleteFood(Long FoodId)throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId,boolean 
                                        isVegetarain,
                                        boolean isNonveg,
                                        boolean isSeasonal , 
                                        String foodCategory);

    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId)throws Exception;

    public Food updateAvailibilityStatus(Long foodId)throws Exception;





                                        






}
