package com.food.OnlineFoodOrdering.request;

import com.food.OnlineFoodOrdering.Model.Address;
import com.food.OnlineFoodOrdering.Model.ContactInformation;
import java.util.List;

import lombok.Data;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInfo;
    private String openingHours;
    private List<String> images;
    


    




}
