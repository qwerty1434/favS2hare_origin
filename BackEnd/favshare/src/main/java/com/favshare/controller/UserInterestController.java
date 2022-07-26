package com.favshare.controller;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/interest")
public class UserInterestController {
	@ApiOperation(value = "", response = ResponseEntity.class)
	@PostMapping
	public void saveInterest() {
		
	}

	@ApiOperation(value = "", response = ResponseEntity.class)
	@GetMapping("/sing")
	public void showSing() {
	}
	
	@ApiOperation(value = "", response = ResponseEntity.class)
	@GetMapping("/findIdol")
	public void findIdol() {
	}
	
	@ApiOperation(value = "", response = ResponseEntity.class)
	@GetMapping("/idol")
	public void showIdol() {
	}
	
}
