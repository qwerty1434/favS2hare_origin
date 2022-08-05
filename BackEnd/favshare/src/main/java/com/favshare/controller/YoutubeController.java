package com.favshare.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeDto;
import com.favshare.service.YoutubeService;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
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
	public ResponseEntity<List<String>> showYoutubeList(@PathVariable("userId") int userId) {

		try {
			List<String>urlList = youtubeService.getAlgoUrlByUserId(userId);
			return new ResponseEntity<List<String>>(urlList, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "유튜브 관련 정보", response = YoutubeDetailDto.class)
	@GetMapping("/detail/{youtubeUrl}")
	public ResponseEntity<YoutubeDetailDto> showYoutubeDetil(@PathVariable("youtubeUrl") String youtubeUrl) {
		try {
			YoutubeDetailDto youtubeDetailDto = youtubeService.getDetailByUrl(youtubeUrl);
			return new ResponseEntity<YoutubeDetailDto>(youtubeDetailDto, HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity<YoutubeDetailDto>(HttpStatus.BAD_REQUEST); 
		}

	}
	
}
