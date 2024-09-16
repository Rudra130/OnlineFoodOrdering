package com.food.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.OnlineFoodOrdering.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    
    public Cart findByCustomerId(Long userId);
    
    
}
