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

import com.favshare.dto.PopDto;
import com.favshare.dto.UserIdMessageDto;
import com.favshare.dto.UserProfileDto;
import com.favshare.entity.UserEntity;
import com.favshare.service.PopService;
import com.favshare.service.UserService;
import com.favshare.service.YoutubeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private PopService popService;
	
	@Autowired
	private YoutubeService youtubeService;
	
	@Autowired
	private UserService userService;

	@ApiOperation(value="검색어를 입력하면 해당 키워드가 포함된 '팝리스트','동영상 리스트','유저 리스트' 반환")
	@PostMapping
	public ResponseEntity search(@RequestBody UserIdMessageDto userIdMessageDto){
		HashMap<String,List> result = new HashMap<String,List>();
		int userId = userIdMessageDto.getUserId();
		String message = userIdMessageDto.getMessage();
		
		List<PopDto> popList = popService.popDtoListByKeyword(message, userId);
		result.put("pop",popList);
		
		List<String> youtubeList = youtubeService.getUrlByKeyword(message);
		result.put("youtube", youtubeList);
		
		
		List<UserProfileDto> userList = userService.userDtoListByKeyword(message); // userprofiledto정보인 nickname, content, profile사진 3개만 반환함
		result.put("user", userList);

		
		
		return new ResponseEntity(result,HttpStatus.OK);
		
	}
}
