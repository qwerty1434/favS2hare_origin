package com.favshare.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YoutubeDetailDto {

	private String url;
	private boolean isBookmarked;
	private List<PopDto> popList = new ArrayList<>();

	public YoutubeDetailDto(String url, boolean isBookmarked) {
		this.url = url;
		this.isBookmarked = isBookmarked;
	}

}
