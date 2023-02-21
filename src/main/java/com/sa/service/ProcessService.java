package com.sa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.primary.model.UserDetails;
import com.sa.primary.repo.UserRepository;
import com.sa.secondary.model.Restaurant;
import com.sa.secondary.repo.RestaurantRepository;
import com.sa.vo.UserAndRestDataVO;
import com.sa.vo.UserInfo;

@Service
public class ProcessService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	

	@Transactional
	@Modifying
	public String process(UserInfo userInfo) {
		
		System.out.println("Request Delete Process");
		userRepo.deleteById(userInfo.getUserId());
		restaurantRepository.deleteById(userInfo.getResturantId());
		return "Success";
	}
	
	
	@Transactional
	//@Modifying
	public String save(UserAndRestDataVO userInfo) {
		UserDetails user=new UserDetails();
		Restaurant res=new Restaurant();
		user.setFirstName(userInfo.getFirstName());
		user.setLastname(userInfo.getLastname());
		System.out.println("Request Delete Process");
		userRepo.save(user);
		
		res.setName(userInfo.getName());
		res.setAddress(userInfo.getAddress());
		restaurantRepository.save(res);
		
		return "Success";
	}

}
