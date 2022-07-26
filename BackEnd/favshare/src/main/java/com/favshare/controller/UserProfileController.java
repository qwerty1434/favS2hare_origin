package com.favshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.UserProfileDto;
import com.favshare.service.PopService;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private PopService popService;

	@ApiOperation(value = "프로필 보기", response = ResponseEntity.class)
	@GetMapping("/{userId}")
	public ResponseEntity<UserProfileDto> showProfile(@PathVariable("userId") int userId) {
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


	@ApiOperation(value = "프로필 수정 화면 들어올 시", response = ResponseEntity.class)
	@GetMapping("/edit/{userId}")
	public ResponseEntity<UserProfileDto> showEditProfile(@PathVariable("userId") int userId) {
//		UserProfileDto userProfileDto = userService.getUserProfileById(userId);
		try {

			UserProfileDto userProfileDto = userService.getUserProfileById(userId);

			return new ResponseEntity<UserProfileDto>(userProfileDto, HttpStatus.OK);
		} catch (Exception e) {
			//왜 되지..???????객체 없이 httpstatus만 반환 가능?
			return new ResponseEntity<UserProfileDto>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@ApiOperation(value = "프로필 수정", response = ResponseEntity.class)
	@PutMapping
	public ResponseEntity changeProfile(@RequestBody UserProfileDto userProfileDto) {
		try {
			UserProfileDto result = userService.getUserProfileById(userProfileDto.getId());
			result.setNickname(userProfileDto.getNickname());
			result.setContent(userProfileDto.getContent());
			result.setProfileImageUrl(userProfileDto.getProfileImageUrl());
			
			userService.updateProfile(result);
			
			return new ResponseEntity(HttpStatus.OK); 
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
