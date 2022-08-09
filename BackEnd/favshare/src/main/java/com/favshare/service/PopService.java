package com.favshare.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.favshare.dto.IdolDto;
import com.favshare.dto.PopDto;
import com.favshare.dto.PopInfoDto;
import com.favshare.dto.UserPopIdDto;
import com.favshare.dto.YoutubeEditPopDto;
import com.favshare.entity.FeedEntity;
import com.favshare.entity.InterestIdolEntity;
import com.favshare.entity.InterestSongEntity;
import com.favshare.entity.PopEntity;
import com.favshare.entity.PopInFeedEntity;
import com.favshare.entity.UserEntity;
import com.favshare.entity.YoutubeEntity;
import com.favshare.repository.FeedRepository;
import com.favshare.repository.InterestIdolRepository;
import com.favshare.repository.InterestSongRepository;
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
	
	@Autowired
	private InterestIdolRepository interestIdolRepository;

	@Autowired
	private InterestSongRepository interestSongRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
	
	public PopDto getPopDtoById(int popId) {
		PopEntity popEntity = popRepository.findById(popId).get();
		PopDto popDto = new PopDto(popEntity);
		return popDto;
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
		YoutubeEntity youtubeEntity = youtubeRepository.findByUrl(youtubeEditPopDto.getYoutubeUrl());
		
		//youtube가 아직데이터 베이스에 없을 시 데이터베이스에 넣어주기
		if(youtubeEntity == null) {
			youtubeEntity = youtubeEntity.builder().url(youtubeEditPopDto.getYoutubeUrl()).build();
			youtubeRepository.save(youtubeEntity);
		}
		
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
	
	public void deletePop(List<Integer> popIdList) {
		for(int i = 0; i < popIdList.size(); i++) {
			popRepository.deleteById(popIdList.get(i));
		}
	}

	public List<Integer> findSimilarIdolInterst(int userId, int idolId) {
		List<InterestIdolEntity> interestIdolEntityList = interestIdolRepository.findByIdolIdExceptUserId(userId, idolId);
		
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < interestIdolEntityList.size(); i++) {
			result.add(interestIdolEntityList.get(i).getUserEntity().getId());
		}
		return result;
	}
	
	public List<Integer> findSimilarSongInterst(int userId, int songId) {
		List<InterestSongEntity> interestSongEntityList = interestSongRepository.findBySongIdExceptUserId(userId, songId);

		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < interestSongEntityList.size(); i++) {
			result.add(interestSongEntityList.get(i).getUserEntity().getId());
		}
		return result;
	}
	
	public List<PopDto> popDtoListByUserId(int userId) {
		UserEntity userEntity = userRepository.findById(userId).get();
		List<PopEntity> popEntity = userEntity.getPopList();

		List<PopDto> popDtoList = Arrays.asList(modelMapper.map(popEntity,PopDto[].class));
		return popDtoList;
	}
	
	
}
