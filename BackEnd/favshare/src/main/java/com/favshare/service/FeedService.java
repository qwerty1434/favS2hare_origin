package com.favshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.input.FeedUserIdDto;
import com.favshare.dto.input.IdFeedImageUrlDto;
import com.favshare.dto.input.IdNameDto;
import com.favshare.entity.FeedEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.FeedRepository;
import com.favshare.repository.UserRepository;

@Service
public class FeedService {

	@Autowired
	private FeedRepository feedRepository;

	@Autowired
	private UserRepository userRepository;

	public void insertFeed(int userId) {
		FeedEntity feedEntity;
		UserEntity userEntity = userRepository.findById(userId).get();
		if (feedRepository.countFeedByUserId(userId) == 0) {
			feedEntity = FeedEntity.builder().name("피드").isFirst(true).feedImageUrl(null).userEntity(userEntity)
					.build();
		} else {
			feedEntity = FeedEntity.builder().name("피드").isFirst(false).feedImageUrl(null).userEntity(userEntity)
					.build();
		}
		feedRepository.save(feedEntity);
	}

	public void deleteFeed(int feedId) {
		feedRepository.deleteById(feedId);
	}

	public void updateFeedName(IdNameDto idNameDto) {
		FeedEntity feedEntity;
		feedEntity = feedRepository.findById(idNameDto.getId()).get();
		feedEntity.changeName(idNameDto.getName());
		feedRepository.save(feedEntity);
	}

	public void updateFeedImage(IdFeedImageUrlDto idFeedImageUrlDto) {
		FeedEntity feedEntity;
		feedEntity = feedRepository.findById(idFeedImageUrlDto.getId()).get();
		feedEntity.changeImageUrl(idFeedImageUrlDto.getFeedImageUrl());
		feedRepository.save(feedEntity);
	}

	public void updateFirstFeed(FeedUserIdDto feedUserIdDto) {
		FeedEntity newFeedEntity, oldFeedEntity;
		newFeedEntity = feedRepository.findById(feedUserIdDto.getFeedId()).get();
		newFeedEntity.changeIsFirst();

		int oldFirstFeedId = feedRepository.findFirstId(feedUserIdDto.getUserId());
		oldFeedEntity = feedRepository.findById(oldFirstFeedId).get();
		oldFeedEntity.changeIsNotFirst();

		feedRepository.save(newFeedEntity);
		feedRepository.save(oldFeedEntity);
	}

}
