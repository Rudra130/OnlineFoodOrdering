package com.food.OnlineFoodOrdering.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.OnlineFoodOrdering.Model.Category;
import com.food.OnlineFoodOrdering.Model.Food;
import com.food.OnlineFoodOrdering.Model.Restaurant;
import com.food.OnlineFoodOrdering.repository.FoodRepository;
import com.food.OnlineFoodOrdering.request.CreateFoodRequest;

@Service
public class FoodServiceImpl implements FoodService{


    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req,
                           Category category,
                           Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        
        food.setSeasonal(req.isSeasional());
        food.setVegetarian(req.isVegetarin());

        Food savedFood=   foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long FoodId) throws Exception {

        Food food = findFoodById(FoodId);
        food.setRestaurant(null);
        foodRepository.save(food);
      
    } 

    @Override
    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVegetarain, 
                                        boolean isNonveg, 
                                        boolean isSeasonal,
                                        String foodCategory) {
         List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
         if(foodCategory!=null && !foodCategory.equals("")){ 
            foods = filterByCategory(foods,foodCategory);
           
         }
         if(isVegetarain){
            foods = filterByVegetarian(foods,isVegetarain);

         }
         if(isNonveg){
            foods = filterBySeasonal(foods,isNonveg);

         }
         if(isSeasonal){
            foods = filterByNonVeg(foods,isSeasonal);

         }
         

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
     
       return foods.stream().
       filter(food -> {
        if(food.getFoodCategory()!=null){
            return food.getFoodCategory().getName().equals(foodCategory);
        }
        return false;
        }
       ).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food->food.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasional) {
      return foods.stream().filter(food ->food.isSeasonal()==isSeasional).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarain) {
        return foods.stream().filter(food->food.isVegetarian()==isVegetarain).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
         List<Food> list = foodRepository.searchFood(keyword);
        
        return list;
     }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
       Optional<Food> optionalFood = foodRepository.findById(foodId);
       if(optionalFood.isEmpty()){
          throw new Exception("food does not ");
       }else{
        return optionalFood.get();
       }
    }

    @Override
    public Food updateAvailibilityStatus(Long foodId) throws Exception {
       Food food = findFoodById(foodId);
       food.setAvailble(!food.isAvailble());
      return foodRepository.save(food);
    
    }

}
