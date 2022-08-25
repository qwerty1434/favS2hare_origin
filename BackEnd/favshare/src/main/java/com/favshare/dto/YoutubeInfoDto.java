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

	private int userId;
	private String url;

	private List<FeedDto> feedList = new ArrayList<>();

	public YoutubeInfoDto(UserEntity userEntity, String url) {
		this.userId = userEntity.getId();
		this.url = url;
	}
}
