package com.favshare.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.favshare.dto.UserAccountDto;
import com.favshare.dto.UserSignUpDto;
import com.favshare.entity.UserEntity;
import com.favshare.repository.UserRepository;

public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserAccountDto getByEmail(String email) {
		UserEntity userEntity;
		userEntity = userRepository.findByEmail(email);
		UserAccountDto result = new UserAccountDto(userEntity);
		return result;
	}

	public void insertUser(UserSignUpDto userSignUpDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.builder().name(userSignUpDto.getName()).email(userSignUpDto.getEmail())
				.password(userSignUpDto.getPassword()).nickname(userSignUpDto.getNickname())
				.birthDate(userSignUpDto.getBirthDate()).phone(userSignUpDto.getPhone()).build();
		userRepository.save(userEntity);
	}
	
	public void updatePassword(HashMap<String, String> userInfo) {
		UserEntity userEntity;
		userEntity = userRepository.findByEmail(userInfo.get("email"));
		userEntity.changePassword(userInfo.get("password"));
		userRepository.save(userEntity);
	}
	

}
