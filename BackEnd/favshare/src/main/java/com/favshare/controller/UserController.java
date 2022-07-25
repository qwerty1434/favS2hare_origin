package com.favshare.controller;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.UserDto;
import com.favshare.entity.UserEntity;
import com.favshare.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@ApiOperation(value="모든 유저정보를 반환합니다",response=UserEntity.class)	
	@GetMapping("/searchAll")
	@Transactional()
	public ResponseEntity<List<UserDto>> getUserList() {
		List<UserEntity> result = userRepository.findAll();
		List<UserDto> temp = new LinkedList<UserDto>();
		for (int i = 0; i < result.size(); i++) {
			temp.add(new UserDto(result.get(i)));
		}
		return new ResponseEntity<>(temp,HttpStatus.OK);
	}

	@ApiOperation(value="id 값으로 찾은 특정 유저정보를 반환합니다",response=UserEntity.class)	
	@GetMapping("/search")
	@Transactional()
	public ResponseEntity<UserDto> getUser(Integer id) {
		UserEntity entityValue = userRepository.search(id);
		UserDto result = new UserDto(entityValue);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@GetMapping("/insert")
	@Transactional()
	public void add(UserDto dto) {
		UserEntity result = UserEntity.builder().name(dto.getName()).email(dto.getEmail()).password(dto.getPassword())
				.nickname(dto.getNickname()).birthDate(dto.getBirthDate()).phone(dto.getPhone())
				.content(dto.getContent()).profileImageUrl(dto.getProfileImageUrl()).build();
		userRepository.save(result);
	}

	@GetMapping("/delete")
	@Transactional()
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
}
