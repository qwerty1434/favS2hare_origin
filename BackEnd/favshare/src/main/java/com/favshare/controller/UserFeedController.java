package com.favshare.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	
	@ApiOperation(value="feed에서 pop 하나 선택 시 pop의 재생 화면 나타내기",response=ResponseEntity.class)	
	@PostMapping("/pop")		
	public ResponseEntity<HashMap<String, Object>> showOnePop(@RequestBody UserPopIdDto userPopIdDto) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int userId = userPopIdDto.getUserId();
		int popId = userPopIdDto.getPopId();

		try {
			PopDto popDto = popService.getPopDtoById(userId, popId);
			UserProfileDto userProfileDto = userService.getUserProfileById(userId);
			
			result.put("popInfo", popDto);
			result.put("userInfo", userProfileDto);
			
			return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<HashMap<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
