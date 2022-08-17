package com.favshare.service;

import java.util.Arrays;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.IdolDto;
import com.favshare.dto.UserAllDto;
import com.favshare.entity.FollowEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.FollowRepository;
import com.favshare.repository.UserRepository;

@Service
public class FollowService {
	@Autowired
	private FollowRepository followRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public UserEntity getUserById(int id) {
		UserEntity userEntity = userRepository.findById(id).get();
		return userEntity;		
	}
	
	public void insertFollow(int fromUserId, int toUserId) {
		if(followRepository.countFollowFByUserId(fromUserId, toUserId) < 1) {
			UserEntity fromUserEntity = getUserById(fromUserId);		
			UserEntity toUserEntity = getUserById(toUserId);
			FollowEntity followEntity = FollowEntity.builder().toUserEntity(toUserEntity).fromUserEntity(fromUserEntity).build();
			followRepository.save(followEntity);
			
		}
		
	}

	// from이 follower 맞나요??
	// 팔로워 리스트를 구하는 메서드, 나를 팔로우 하는 사람을 구해야 함
	public List<FollowEntity> getFollowerById(int userId) { // 아이유
		UserEntity userEntity;
		userEntity = userRepository.findById(userId).get();
		List<FollowEntity> result = userEntity.getToUserEntityList();

		return result;
	}
	
	// to가 following 맞나요???
	// 팔로잉 리스트를 구하는 메서드, 내가 팔로우 하는 사람을 구해야 함
	public List<FollowEntity> getFollowingById(int userId) {
		UserEntity userEntity;
		userEntity = userRepository.findById(userId).get();
		List<FollowEntity> result = userEntity.getFromUserEntityList(); 

		return result;
	}
	
	public void DeleteFollowById(int fromUserId, int toUserId) {
		UserEntity fromUserEntity = getUserById(fromUserId);		
		UserEntity toUserEntity = getUserById(toUserId);
		followRepository.deleteFollowByUserId(fromUserEntity, toUserEntity);
		
	}
	
	
	
}
