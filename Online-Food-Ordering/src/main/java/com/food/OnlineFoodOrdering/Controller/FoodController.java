package com.food.OnlineFoodOrdering.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.OnlineFoodOrdering.Model.Food;
import com.food.OnlineFoodOrdering.Model.User;
import com.food.OnlineFoodOrdering.service.FoodService;
import com.food.OnlineFoodOrdering.service.RestaurantService;
import com.food.OnlineFoodOrdering.service.UserService;


@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;
 
    @Autowired
    private UserService userService;

  

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name, 
        @RequestHeader("Authorization") String jwt ) throws Exception{

            User user = userService.findUserByJwtToken(jwt);
            System.out.println(user);
            
           List<Food> food=foodService.searchFood(name);
           

           System.out.println(restaurantService.getClass().getName());


            return new ResponseEntity<>(food,HttpStatus.CREATED);
        }

        @GetMapping("/restaurant/{restaurantId}")
        public ResponseEntity<List<Food>> getRestaurantFood(
           
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonVeg,
            @RequestParam(required = false) String food_category,
            @PathVariable Long restaurantId,

            @RequestHeader("Authorization") String jwt ) throws Exception{
    
                User user = userService.findUserByJwtToken(jwt);
                System.out.println(user);
                
               List<Food> food=foodService.getRestaurantFood(restaurantId,vegetarian,nonVeg,seasonal,food_category);
    
    
                return new ResponseEntity<>(food,HttpStatus.OK);
            }
        
    
}
