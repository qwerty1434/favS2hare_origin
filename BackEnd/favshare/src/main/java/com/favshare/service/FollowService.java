package com.favshare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		if (followRepository.countFollowFByUserId(fromUserId, toUserId) < 1) {
			UserEntity fromUserEntity = getUserById(fromUserId);
			UserEntity toUserEntity = getUserById(toUserId);
			FollowEntity followEntity = FollowEntity.builder().toUserEntity(toUserEntity).fromUserEntity(fromUserEntity)
					.build();
			followRepository.save(followEntity);

		}

	}

	public List<FollowEntity> getFollowerById(int userId) {
		UserEntity userEntity;
		userEntity = userRepository.findById(userId).get();
		List<FollowEntity> result = userEntity.getToUserEntityList();

		return result;
	}

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
