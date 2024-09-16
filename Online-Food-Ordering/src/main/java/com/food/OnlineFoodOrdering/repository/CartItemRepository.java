package com.food.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.OnlineFoodOrdering.Model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long>{

    



}
