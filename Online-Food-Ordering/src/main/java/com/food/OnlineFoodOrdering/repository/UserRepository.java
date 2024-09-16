package com.food.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.OnlineFoodOrdering.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

       public User findByEmail(String username);

       
    
}
 