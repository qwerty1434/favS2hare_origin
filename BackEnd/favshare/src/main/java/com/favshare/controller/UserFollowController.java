package com.favshare.controller;

import java.util.ArrayList;

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

import com.favshare.dto.FollowDto;
import com.favshare.dto.input.FromUserToUserDto;
import com.favshare.entity.FollowEntity;
import com.favshare.service.FollowService;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/follow")
public class UserFollowController {

	@Autowired
	private FollowService followService;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "내가 팔로우 하는 사람(팔로잉)", response = ResponseEntity.class)
	@GetMapping("/from/{userId}")
	public ResponseEntity<List<FollowDto>> showFollower(@PathVariable("userId") int userId) {
		try {
			List<FollowDto> followEntityList = followService.getFollowingById(userId);

			return new ResponseEntity<List<FollowDto>>(followEntityList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<FollowDto>>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "나를 팔로우 하는 사람(팔로워)", response = ResponseEntity.class)
	@GetMapping("to/{userId}")
	public ResponseEntity<List<FollowDto>> showFollowing(@PathVariable("userId") int userId) {
		try {
			List<FollowDto> followEntityList = followService.getFollowerById(userId);

			return new ResponseEntity<List<FollowDto>>(followEntityList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<FollowDto>>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "from이 to를 팔로우 함", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity<?> addFollow(@RequestBody FromUserToUserDto fromUserToUserDto) {
		try {
			// 프론트에서 유저 id를 알고 있나요?
			int fromUserId = fromUserToUserDto.getFromUserId();
			int toUserId = fromUserToUserDto.getToUserId();
			followService.insertFollow(fromUserId, toUserId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "팔로우 취소 버튼 - 내가 팔로우 하는 사람(팔로잉)을 삭제", response = ResponseEntity.class)
	@DeleteMapping("/from")
	public void deleteFollower(@RequestBody FromUserToUserDto fromUserToUserDto) {
		int fromUserId = fromUserToUserDto.getFromUserId();
		int toUserId = fromUserToUserDto.getToUserId();
		followService.DeleteFollowById(fromUserId, toUserId);
	}

	// 이 api가 조금 헷갈린다. => 같이 봐주면 좋을 것 같음
	@ApiOperation(value = "삭제 버튼 - 나를 팔로우 하는 사람(팔로워)을 삭제", response = ResponseEntity.class)
	@DeleteMapping("/to")
	public void deleteFollowing(@RequestBody FromUserToUserDto fromUserToUserDto) {
		int fromUserId = fromUserToUserDto.getFromUserId();
		int toUserId = fromUserToUserDto.getToUserId();
		followService.DeleteFollowById(fromUserId, toUserId);
	}
}