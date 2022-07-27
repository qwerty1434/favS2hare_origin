package com.favshare.controller;

import java.util.*;




import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.UserAccountDto;
import com.favshare.dto.UserSignUpDto;
import com.favshare.repository.UserRepository;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(value="로그인",response=ResponseEntity.class)
	//get방식은 안됨 pathvariable만 가능한듯?
	@PostMapping("/login")
	public ResponseEntity userLogin(@RequestBody HashMap<String, String> userInfo) {
        UserAccountDto result = userService.getByEmail(userInfo.get("email"));

        // 보안 강화 필요
        // 반환값 필요한지 확인
        if(userInfo.get("email").equals(result.getEmail())&&
        		userInfo.get("password").equals(result.getPassword())) {
        	System.out.println("succ");
        	return new ResponseEntity(HttpStatus.OK);
        }else {        	
        	System.out.println("fail");
        	return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

	}
	
	@ApiOperation(value="회원가입",response=ResponseEntity.class)	
	@PostMapping("/signup")	
	public ResponseEntity userSignUp(@RequestBody UserSignUpDto userSignUpDto) {
		try {			
			System.out.println(userSignUpDto.getEmail());
			System.out.println(userSignUpDto.getName());
			userService.insertUser(userSignUpDto);
			return new ResponseEntity(HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	

	

}
