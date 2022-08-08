package com.favshare.controller;

import java.util.HashMap;

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

import com.favshare.dto.IdPasswordDto;
import com.favshare.dto.UserInfoDto;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/info")
public class UserInfoController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "비밀번호 확인", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity<UserInfoDto> checkPassword(@RequestBody IdPasswordDto idPasswordDto) {

		// id 값이 int로 넘어오는지 string 으로 넘어오는지 //
		UserInfoDto userInfoDto = userService.getUserInfoById(idPasswordDto.getId());

		if (userInfoDto.getPassword().equals(idPasswordDto.getPassword())) {
		
			return new ResponseEntity<UserInfoDto>(userInfoDto, HttpStatus.OK);
		} else {
			// 정보 보내주지 않음
			return new ResponseEntity<UserInfoDto>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "회원 정보 변경", response = ResponseEntity.class)
	@PutMapping
	public ResponseEntity changeUserInfo(@RequestBody UserInfoDto userInfoDto) {
		try {
			UserInfoDto result = userService.getUserInfoById(userInfoDto.getId());
			result.setName(userInfoDto.getName());
			result.setPassword(userInfoDto.getPassword());
			result.setPhone(userInfoDto.getPhone());
			result.setBirthDate(userInfoDto.getBirthDate());

			userService.updateUserInfo(result);
			
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

}
