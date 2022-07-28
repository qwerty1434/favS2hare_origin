package com.favshare.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.UserProfileDto;
import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeDto;
import com.favshare.service.YoutubeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {

	@Autowired
	private YoutubeService youtubeService;
	
	@ApiOperation(value = "사용자에게 맞는 유튜브 리스트", response = List.class)
	@GetMapping("/{userId}")
	public ResponseEntity<List<YoutubeDto>> showYoutubeList(@PathVariable("userId") int userId) {

		//알고리즘을 통해 유튜브 리스트 반환 
		return null;
	}
	
	@ApiOperation(value = "유튜브 관련 정보", response = YoutubeDetailDto.class)
	@GetMapping("/detail/{youtubeId}")
	public ResponseEntity<YoutubeDetailDto> showYoutubeDetil(@PathVariable("youtubeId") int youtubeId) {
		try {
			YoutubeDetailDto youtubeDetailDto = youtubeService.getDetailById(youtubeId);
			return new ResponseEntity<YoutubeDetailDto>(youtubeDetailDto, HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity<YoutubeDetailDto>(HttpStatus.BAD_REQUEST); 
		}
		
	}

}
