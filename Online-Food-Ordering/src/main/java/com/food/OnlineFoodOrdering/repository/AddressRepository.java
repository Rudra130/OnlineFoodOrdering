package com.food.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.OnlineFoodOrdering.Model.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{

}
