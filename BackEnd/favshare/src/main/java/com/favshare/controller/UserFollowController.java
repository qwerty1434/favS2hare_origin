package com.favshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.UserProfileDto;
import com.favshare.service.PopService;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/follow")
public class UserFollowController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PopService popService;

	@ApiOperation(value = "나를 팔로우 하는 사람 팔로워", response = ResponseEntity.class)
	@GetMapping("/from/{userId}")
	public ResponseEntity<UserProfileDto> showFollowerList(@PathVariable("userId") int userId) {
//		UserProfileDto userProfileDto = userService.getUserProfileById(userId);
		try {

			int popCount = popService.getPopCount(userId);

			UserProfileDto userProfileDto = userService.getUserProfileById(userId);

			userProfileDto.setPopCount(popCount);

			// set으로 팔로워 팔로잉 등등 처리해야함

			return new ResponseEntity<UserProfileDto>(userProfileDto, HttpStatus.OK);
		} catch (Exception e) {
			//왜 되지..???????객체 없이 httpstatus만 반환 가능?
			return new ResponseEntity<UserProfileDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
}
