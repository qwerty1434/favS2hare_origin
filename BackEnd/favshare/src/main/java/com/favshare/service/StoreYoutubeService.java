package com.favshare.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.YoutubeBookmarkDto;
import com.favshare.dto.YoutubeDto;
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
	
	//교차엔티티에 값을 넣어줘야함
	public void insertBookmark(Map<String, String> bookmarkInfo) {
		StoreYoutubeEntity storeYoutubeEntity = new StoreYoutubeEntity();
		
		UserEntity userEntity = userRepository.findById(Integer.parseInt(bookmarkInfo.get("userId"))).get();
		YoutubeEntity youtubeEntity = youtubeRepository.findById(Integer.parseInt(bookmarkInfo.get("youtubeId"))).get(); 
		storeYoutubeEntity = storeYoutubeEntity.builder().userEntity(userEntity).youtubeEntity(youtubeEntity).build();
		
		storeYoutubeRepository.save(storeYoutubeEntity);
	}
	
	public List<YoutubeDto> getYoutubeBookmarkById(int id) {
		UserEntity userEntity;
		userEntity = userRepository.findById(id).get();
		//여기 확인
//		System.out.println(userEntity.getStoreYoutubeList().toString());
		List<StoreYoutubeEntity> storeYoutubeList = userEntity.getStoreYoutubeList();
		
		List<YoutubeDto> result = new ArrayList<YoutubeDto>();
		for (int i = 0; i < storeYoutubeList.size(); i++) {
			result.add(new YoutubeDto(storeYoutubeList.get(i).getYoutubeEntity()));
		}
		return result;
	}
	
	public void deleteYoutubeBookMarkById(HashMap<String, String> bookmarkInfo) {
		StoreYoutubeEntity storeYoutubeEntity;
		storeYoutubeEntity = storeYoutubeRepository.searchByUserIdAndYoutubeId(Integer.parseInt(bookmarkInfo.get("userId")), Integer.parseInt(bookmarkInfo.get("youtubeId")));
		storeYoutubeRepository.deleteById(storeYoutubeEntity.getId());
	}
}
