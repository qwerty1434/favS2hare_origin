package com.favshare.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.favshare.dto.UserAccountDto;
import com.favshare.dto.UserInfoDto;
import com.favshare.dto.UserProfileDto;
import com.favshare.dto.UserSignUpDto;
import com.favshare.entity.UserEntity;
import com.favshare.repository.UserRepository;

@Service
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
		System.out.println("11111111111111111");
		userEntity = userEntity.builder().name(userSignUpDto.getName()).email(userSignUpDto.getEmail())
				.password(userSignUpDto.getPassword()).nickname(userSignUpDto.getNickname())
				.birthDate(userSignUpDto.getBirthDate()).phone(userSignUpDto.getPhone()).build();
		System.out.println("22222222222222222");
		System.out.println(userEntity.toString());
		userRepository.save(userEntity);
		System.out.println("3333333333333333");
		

	}
	
	public void updatePassword(HashMap<String, String> userInfo) {
		UserEntity userEntity;
		userEntity = userRepository.findByEmail(userInfo.get("email"));
		userEntity.changePassword(userInfo.get("password"));
		userRepository.save(userEntity);
	}
	
	public UserProfileDto getUserProfileById(int id) {
		UserEntity userEntity;
		userEntity = userRepository.findById(id).get();
		UserProfileDto result = new UserProfileDto(userEntity);
		return result;
	}

	
	public void updateProfile(UserProfileDto userProfileDto) {
		UserEntity userEntity;
		userEntity = userRepository.findById(userProfileDto.getId()).get();
		userEntity.changeProfile(userProfileDto.getNickname(), userProfileDto.getContent(), userProfileDto.getProfileImageUrl());
		userRepository.save(userEntity);
	}
	
	public UserInfoDto getUserInfoById(int id) {
		UserEntity userEntity;
		userEntity = userRepository.findById(id).get();
		UserInfoDto result = new UserInfoDto(userEntity);
		return result;
		
	}
	
	public void updateUserInfo(UserInfoDto userInfoDto) {
		UserEntity userEntity;
		userEntity = userRepository.findById(userInfoDto.getId()).get();
		userEntity.changeUserInfo(userInfoDto.getName(), userInfoDto.getPassword(), userInfoDto.getPhone(), userInfoDto.getBirthDate());
		userRepository.save(userEntity);
	}

}
