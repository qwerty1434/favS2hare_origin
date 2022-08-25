package com.favshare.dto;

import java.util.List;

import com.favshare.entity.YoutubeEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YoutubDetailDto {
	private int id;
	private String url;

	private List<PopDto> popList;

	public YoutubDetailDto(YoutubeEntity youtubeEntity) {
		this.id = youtubeEntity.getId();
		this.url = youtubeEntity.getUrl();
	}

}
