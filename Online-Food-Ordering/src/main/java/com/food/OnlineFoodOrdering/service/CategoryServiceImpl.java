package com.food.OnlineFoodOrdering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.OnlineFoodOrdering.Model.Category;
import com.food.OnlineFoodOrdering.Model.Restaurant;
import com.food.OnlineFoodOrdering.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {


   @Autowired
  public CategoryRepository categoryRepository;

   @Autowired
   private RestaurantService restaurantService;

    @Override
    public Category createCategory(String name, long userId) throws Exception {
          Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
          Category category = new Category();
          category.setName(name);
          category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
           Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(long id) throws Exception {
        
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new Exception("category not found");
        }


        return optionalCategory.get();


    }


    
}
