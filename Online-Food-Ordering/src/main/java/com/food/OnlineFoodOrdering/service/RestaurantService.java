package com.food.OnlineFoodOrdering.service;

import com.food.OnlineFoodOrdering.Model.Restaurant;
import com.food.OnlineFoodOrdering.Model.User;
import com.food.OnlineFoodOrdering.dto.RestaurantDto;
import com.food.OnlineFoodOrdering.request.CreateRestaurantRequest;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req ,User user);
    public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest updatedRestaurant)throws Exception;
    public void deleteRestaurant(Long reataurantId) throws Exception;
    public List<Restaurant> getAllRestaurant();
    public List<Restaurant>  searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Long id)throws Exception;
    public Restaurant getRestaurantByUserId(Long userId)throws Exception;
    public RestaurantDto addToFavorites(Long restaurantId,User user)throws Exception;
    public Restaurant updateRestaurantStatus(Long id)throws Exception;
}
