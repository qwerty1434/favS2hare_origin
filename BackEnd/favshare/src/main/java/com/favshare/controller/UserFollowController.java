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
import com.favshare.dto.FromUserToUserDto;
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
			List<FollowEntity> followEntityList = followService.getFollowingById(userId);

			List<FollowDto> result = new ArrayList<>();

			for (int i = 0; i < followEntityList.size(); i++) {
				int fromUserId = followEntityList.get(i).getFromUserEntity().getId();
				int toUserId = followEntityList.get(i).getToUserEntity().getId();
				String nickname = followEntityList.get(i).getToUserEntity().getNickname();
				String profileImageUrl = followEntityList.get(i).getToUserEntity().getProfileImageUrl();
				result.add(new FollowDto(fromUserId, toUserId, nickname, false, profileImageUrl));
			}

			return new ResponseEntity<List<FollowDto>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<FollowDto>>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "나를 팔로우 하는 사람(팔로워)", response = ResponseEntity.class)
	@GetMapping("to/{userId}")
	public ResponseEntity<List<FollowDto>> showFollowing(@PathVariable("userId") int userId) {
		try {

			List<FollowEntity> followEntityList = followService.getFollowerById(userId);

			List<FollowDto> result = new ArrayList<>();

			for (int i = 0; i < followEntityList.size(); i++) {
				int fromUserId = followEntityList.get(i).getFromUserEntity().getId();
				int toUserId = followEntityList.get(i).getToUserEntity().getId();
				String nickname = followEntityList.get(i).getFromUserEntity().getNickname();
				boolean isFollowForFollow = userService.getFollowForFollow(userId, fromUserId);
				String profileImageUrl = followEntityList.get(i).getFromUserEntity().getProfileImageUrl();
				result.add(new FollowDto(fromUserId, toUserId, nickname, isFollowForFollow, profileImageUrl));
			}

			return new ResponseEntity<List<FollowDto>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<FollowDto>>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "from이 to를 팔로우 함", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity<?> addFollow(@RequestBody FromUserToUserDto fromUserToUserDto) {
		try {
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

	@ApiOperation(value = "삭제 버튼 - 나를 팔로우 하는 사람(팔로워)을 삭제", response = ResponseEntity.class)
	@DeleteMapping("/to")
	public void deleteFollowing(@RequestBody FromUserToUserDto fromUserToUserDto) {
		int fromUserId = fromUserToUserDto.getFromUserId();
		int toUserId = fromUserToUserDto.getToUserId();
		followService.DeleteFollowById(fromUserId, toUserId);
	}
}