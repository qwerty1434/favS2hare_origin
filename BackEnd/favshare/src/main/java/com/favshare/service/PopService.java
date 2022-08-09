package com.favshare.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.favshare.dto.PopAlgoDto;
import com.favshare.dto.PopDto;
import com.favshare.dto.PopInfoDto;
import com.favshare.dto.UserPopIdDto;
import com.favshare.dto.YoutubeEditPopDto;
import com.favshare.entity.FeedEntity;
import com.favshare.entity.PopEntity;
import com.favshare.entity.PopInFeedEntity;
import com.favshare.entity.UserEntity;
import com.favshare.entity.YoutubeEntity;
import com.favshare.repository.FeedRepository;
import com.favshare.repository.PopInFeedRepository;
import com.favshare.repository.PopRepository;
import com.favshare.repository.ShowPopRepository;
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
	private ShowPopRepository showPopRepository;
	
	
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
	public boolean isWatched(int userId, int popId) {
		if(showPopRepository.searchByUserPopId(userId, popId) == 1) {
			return true;
		}
		else return false;
	}
	
	public List<PopAlgoDto> getCustomPopList(int userId) {
		// 조회수, 좋아요수, 팔로워 수 를 통해 알고리즘 구현 (5 : 3 : 2 의 가중치 부여)
		
		List<PopEntity> popEntityList = popRepository.findAll();
		List<PopAlgoDto> algoList = new ArrayList<PopAlgoDto>();
		double referenceValue; // maxValues의 중간값
		int [] value = new int [3]; // 순서대로 조회수, 좋아요수, 팔로워수
		int [] maxValue = new int [3]; // 
		
		// 조회수, 좋아요수, 팔로워수의 최댓값을 구하고 / log취한 값을 algoList에 저장
		for(int i = 0; i < popEntityList.size(); i++) {
			// 이미 시청한 pop의 경우 알고리즘 리스트에 넣지 않는다.
			if(isWatched(userId, popEntityList.get(i).getId())) continue;
			
			value[0] = (int) (Math.log10(popEntityList.get(i).getViews()) * 100);
			value[1] = (int) (Math.log10(popEntityList.get(i).getLikePopList().size()) * 100);
			value[2] = (int) (Math.log10(popEntityList.get(i).getUserEntity().getToUserEntityList().size()) * 100);
			
			maxValue[0] = Math.max(maxValue[0], value[0]);
			maxValue[1] = Math.max(maxValue[1], value[1]);
			maxValue[2] = Math.max(maxValue[2], value[2]);
			algoList.add(new PopAlgoDto(popEntityList.get(i).getId(), value[0], value[1], value[2], 0));
		}
		
		Arrays.sort(maxValue);
		referenceValue = maxValue[1];
		
		for(int i = 0; i < algoList.size(); i++) {
			value[0] = (int) (algoList.get(i).getViews() * (referenceValue / algoList.get(i).getViews()));
			value[1] = (int) (algoList.get(i).getLikeCount() * (referenceValue / algoList.get(i).getLikeCount()));
			value[2] = (int) (algoList.get(i).getFollowers() * (referenceValue / algoList.get(i).getFollowers()));
			
			int score = (value[0] * 5) + (value[1] * 3) + (value[2] * 2);
			algoList.get(i).setAlgoScore(score);
		}
		
		Collections.sort(algoList, new Comparator<PopAlgoDto>() {

			@Override
			public int compare(PopAlgoDto o1, PopAlgoDto o2) {
				return o2.getAlgoScore() - o1.getAlgoScore();
			}
		
		});
		
//		System.out.println(algoList.toString());
		
		return algoList;
		
	}
	
}
