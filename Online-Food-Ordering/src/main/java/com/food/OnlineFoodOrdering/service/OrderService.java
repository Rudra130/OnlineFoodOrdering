package com.food.OnlineFoodOrdering.service;

import java.util.List;



import com.food.OnlineFoodOrdering.Model.Order;
import com.food.OnlineFoodOrdering.Model.User;
import com.food.OnlineFoodOrdering.request.OrderRequest;


public interface OrderService {

    public Order createOrder(OrderRequest order, User user) throws Exception;
    public Order updateOrder(Long OrderId , String status)throws Exception;
    public void cancelOrder(Long orderId)throws Exception;
    public List<Order> getUsersOrder(Long userId)throws Exception; 
    public List<Order> getRestaurantOrder(Long restaurantId,String orderStatus)throws Exception; 
    public Order findOrderById(Long orderId)throws Exception;

    
}
    

