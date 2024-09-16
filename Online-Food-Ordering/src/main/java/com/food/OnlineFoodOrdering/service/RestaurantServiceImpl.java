package com.food.OnlineFoodOrdering.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.OnlineFoodOrdering.Model.Address;
import com.food.OnlineFoodOrdering.Model.Restaurant;
import com.food.OnlineFoodOrdering.Model.User;
import com.food.OnlineFoodOrdering.dto.RestaurantDto;
import com.food.OnlineFoodOrdering.repository.AddressRepository;
import com.food.OnlineFoodOrdering.repository.RestaurantRepository;
import com.food.OnlineFoodOrdering.repository.UserRepository;
import com.food.OnlineFoodOrdering.request.CreateRestaurantRequest; 


@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInfo(req.getContactInfo());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);    
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedRestaurant.getDescription());

        }
        if(restaurant.getName()!=null){
            restaurant.setName(updatedRestaurant.getName());
        } 
        return restaurantRepository.save(restaurant); 
        
    }

    @Override
    public void deleteRestaurant(Long reataurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(reataurantId);
   
        restaurantRepository.delete(restaurant);
    }
       

    @Override
    public List<Restaurant> getAllRestaurant() {

        return  restaurantRepository.findAll();
        
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {

        return restaurantRepository.findBySearchQuery(keyword);
        
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("restaurant not found with id");
        }

        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
              Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
              if(restaurant==null){
                  throw new Exception("restaurant not found with owner id");

              }
              return restaurant;
        
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {

         Restaurant restaurant = findRestaurantById(restaurantId);
         RestaurantDto dto= new RestaurantDto();
         dto.setDescription(restaurant.getDescription());
         dto.setImages(restaurant.getImages());
         dto.setTitle(restaurant.getName());
         dto.setId(restaurantId);

         if(user.getFavorites().contains(dto)){
               user.getFavorites().remove(dto);
         }else{
            user.getFavorites().add(dto);
         }
         userRepository.save(user);

         return null;

    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());



        return restaurantRepository.save(restaurant);
       
    }
    
}
