package com.food.OnlineFoodOrdering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.OnlineFoodOrdering.Model.User;
import com.food.OnlineFoodOrdering.config.JwtProvider;
import com.food.OnlineFoodOrdering.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;


    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

       String email= jwtProvider.getEmailFromJwtToken(jwt);
       User user = findUserByEmail(email);

       return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
          User user = userRepository.findByEmail(email);
            if(user==null){
                throw new Exception("user not found");
            }
        return user;
    }
    
}
