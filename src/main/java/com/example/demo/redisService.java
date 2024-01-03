package com.example.demo;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/redisService")
public class redisService {
	@Autowired
	
	
	private final UserDao userDao;

	
	public redisService(UserDao userDao) {
	       this.userDao = userDao;
	}
	@GetMapping("/redisHello")
	public String hello() {
		return "Hello chaps from springboot with redis";
	}
	@GetMapping("/getAllUsersADEA")
	public String getUsersADEAredis(){
	    
	   return userDao.getUsersADEAredis();
	}
	@GetMapping(value="/getPapers", produces="application/json;charset=UTF-8")
	public String getPapers(){
	    
	   return userDao.getPapers();
	}
}