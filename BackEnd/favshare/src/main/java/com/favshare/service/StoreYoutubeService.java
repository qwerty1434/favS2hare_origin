package com.favshare.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.YoutubeDto;
import com.favshare.dto.YoutubeUserIdDto;
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

	public void insertBookmark(YoutubeUserIdDto youtubeUserIdDto) {

		insertYoutube(youtubeUserIdDto);

		StoreYoutubeEntity storeYoutubeEntity = new StoreYoutubeEntity();
		UserEntity userEntity = userRepository.findById(youtubeUserIdDto.getUserId()).get();
		YoutubeEntity youtubeEntity = youtubeRepository.findByUrl(youtubeUserIdDto.getYoutubeUrl());

		if (storeYoutubeRepository.isBookmarked(userEntity.getId(), youtubeEntity.getId()) < 1) {

			if (youtubeEntity == null) {
				youtubeEntity = YoutubeEntity.builder().url(youtubeUserIdDto.getYoutubeUrl()).build();
			}
			storeYoutubeEntity = StoreYoutubeEntity.builder().userEntity(userEntity).youtubeEntity(youtubeEntity)
					.build();

			storeYoutubeRepository.save(storeYoutubeEntity);
		}
	}

	public void insertYoutube(YoutubeUserIdDto youtubeUserIdDto) {
		if (youtubeRepository.isDuplicated(youtubeUserIdDto.getYoutubeUrl()) < 1) {

			YoutubeEntity youtubeEntity = YoutubeEntity.builder().url(youtubeUserIdDto.getYoutubeUrl()).build();
			youtubeRepository.save(youtubeEntity);
		}
	}

	public List<YoutubeDto> getYoutubeBookmarkById(int id) {
		UserEntity userEntity;
		userEntity = userRepository.findById(id).get();

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

		storeYoutubeEntity = storeYoutubeRepository.searchByUserIdAndYoutubeId(youtubeUserIdDto.getUserId(),
				youtubeEntity.getId());
		storeYoutubeRepository.deleteById(storeYoutubeEntity.getId());
	}
}
