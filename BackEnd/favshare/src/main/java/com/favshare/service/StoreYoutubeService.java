package com.favshare.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.YoutubeDto;
import com.favshare.dto.input.YoutubeUserIdDto;
import com.favshare.entity.StoreYoutubeEntity;
import com.favshare.entity.UserEntity;
import com.favshare.entity.YoutubeEntity;
import com.favshare.repository.StoreYoutubeRepository;
import com.favshare.repository.UserRepository;
import com.favshare.repository.YoutubeRepository;

@Service
public class StoreYoutubeService {

	@Autowired
	private StoreYoutubeRepository storeYoutubeRepository;

	@Autowired
	private YoutubeRepository youtubeRepository;

	@Autowired
	private UserRepository userRepository;

	// 교차엔티티에 값을 넣어줘야함
	public void insertBookmark(YoutubeUserIdDto youtubeUserIdDto) {
		UserEntity userEntity = userRepository.findById(youtubeUserIdDto.getUserId()).orElseThrow(() -> new NullPointerException());
		YoutubeEntity youtubeEntity = youtubeRepository.findByUrl(youtubeUserIdDto.getYoutubeUrl());
		if (youtubeEntity == null) {
			youtubeEntity = YoutubeEntity.builder().url(youtubeUserIdDto.getYoutubeUrl()).build();
			youtubeRepository.save(youtubeEntity);
		}
		StoreYoutubeEntity storeYoutubeEntity = StoreYoutubeEntity.builder().userEntity(userEntity).youtubeEntity(youtubeEntity).build();

		storeYoutubeRepository.save(storeYoutubeEntity);
	}

	public List<YoutubeDto> getYoutubeBookmarkById(int id) {
		UserEntity userEntity;
		userEntity = userRepository.findById(id).orElseThrow(() -> new NullPointerException());
		List<StoreYoutubeEntity> storeYoutubeList = userEntity.getStoreYoutubeList();
		List<YoutubeDto> result = new ArrayList<YoutubeDto>();
		for (int i = 0; i < storeYoutubeList.size(); i++) {
			result.add(new YoutubeDto(storeYoutubeList.get(i).getYoutubeEntity()));
		}
		return result;
	}

	public void deleteYoutubeBookMarkById(YoutubeUserIdDto youtubeUserIdDto) {
		StoreYoutubeEntity storeYoutubeEntity;
		YoutubeEntity youtubeEntity = youtubeRepository.findByUrl(youtubeUserIdDto.getYoutubeUrl());
		System.out.println(youtubeUserIdDto.getYoutubeUrl());
		storeYoutubeEntity = storeYoutubeRepository.searchByUserIdAndYoutubeId(youtubeUserIdDto.getUserId(),
				youtubeEntity.getId());
		storeYoutubeRepository.deleteById(storeYoutubeEntity.getId());
	}
}
