package com.sa.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sa.primary.model.UserDetails;
import com.sa.secondary.model.Restaurant;
import com.sa.service.ProcessService;
import com.sa.vo.UserAndRestDataVO;
import com.sa.vo.UserInfo;


@RestController
public class ApiController {
	
	@Autowired
	ProcessService processService;
	
//	@PersistenceContext
//	EntityManager entityManager;
	
	@PostMapping(value = "/userdelete")
	public ResponseEntity<String> process( @RequestBody UserInfo userInfo) {
System.out.println("Request Coming");
		String resp = processService.process(userInfo);
		return new ResponseEntity<String>(resp, HttpStatus.OK);

	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save( @RequestBody UserAndRestDataVO userInfo) {
System.out.println("Request Coming");
		String resp = processService.save(userInfo);
		return new ResponseEntity<String>(resp, HttpStatus.OK);

	}
	
	/*
	 * @GetMapping("/data") public List<UserDetails> getAllByDevicelD(){ String sql
	 * = "SELECT * FROM user_details WHERE id = 1"; Query query =
	 * entityManager.createNativeQuery(sql); ; List<UserDetails> list =
	 * query.getResultList();
	 * 
	 * return list; }
	 */

}
