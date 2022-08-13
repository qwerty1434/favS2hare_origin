package com.favshare.controller;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.favshare.dto.FeedPopIdDto;
import com.favshare.dto.FeedUserIdDto;
import com.favshare.dto.IdFeedImageUrlDto;
import com.favshare.dto.IdNameDto;
import com.favshare.service.FeedService;
import com.favshare.service.PopInFeedService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedService;
	
	@Autowired
	private PopInFeedService popInFeedService;
	
	
	// Post -> PathVariable 사용 가능한가?? (일단 만들어두긴 했다.)
	@ApiOperation(value="Feed 설정 화면 - 피드 추가하기",response=ResponseEntity.class)	
	@PostMapping("/{userId}")	
	public ResponseEntity<?> addNewFeed(@PathVariable("userId") int userId) {
		try {
			feedService.insertFeed(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@ApiOperation(value="Feed 설정 화면 - 피드 삭제",response=ResponseEntity.class)	
	@DeleteMapping("/{feedId}")
	public ResponseEntity<?> deleteFeed(@PathVariable("feedId") int id) {
		try {
			feedService.deleteFeed(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
		
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		}
	
	}
	
	@ApiOperation(value="Feed 설정 화면 - 피드이름 수정",response=ResponseEntity.class)	
	@PutMapping("/name")
	public ResponseEntity<?> changeFeedName(@RequestBody IdNameDto idNameDto) {
      
		try {
			feedService.updateFeedName(idNameDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

	}
	
	@ApiOperation(value="Feed 설정 화면 - 피드 이미지 변경",response=ResponseEntity.class)	
	@PutMapping("/image")
	public ResponseEntity<?> changeFeedImage(@RequestBody IdFeedImageUrlDto idFeedImageUrlDto) {
		
		try {
			feedService.updateFeedImage(idFeedImageUrlDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

	}
	
	// pop이랑 popInfeed랑 연관되어있음.
	@ApiOperation(value="Feed 설정 화면 - 피드 내 pop 추가",response=ResponseEntity.class)	
	@PostMapping("/pop")
	public ResponseEntity<?> addPopInFeed(@RequestBody List<FeedPopIdDto> feedPopIdDto) {
		try {
			popInFeedService.insertPopInFeed(feedPopIdDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@ApiOperation(value="Feed 설정 화면 - 피드 내 pop 삭제",response=ResponseEntity.class)	
	@DeleteMapping("/pop")
	public ResponseEntity<?> deletePopInFeed(@RequestBody FeedPopIdDto feedPopIdDto) {
		try {
			popInFeedService.deletePopInFeed(feedPopIdDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
 

	}

	
	@ApiOperation(value="Feed 설정 화면 - 대표 피드 설정",response=ResponseEntity.class)	
	@PutMapping("/star")
	public ResponseEntity<?> setFirstFeed(@RequestBody FeedUserIdDto feedUserIdDto) {
		try {
			feedService.updateFirstFeed(feedUserIdDto);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	

}
