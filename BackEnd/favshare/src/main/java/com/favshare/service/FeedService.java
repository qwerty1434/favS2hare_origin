package com.favshare.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.favshare.dto.FeedDto;
import com.favshare.entity.FeedEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.FeedRepository;
import com.favshare.repository.UserRepository;

import antlr.collections.List;


@Service
public class FeedService {
	
	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void insertFeed(int userId) {
		FeedEntity feedEntity = new FeedEntity();
		UserEntity userEntity = userRepository.findById(userId).get();
		if(feedRepository.countFeedByUserId(userId) == 0) {
			feedEntity = feedEntity.builder().name("피드").isFirst(true).feedImageUrl(null).userEntity(userEntity).build();			
		}else {			
			feedEntity = feedEntity.builder().name("피드").isFirst(false).feedImageUrl(null).userEntity(userEntity).build();
		}
		feedRepository.save(feedEntity);
	}
	
	// popInFeed에서 해당 피드에 관련된 영상을 모두 지워야한다. => 아직 구현 x
	public void deleteFeed(int feedId) {
		feedRepository.deleteById(feedId);
	}
	
	
	public void updateFeedName(HashMap<String, String> feedInfo) {
		FeedEntity feedEntity;
		feedEntity = feedRepository.findById(Integer.parseInt(feedInfo.get("id"))).get();
		feedEntity.changeName(feedInfo.get("name"));
		feedRepository.save(feedEntity);
	}
	
	public void updateFeedImage(HashMap<String, String> feedInfo) {
		FeedEntity feedEntity;
		feedEntity = feedRepository.findById(Integer.parseInt(feedInfo.get("id"))).get();
		feedEntity.changeImageUrl(feedInfo.get("feedImageUrl"));
		feedRepository.save(feedEntity);
	}
	
	// api에서 userId를 같이 보내주면 어떨까요? => 기존의 대표피드를 찾기 위해 필요합니다.
	// 일단 REST API Doc 수정했음.
	public void updateFirstFeed(HashMap<String, String> feedInfo) {
		FeedEntity newFeedEntity, oldFeedEntity;
		newFeedEntity = feedRepository.findById(Integer.parseInt(feedInfo.get("feedId"))).get();
		newFeedEntity.changeIsFirst();
		
		int oldFirstFeedId = feedRepository.findFirstId(Integer.parseInt(feedInfo.get("userId")));
		oldFeedEntity = feedRepository.findById(oldFirstFeedId).get();
		oldFeedEntity.changeIsNotFirst();
		
		feedRepository.save(newFeedEntity);
		feedRepository.save(oldFeedEntity);
	}


}
