package com.sa.secondary.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.secondary.model.Restaurant;

public interface RestaurantRepository  extends JpaRepository<Restaurant,Long>{

}
