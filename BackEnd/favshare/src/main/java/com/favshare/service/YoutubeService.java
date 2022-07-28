package com.favshare.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.FeedDto;
import com.favshare.dto.PopDto;
import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeInfoDto;
import com.favshare.entity.UserEntity;
import com.favshare.entity.YoutubeEntity;
import com.favshare.repository.UserRepository;
import com.favshare.repository.YoutubeRepository;

@Service
public class YoutubeService {
	
	@Autowired
	YoutubeRepository youtubeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public YoutubeDetailDto getDetailById(int id) {
		YoutubeEntity youtubeEntity;
		youtubeEntity = youtubeRepository.findById(id).get();
		
		YoutubeDetailDto result = new YoutubeDetailDto(youtubeEntity);
		List<PopDto> popList = Arrays.asList(modelMapper.map(youtubeEntity.getPopList(),PopDto[].class));
		result.setPopList(popList);
		return result;
	}
	
	public YoutubeInfoDto getEditInfoById(Map<String,String> editInfo) {
		UserEntity userEntity = userRepository.findById(Integer.parseInt(editInfo.get("userId"))).get();
		YoutubeEntity youtubeEntity = youtubeRepository.findById(Integer.parseInt(editInfo.get("youtubeId"))).get();
		
		YoutubeInfoDto result = new YoutubeInfoDto(userEntity, youtubeEntity);
		List<FeedDto> feedList = Arrays.asList(modelMapper.map(userEntity.getFeedList(),FeedDto[].class));
		result.setFeedList(feedList);
		return result;
	}
}
