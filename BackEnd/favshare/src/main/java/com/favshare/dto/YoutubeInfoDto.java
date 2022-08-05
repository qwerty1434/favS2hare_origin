package com.favshare.dto;

import java.util.ArrayList;
import java.util.List;

import com.favshare.entity.FeedEntity;
import com.favshare.entity.UserEntity;
import com.favshare.entity.YoutubeEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YoutubeInfoDto {

	//youtubeId, youtubeUrl,피드 리스트{해당 유저가 보유한 피드정보(Id)}
	private int userId;
	private String url;
	
	private List<FeedDto> feedList = new ArrayList<>();
	
	public YoutubeInfoDto(UserEntity userEntity, String url) {
		this.userId = userEntity.getId();
		this.url = url;
//		this.feedList = userEntity.getFeedList();
	}
}
