package com.favshare.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.FollowDto;
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
	
	@Autowired
	private UserService userService;
	
	
	public UserEntity getUserById(int id) {
		UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NullPointerException());
		return userEntity;		
	}
	
	public void insertFollow(int fromUserId, int toUserId) {
		UserEntity fromUserEntity = getUserById(fromUserId);		
		UserEntity toUserEntity = getUserById(toUserId);
		FollowEntity followEntity = FollowEntity.builder().toUserEntity(toUserEntity).fromUserEntity(fromUserEntity).build();
		followRepository.save(followEntity);
		
	}

	// from이 follower 맞나요??
	// 팔로워 리스트를 구하는 메서드, 나를 팔로우 하는 사람을 구해야 함
	public List<FollowDto> getFollowerById(int userId) { // 아이유
		UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NullPointerException());
		List<FollowEntity> followEntityList = userEntity.getToUserEntityList();

		List<FollowDto> result = new ArrayList<>();

		for (int i = 0; i < followEntityList.size(); i++) {
			int fromUserId = followEntityList.get(i).getFromUserEntity().getId();
			int toUserId = followEntityList.get(i).getToUserEntity().getId();
			String nickname = followEntityList.get(i).getFromUserEntity().getNickname();
			boolean isFollowForFollow = userService.getFollowForFollow(userId, fromUserId);
			String profileImageUrl = followEntityList.get(i).getFromUserEntity().getProfileImageUrl();
			result.add(new FollowDto(fromUserId, toUserId, nickname, isFollowForFollow, profileImageUrl));
		}

		return result;
	}
	
	// to가 following 맞나요???
	// 팔로잉 리스트를 구하는 메서드, 내가 팔로우 하는 사람을 구해야 함
	public List<FollowDto> getFollowingById(int userId) {
		UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NullPointerException());
		List<FollowEntity> followEntityList = userEntity.getFromUserEntityList(); 

		List<FollowDto> result = new ArrayList<>();

		for (int i = 0; i < followEntityList.size(); i++) {
			int fromUserId = followEntityList.get(i).getFromUserEntity().getId();
			int toUserId = followEntityList.get(i).getToUserEntity().getId();
			String nickname = followEntityList.get(i).getToUserEntity().getNickname();
			String profileImageUrl = followEntityList.get(i).getToUserEntity().getProfileImageUrl();
			result.add(new FollowDto(fromUserId, toUserId, nickname, false, profileImageUrl));
		}
		
		
		return result;
	}
	
	public void DeleteFollowById(int fromUserId, int toUserId) {
		UserEntity fromUserEntity = getUserById(fromUserId);		
		UserEntity toUserEntity = getUserById(toUserId);
		followRepository.deleteFollowByUserId(fromUserEntity, toUserEntity);
		
	}
	
	
	
}
