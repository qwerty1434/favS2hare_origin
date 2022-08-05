package com.favshare.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.favshare.entity.PopEntity;
import com.favshare.entity.YoutubeEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YoutubeDetailDto {

	
	private String url;
	
  	private List<PopDto> popList = new ArrayList<>();		
  	
  	public YoutubeDetailDto(String url) {
  		this.url = url;
//  		this.popList = youtubeEntity.getPopList();
  	}

}
