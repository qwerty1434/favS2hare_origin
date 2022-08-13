package com.favshare.controller;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.PopDto;
import com.favshare.dto.UserPopIdDto;
import com.favshare.dto.UserProfileDto;

import com.favshare.service.PopService;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user/feed")
public class UserFeedController {
	
	
	@Autowired
	private PopService popService;
	
	@Autowired
	private UserService userService;
	
	
	
	@ApiOperation(value="feed에서 pop 하나 선택 시 pop의 재생 화면 나타내기",response=ResponseEntity.class)	
	@PostMapping("/pop")		
	public ResponseEntity<HashMap<String, Object>> showOnePop(@RequestBody UserPopIdDto userPopIdDto) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int userId = userPopIdDto.getUserId();
		int popId = userPopIdDto.getPopId();

		try {
			PopDto popDto = popService.getPopDtoById(popId);
			UserProfileDto userProfileDto = userService.getUserProfileById(userId);
			
			result.put("popInfo", popDto);
			result.put("userInfo", userProfileDto);
			
			return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<HashMap<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
