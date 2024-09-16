package com.food.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.OnlineFoodOrdering.Model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    
}
