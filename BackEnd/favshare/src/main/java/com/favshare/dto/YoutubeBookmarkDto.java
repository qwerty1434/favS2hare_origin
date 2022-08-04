package com.favshare.dto;

import java.util.ArrayList;
import java.util.List;

import com.favshare.entity.StoreYoutubeEntity;
import com.favshare.entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YoutubeBookmarkDto {
	
	private int userId;
	
	private List<StoreYoutubeEntity> storeYoutubeList = new ArrayList<>();
	
	public YoutubeBookmarkDto(UserEntity userEntity) {
		this.userId = userEntity.getId();
		this.storeYoutubeList = userEntity.getStoreYoutubeList();
	}
}
