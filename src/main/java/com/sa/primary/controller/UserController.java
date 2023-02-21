package com.sa.primary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sa.primary.model.UserDetails;
import com.sa.primary.repo.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	List<UserDetails> getUsers(){
		return userRepository.findAll();
		
	}
	
	@PostMapping("/users")
	List<UserDetails>  addUser(@RequestBody UserDetails userDetails) throws  Exception{
		
		userRepository.save(userDetails);
		return userRepository.findAll();
	}

}
