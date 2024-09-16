package com.food.OnlineFoodOrdering.request;

import com.food.OnlineFoodOrdering.Model.Address;

import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;

    
}
