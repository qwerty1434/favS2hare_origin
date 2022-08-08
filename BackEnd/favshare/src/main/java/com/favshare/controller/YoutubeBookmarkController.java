package com.favshare.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.UserProfileDto;
import com.favshare.dto.YoutubeBookmarkDto;
import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeDto;
import com.favshare.dto.YoutubeUserIdDto;
import com.favshare.service.StoreYoutubeService;
import com.favshare.service.UserService;
import com.favshare.service.YoutubeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/youtube/bookmark")
public class YoutubeBookmarkController {

	@Autowired
	private YoutubeService youtubeService;
	
	@Autowired
	private StoreYoutubeService storeYoutubeService;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "유튜브 저장", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity storeYoutube(@RequestBody YoutubeUserIdDto youtubeUserIdDto) {
		try {
			storeYoutubeService.insertBookmark(youtubeUserIdDto);
			return new ResponseEntity(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "유튜브 저장 목록 확인", response = YoutubeBookmarkDto.class)
	@GetMapping("/{userId}")
	public ResponseEntity<List<YoutubeDto>> showYoutubeBookmark(@PathVariable("userId") int userId) {
		try {
			List<YoutubeDto> youtubeBookmarkList = storeYoutubeService.getYoutubeBookmarkById(userId);
			return new ResponseEntity<List<YoutubeDto>>(youtubeBookmarkList,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<List<YoutubeDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "유튜브 저장 삭제", response = ResponseEntity.class)
	@DeleteMapping
	public ResponseEntity deleteYoutubeBookMark(@RequestBody YoutubeUserIdDto youtubeUserIdDto) {
		try {
			storeYoutubeService.deleteYoutubeBookMarkById(youtubeUserIdDto);
			return new ResponseEntity(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
