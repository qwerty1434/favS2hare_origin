package com.favshare.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.CommentDto;
import com.favshare.dto.UserDto;
import com.favshare.entity.CommentEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/searchAll")
	@Transactional()
	public String searchAll() {
		String result = userRepository.findAll().toString();
		System.out.println(result);
		return result;
	}

	@GetMapping("/search")
	@Transactional()
	public UserDto search(Integer id) {
		UserEntity entityValue = userRepository.search(id);
		UserDto result = new UserDto(entityValue.getId(), entityValue.getName(), entityValue.getEmail(),
				entityValue.getPassword(), entityValue.getNickname(), entityValue.getBirthDate(),
				entityValue.getPhone(), entityValue.getContent(), entityValue.getProfileImageUrl());
		return result;
	}

	@GetMapping("/insert")
	@Transactional()
	public void insert(UserDto dto) {
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
