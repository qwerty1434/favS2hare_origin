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

import com.favshare.dto.UserAccountDto;
import com.favshare.dto.UserSignUpDto;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/password")
public class UserPasswordController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "비밀번호 재설정", response = ResponseEntity.class)
	@PutMapping
	public ResponseEntity changePassword(@RequestBody HashMap<String, String> userInfo) {
		try {
			userService.updatePassword(userInfo);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}
	}

	@ApiOperation(value = "인증번호 입력 후 확인 클릭시", response = ResponseEntity.class)
	@GetMapping("/auth/input")
	public void checkAuth(@RequestBody HashMap<String, String> userInfo) {
		// 인증번호  완료 후 작업 필요
	}

	@ApiOperation(value = "인증번호 발송 클릭 시 ", response = ResponseEntity.class)
	@GetMapping("/sendAuth/{email}")
	public void sendAuth(@PathVariable("email") String email) {
		// 이메일 전송

	}

}
