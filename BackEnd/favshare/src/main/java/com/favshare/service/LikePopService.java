package com.favshare.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.entity.LikePopEntity;
import com.favshare.entity.PopEntity;
import com.favshare.entity.UserEntity;
import com.favshare.entity.YoutubeEntity;
import com.favshare.repository.LikePopRepository;
import com.favshare.repository.PopRepository;
import com.favshare.repository.UserRepository;

@Service
public class LikePopService {

	@Autowired
	private LikePopRepository likePopRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PopRepository popRepository;
	
	public void insertLikePop(Map<String, String> likePopInfo) {
		LikePopEntity likePopEntity = new LikePopEntity();
		
		UserEntity userEntity = userRepository.findById(Integer.parseInt(likePopInfo.get("userId"))).get();
		PopEntity popEntity = popRepository.findById(Integer.parseInt(likePopInfo.get("popId"))).get(); 
		likePopEntity = likePopEntity.builder().userEntity(userEntity).popEntity(popEntity).build();
		
		likePopRepository.save(likePopEntity);
	}
	
	public void deleteLikePop(Map<String, String> likePopInfo) {
		LikePopEntity likePopEntity;
		likePopEntity = likePopRepository.searchByUserIdAndPopId(Integer.parseInt(likePopInfo.get("userId")), Integer.parseInt(likePopInfo.get("popId")));
		likePopRepository.deleteById(likePopEntity.getId());
	}
}
