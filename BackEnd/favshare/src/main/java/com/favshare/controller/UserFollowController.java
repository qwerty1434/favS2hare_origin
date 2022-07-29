package com.favshare.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.favshare.dto.FollowDto;
import com.favshare.dto.FromUserToUserDto;
import com.favshare.entity.FollowEntity;
import com.favshare.service.FollowService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/follow")
public class UserFollowController {

	@Autowired	
	private FollowService followService;

	@Autowired
	private ModelMapper modelMapper;

	@ApiOperation(value = "내가 팔로우 하는 사람(팔로워)", response = ResponseEntity.class)
	@GetMapping("/from/{userId}")
	public ResponseEntity<List<String>> showFollower(@PathVariable("userId") int userId) { // 프론트에서 유저의 아이디를 알고 있나요?
		try {			
			List<FollowEntity> followEntityList = followService.getFollowerById(userId);
			
			
			List<FollowDto> followDtoList = Arrays.asList(modelMapper.map(followEntityList, FollowDto[].class));
			
			// 변환방법 찾아보기
			List<String> nickNameList = new ArrayList<String>(); // ArrayList vs LinkedList
			for (int i = 0; i < followDtoList.size(); i++) {
				nickNameList.add(followDtoList.get(i).getToUserEntity().getNickname());
			}
			return new ResponseEntity<List<String>>(nickNameList,HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "나를 팔로우 하는 사람(팔로잉)", response = ResponseEntity.class)
	@GetMapping("to/{userId}")
	public ResponseEntity<List<String>> showFollowing(@PathVariable("userId") int userId) {
		try {
			List<FollowEntity> followEntityList = followService.getFollowingById(userId);
			List<FollowDto> followDtoList = Arrays.asList(modelMapper.map(followEntityList, FollowDto[].class));
			// 변환방법 찾아보기
			List<String> nickNameList = new ArrayList<String>(); // ArrayList vs LinkedList
			for (int i = 0; i < followDtoList.size(); i++) {
				nickNameList.add(followDtoList.get(i).getFromUserEntity().getNickname());
			}
			return new ResponseEntity<List<String>>(nickNameList,HttpStatus.OK);			
		} catch(Exception e) {	
			return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "from이 to를 팔로우 함", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity addFollow(@RequestBody FromUserToUserDto fromUserToUserDto) {
		try {
			// 프론트에서 유저 id를 알고 있나요?
			int fromUserId = fromUserToUserDto.getFromUserId();
			int toUserId = fromUserToUserDto.getToUserId();
			followService.insertFollow(fromUserId, toUserId);
			return new ResponseEntity(HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);			
		}
	}

	@ApiOperation(value = "내가 팔로우 하는 사람(팔로워)을 삭제", response = ResponseEntity.class)
	@DeleteMapping("/from")
	public void deleteFollower(@RequestBody FromUserToUserDto fromUserToUserDto) {
		int fromUserId = fromUserToUserDto.getFromUserId();
		int toUserId = fromUserToUserDto.getToUserId();
		followService.DeleteFollowById(fromUserId, toUserId);
	}
	@ApiOperation(value = "나를 팔로우 하는 사람(팔로잉)을 삭제", response = ResponseEntity.class)
	@DeleteMapping("/to")
	public void deleteFollowing(@RequestBody FromUserToUserDto fromUserToUserDto) {
		int fromUserId = fromUserToUserDto.getFromUserId();
		int toUserId = fromUserToUserDto.getToUserId();
		followService.DeleteFollowById(toUserId, fromUserId);
	}
}