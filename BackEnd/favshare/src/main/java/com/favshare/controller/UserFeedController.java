package com.favshare.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.service.FeedService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user/feed")
public class UserFeedController {
	
	@Autowired
	private FeedService feedService;
	
	@ApiOperation(value="",response=ResponseEntity.class)	
	@GetMapping		
	public void func1(@RequestBody HashMap<String, String> Inputdata) {

		
		
		
	}
	
	@ApiOperation(value="",response=ResponseEntity.class)	
	@GetMapping("/pop")		
	public void func2() {
		
	}
	
	
}
