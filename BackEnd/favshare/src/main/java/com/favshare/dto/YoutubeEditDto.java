package com.favshare.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.favshare.entity.FeedEntity;
import com.favshare.entity.UserEntity;
import com.favshare.entity.YoutubeEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YoutubeEditDto {
	//userId, youtubeId, startSecond, endSecond, bgm(심화과정), title, content,feedId
	private int userId;
	private int youtubeId;
	private int startSecond;
	private int endSecond; 
	private String title;
	private String content;
	private int feedId;
	
	
	public YoutubeEditDto(UserEntity userEntity, YoutubeEntity youtubeEntity) {
		this.userId = userEntity.getId();
		this.youtubeId = youtubeEntity.getId();
	}


	public YoutubeEditDto(YoutubeEntity youtubeEntity) {
		this.youtubeId = youtubeEntity.getId();
	}
	
	
	
}
