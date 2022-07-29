package com.favshare.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.EmailPasswordDto;
import com.favshare.dto.FeedDto;
import com.favshare.dto.FollowDto;
import com.favshare.dto.PopInFeedDto;
import com.favshare.dto.UserAccountDto;
import com.favshare.dto.UserInfoDto;
import com.favshare.dto.UserProfileDto;
import com.favshare.dto.UserSignUpDto;
import com.favshare.dto.YoutubeBookmarkDto;
import com.favshare.dto.YoutubeDto;
import com.favshare.entity.FeedEntity;
import com.favshare.entity.PopInFeedEntity;
import com.favshare.entity.StoreYoutubeEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.FeedRepository;
import com.favshare.repository.UserRepository;
import com.favshare.repository.YoutubeRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FeedRepository feedRepository;

	
	@Autowired
	private ModelMapper modelMapper;	
	

	public UserAccountDto getByEmail(String email) {
		UserEntity userEntity;
		userEntity = userRepository.findByEmail(email);
		UserAccountDto result = new UserAccountDto(userEntity);
		return result;
	}

	public void insertUser(UserSignUpDto userSignUpDto) {
		UserEntity userEntity = new UserEntity();
		userEntity = userEntity.builder().name(userSignUpDto.getName()).email(userSignUpDto.getEmail())
				.password(userSignUpDto.getPassword()).nickname(userSignUpDto.getNickname())
				.birthDate(userSignUpDto.getBirthDate()).phone(userSignUpDto.getPhone()).build();
		userRepository.save(userEntity);
	}
	
	public void updatePassword(EmailPasswordDto emailPasswordDto) {
		UserEntity userEntity;
		userEntity = userRepository.findByEmail(emailPasswordDto.getEmail());
		userEntity.changePassword(emailPasswordDto.getPassword());
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
	
	public int[] countFollow(int userId) {
		UserEntity userEntity = userRepository.findById(userId).get();
		int followerNum = userEntity.getFromUserEntityList().size();
		int followingNum = userEntity.getToUserEntityList().size();
		return new int[] {followerNum, followingNum};
	}
	
	public List<FeedDto> getFeedList(int userId) {
		UserEntity userEntity = userRepository.findById(userId).get();
		List<FeedEntity> feedEntityList = userEntity.getFeedList();		
		List<FeedDto> feedDtoList = Arrays.asList(modelMapper.map(feedEntityList, FeedDto[].class));
		return feedDtoList;
	}
	
	public List<PopInFeedDto> getPopInFeedList(int feedId){
		FeedEntity feedEntity = feedRepository.findById(feedId).get();
		List<PopInFeedEntity> popInFeedEntityList = feedEntity.getPopInFeedList();
		List<PopInFeedDto> popInFeedDtoList = Arrays.asList(modelMapper.map(popInFeedEntityList, PopInFeedDto[].class));
		return popInFeedDtoList;
	}
	
	
	

}
