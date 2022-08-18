package com.favshare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeDto;
import com.favshare.dto.YoutubeUserIdDto;
import com.favshare.service.YoutubeService;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {

	@Autowired
	private YoutubeService youtubeService;

	@ApiOperation(value = "사용자에게 맞는 유튜브 리스트", response = List.class)
	@GetMapping("/{userId}")
	public ResponseEntity<String> showYoutubeList(@PathVariable("userId") int userId) {
		// 로그인 안한 경우에는 userId 값이 0으로 넘어온다.
		try {
//			List<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> urlMap = new HashMap<String, Object>();

			String urlList;
			System.out.println("!!!!!!!!!!" + userId);
			if(userId == 0) {
				urlList = youtubeService.getAlgoUrlByNoId();
			}
			else {
				boolean hasInterestIdol = youtubeService.hasInterestIdol(userId);
				if(!hasInterestIdol) {
					urlList = youtubeService.getAlgoUrlByNoId();
				}
				else {
					urlList = youtubeService.getAlgoUrlByUserId(userId);
				}
			}
			
//			for(int i = 0; i < urlList.size(); i++) {
//				urlMap = new HashMap<String, Object>();
//				urlMap.put("youtubeId" , urlList.get(i));
//				result.add(urlMap);
//			}
			
			return new ResponseEntity<String>(urlList, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "유튜브 관련 정보", response = YoutubeDetailDto.class)
	@PostMapping("/detail")
	public ResponseEntity<YoutubeDetailDto> showYoutubeDetil(@RequestBody YoutubeUserIdDto youtubeUserIdDto) {
		try {
			YoutubeDetailDto youtubeDetailDto = youtubeService.getDetailByUrl(youtubeUserIdDto);
			return new ResponseEntity<YoutubeDetailDto>(youtubeDetailDto, HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity<YoutubeDetailDto>(HttpStatus.BAD_REQUEST); 
		}

	}
	
}
