package com.favshare.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.FeedUserIdDto;
import com.favshare.service.FeedService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user/feed")
public class UserFeedController {
	
	@Autowired
	private FeedService feedService;
	
	@ApiOperation(value="원하는 피드를 선택했을 때 해당 피드에 있는 pops 목록을 보여줍니다",response=ResponseEntity.class)	
	@GetMapping
	public void func1(@RequestBody FeedUserIdDto feedUserIdDto) {
		// popsinfeedid
		// pops리스트
		// 동영상 리스트

		
		
		
	}
	
	@ApiOperation(value="",response=ResponseEntity.class)	
	@GetMapping("/pop")		
	public void func2(int popId) {
		
	}
	
	
}
