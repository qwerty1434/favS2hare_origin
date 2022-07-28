package com.favshare.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.PopDto;
import com.favshare.dto.PopInfoDto;
import com.favshare.dto.YoutubeEditPopDto;
import com.favshare.entity.FeedEntity;
import com.favshare.entity.PopEntity;
import com.favshare.entity.PopInFeedEntity;
import com.favshare.entity.UserEntity;
import com.favshare.entity.YoutubeEntity;
import com.favshare.repository.FeedRepository;
import com.favshare.repository.PopInFeedRepository;
import com.favshare.repository.PopRepository;
import com.favshare.repository.UserRepository;
import com.favshare.repository.YoutubeRepository;


@Service
public class PopService {

	@Autowired
	private PopRepository popRepository;
	
	@Autowired
	private YoutubeRepository youtubeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PopInFeedRepository popInFeedRepository;
	
	@Autowired
	private FeedRepository feedRepository;
	
	
	
	public void updatePopView(int popId) {
		PopEntity popEntity = popRepository.findById(popId).get();
		popEntity.changeView();
		popRepository.save(popEntity);
	}
	
	public PopInfoDto getPopInfoById(int popId) {
		PopEntity popEntity = popRepository.findById(popId).get();
		PopInfoDto popInfoDto = new PopInfoDto(popEntity, popEntity.getYoutubeEntity());
		
		return popInfoDto;
	}
	
	//유튜브 찾은 후 그에 맞는 
	public List<PopDto> getPopListById(int youtubeId){
		YoutubeEntity youtubeEntity = youtubeRepository.findById(youtubeId).get();
		List<PopDto> popList = new ArrayList<>();
		for(int i=0; i<youtubeEntity.getPopList().size(); i++) {
			popList.add(new PopDto(youtubeEntity.getPopList().get(i)));
		}
		return popList;
	}
	
	public void insertPop(YoutubeEditPopDto youtubeEditPopDto) {
		UserEntity userEntity = userRepository.findById(youtubeEditPopDto.getUserId()).get();
		YoutubeEntity youtubeEntity = youtubeRepository.findById(youtubeEditPopDto.getYoutubeId()).get();
		FeedEntity feedEntity = feedRepository.findById(youtubeEditPopDto.getFeedId()).get();
		
		PopEntity popEntity = new PopEntity();
		
		//팝에도 넣어주고
		popEntity = popEntity.builder().name(youtubeEditPopDto.getName())
				.startSecond(youtubeEditPopDto.getStartSecond())
				.endSecond(youtubeEditPopDto.getEndSecond())
				.content(youtubeEditPopDto.getContent())
				.createDate(youtubeEditPopDto.getCreateDate())
				.views(youtubeEditPopDto.getViews())
				.userEntity(userEntity)
				.youtubeEntity(youtubeEntity).build();
		popRepository.save(popEntity);
		
		//popinfeed에도 넣어준다
		PopInFeedEntity popInFeedEntity = new PopInFeedEntity();
		popInFeedEntity = popInFeedEntity.builder().popEntity(popEntity)
				.feedEntity(feedEntity).build();
		
		popInFeedRepository.save(popInFeedEntity);
		
	}

	public int getPopCount(int userId) {
		UserEntity userEntity = userRepository.findById(userId).get();
		return userEntity.getPopList().size();
	}
	
}
