package com.food.OnlineFoodOrdering.service;

import org.springframework.stereotype.Service;

import com.food.OnlineFoodOrdering.Model.User;


@Service
public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    
    

    
} 