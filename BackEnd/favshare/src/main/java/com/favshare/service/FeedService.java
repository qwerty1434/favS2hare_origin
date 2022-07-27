package com.favshare.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.favshare.dto.FeedDto;
import com.favshare.entity.FeedEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.FeedRepository;
import com.favshare.repository.UserRepository;


@Service
public class FeedService {
	
	@Autowired
	private FeedRepository feedRepository;
	
	public void insertFeed(int userId) {
		FeedEntity feedEntity = new FeedEntity();
		// 아래 부분 논의해보기
//		feedEntity = feedEntity.builder().userId(userId).build();
//		feedRepository.save(feedEntity);
	}
	
	public void deleteFeed(int feedId) {
		feedRepository.deleteById(feedId);
	}
	
	
	public void updateFeedName(HashMap<String, String> userInfo) {
		FeedEntity feedEntity;
		feedEntity = feedRepository.findById(Integer.parseInt(userInfo.get("id"))).get();
		feedEntity.changeName(userInfo.get("name"));
		feedRepository.save(feedEntity);
	}
	
	public void updateFeedImage(HashMap<String, String> userInfo) {
		FeedEntity feedEntity;
		feedEntity = feedRepository.findById(Integer.parseInt(userInfo.get("id"))).get();
		feedEntity.changeImageUrl(userInfo.get("feedImageUrl"));
		feedRepository.save(feedEntity);
	}
	
	public void updateFirstFeed(int feedId) {
		FeedEntity feedEntity;
		feedEntity = feedRepository.findById(feedId).get();
		
		
	}


}
