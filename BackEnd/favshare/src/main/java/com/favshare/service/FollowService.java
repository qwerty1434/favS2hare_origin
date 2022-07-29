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
		UserEntity fromUserEntity = getUserById(fromUserId);		
		UserEntity toUserEntity = getUserById(toUserId);
		FollowEntity followEntity = FollowEntity.builder().toUserEntity(toUserEntity).fromUserEntity(fromUserEntity).build();
		followRepository.save(followEntity);
		
	}

	// from이 follower 맞나요??
	public List<FollowEntity> getFollowerById(int userId) { // 아이유
		UserEntity userEntity;
		userEntity = userRepository.findById(userId).get();
		List<FollowEntity> result = userEntity.getFromUserEntityList(); // 아이유 - 나 || 나,너 
		// userEntity.toUserEntityList(); // 아이유가 팔로우 한 사람 || 나
		return result;
	}
	// to가 following 맞나요???
	public List<FollowEntity> getFollowingById(int userId) {
		UserEntity userEntity;
		userEntity = userRepository.findById(userId).get();
		List<FollowEntity> result = userEntity.getToUserEntityList();
		return result;
	}
	
	public void DeleteFollowById(int fromUserId, int toUserId) {
		UserEntity fromUserEntity = getUserById(fromUserId);		
		UserEntity toUserEntity = getUserById(toUserId);
		followRepository.deleteFollowByUserId(fromUserEntity, toUserEntity);
		
	}
	
	
	
}
