package com.favshare.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.FeedUserIdDto;
import com.favshare.dto.PopDto;
import com.favshare.dto.PopInfoDto;
import com.favshare.dto.PopUserYoutubeDto;
import com.favshare.dto.UserPopIdDto;
import com.favshare.dto.UserPopYoutubeIdDto;
import com.favshare.dto.UserProfileDto;
import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeEditDto;
import com.favshare.entity.UserEntity;
import com.favshare.entity.YoutubeEntity;
import com.favshare.service.FeedService;
import com.favshare.service.PopService;
import com.favshare.service.UserService;
import com.favshare.service.YoutubeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user/feed")
public class UserFeedController {
	
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private PopService popService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private YoutubeService youtubeService;
	
	@ApiOperation(value="원하는 피드를 선택했을 때 해당 피드에 있는 pops 목록을 보여줍니다",response=ResponseEntity.class)	
	@GetMapping
	public void func1(@RequestBody FeedUserIdDto feedUserIdDto) {
		// popsinfeedid
		// pops리스트
		// 동영상 리스트

	}
	
	@ApiOperation(value="",response=ResponseEntity.class)	
	@PostMapping("/pop")		
	public List<Object> func2(@RequestBody UserPopYoutubeIdDto userPopYoutubeIdDto) {
		int userId = userPopYoutubeIdDto.getUserId();
		int popId = userPopYoutubeIdDto.getPopId();
		int youtubeId = userPopYoutubeIdDto.getPopId();
		// pop: popId, title, startSecond, endSecond -> popDto
		// user: userId, nickname, userPicture -> userProfileDto 
		// youtube: youtubeId, youtubeName, youtubeUrl -> YoutubeEditDto
		// -> youtubeName이라는 변수가 어디에도 존재하지 않음
		PopDto popDto = popService.getPopDtoById(popId);
		UserProfileDto userProfileDto = userService.getUserProfileById(userId);
//		YoutubeEditDto youtubeEditDto = youtubeService.getYoutubeEditDtoById(youtubeId);
		List<Object> lst = new ArrayList<Object>();
		lst.add(popDto);
		lst.add(userProfileDto);
//		lst.add(youtubeEditDto);
		return lst;
		
	}
	
	
}
