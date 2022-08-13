package com.favshare.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.favshare.dto.FeedPopIdDto;
import com.favshare.entity.CommentEntity;
import com.favshare.entity.FeedEntity;
import com.favshare.entity.LikeCommentEntity;
import com.favshare.entity.PopEntity;
import com.favshare.entity.PopInFeedEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.FeedRepository;
import com.favshare.repository.PopInFeedRepository;
import com.favshare.repository.PopRepository;

@Service
public class PopInFeedService {

	@Autowired
	private PopInFeedRepository popInFeedRepository;

	@Autowired
	private FeedRepository feedRepository;

	@Autowired
	private PopRepository popRepository;

	public void insertPopInFeed(List<FeedPopIdDto> feedPopIdDto) {

		for (int i = 0; i < feedPopIdDto.size(); i++) {
			FeedEntity feedEntity = feedRepository.findById(feedPopIdDto.get(i).getFeedId()).get();
			PopEntity popEntity = popRepository.findById(feedPopIdDto.get(i).getPopId()).get();
			PopInFeedEntity popInFeedEntity = PopInFeedEntity.builder().feedEntity(feedEntity).popEntity(popEntity)
					.build();
			popInFeedRepository.save(popInFeedEntity);
		}

	}

	public void deletePopInFeed(FeedPopIdDto feedPopIdDto) {
		int feedId = feedPopIdDto.getFeedId();
		int popId = feedPopIdDto.getPopId();
		popInFeedRepository.deleteByPopFeedId(feedId, popId);
	}

}
