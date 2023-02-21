package com.sa.secondary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sa.secondary.model.Restaurant;
import com.sa.secondary.repo.RestaurantRepository;

@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantRepository restaurabtRepository;
	
	@GetMapping("/restaurants")
	List<Restaurant> getRestauratn(){
		return restaurabtRepository.findAll();
		
	}
	
	@PostMapping(path="/restaurants")
	List<Restaurant>  addUser(@RequestBody Restaurant restaurant) throws  Exception{
		
		restaurabtRepository.save(restaurant);
		return restaurabtRepository.findAll();
	}

}
