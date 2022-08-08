package com.favshare.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.FeedDto;
import com.favshare.dto.FeedUserIdDto;
import com.favshare.dto.FollowDto;
import com.favshare.dto.FollowForFollowDto;
import com.favshare.dto.PopDto;
import com.favshare.dto.PopInFeedDto;
import com.favshare.dto.UserProfileDto;
import com.favshare.service.PopService;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private PopService popService;

	@ApiOperation(value = "프로필 보기 윗부분", response = ResponseEntity.class)
	@GetMapping("/{userId}")
	public ResponseEntity<UserProfileDto> showProfilehead(@PathVariable("userId") int userId) {
		try {

			int popCount = popService.getPopCount(userId);
			int[] temp = userService.countFollow(userId);
			int followerNum = temp[0];
			int followingNum = temp[1];
			List<FeedDto> feedDtoList = userService.getFeedList(userId);

			UserProfileDto userProfileDto = userService.getUserProfileById(userId);

			userProfileDto.setPopCount(popCount);
			userProfileDto.setFollowerNum(followerNum);
			userProfileDto.setFollowingNum(followingNum);
			userProfileDto.setFeedList(feedDtoList);

			return new ResponseEntity<UserProfileDto>(userProfileDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<UserProfileDto>(HttpStatus.BAD_REQUEST);
		}

	}

	// feedController에 있어야 하는건가?
	@ApiOperation(value = "프로필 보기 아래 부분 - 피드별 poplist 출력 ", response = ResponseEntity.class)
	@PostMapping("/feed")
	public ResponseEntity<List<PopDto>> showPopInFeed(@RequestBody FeedUserIdDto feedUserIdDto) {
		List<PopDto> popInFeedDtoList;
		if (feedUserIdDto.getFeedId() == -1) {
			// feedId가 -1이라는 것은 전체 피드라는 의미입니다.
			popInFeedDtoList = userService.getAllPopList(feedUserIdDto);

		} else { // 전체 피드가 아니라 각각의 피드일때
			popInFeedDtoList = userService.getPopInFeedList(feedUserIdDto.getFeedId());
		}
		return new ResponseEntity<List<PopDto>>(popInFeedDtoList, HttpStatus.OK);

	}

	@ApiOperation(value = "프로필 수정 화면 들어올 시", response = ResponseEntity.class)
	@GetMapping("/edit/{userId}")
	public ResponseEntity<UserProfileDto> showEditProfile(@PathVariable("userId") int userId) {
//		UserProfileDto userProfileDto = userService.getUserProfileById(userId);
		try {

			UserProfileDto userProfileDto = userService.getUserProfileById(userId);

			return new ResponseEntity<UserProfileDto>(userProfileDto, HttpStatus.OK);
		} catch (Exception e) {
			// 왜 되지..???????객체 없이 httpstatus만 반환 가능?
			return new ResponseEntity<UserProfileDto>(HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "프로필 수정", response = ResponseEntity.class)
	@PutMapping
	public ResponseEntity changeProfile(@RequestBody UserProfileDto userProfileDto) {
		try {
			UserProfileDto result = userService.getUserProfileById(userProfileDto.getId());
			result.setNickname(userProfileDto.getNickname());
			result.setContent(userProfileDto.getContent());
			result.setProfileImageUrl(userProfileDto.getProfileImageUrl());

			userService.updateProfile(result);

			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "친구 프로필 보기의 윗부분", response = ResponseEntity.class)
	@PostMapping("/friend")
	public ResponseEntity<HashMap<String, Object>> showFreindProfileHead(
			@RequestBody FollowForFollowDto followForFollowDto) {
		// {UserProfileDto, 맞팔 여부}를 반환해야 됨
		try {

			boolean isFollowForFollow = userService.getFollowForFollow(followForFollowDto.getFromUserId(),
					followForFollowDto.getToUserId());

			int popCount = popService.getPopCount(followForFollowDto.getToUserId());
			int[] temp = userService.countFollow(followForFollowDto.getToUserId());
			int followerNum = temp[0];
			int followingNum = temp[1];
			List<FeedDto> feedDtoList = userService.getFeedList(followForFollowDto.getToUserId());

			UserProfileDto userProfileDto = userService.getUserProfileById(followForFollowDto.getToUserId());

			userProfileDto.setPopCount(popCount);
			userProfileDto.setFollowerNum(followerNum);
			userProfileDto.setFollowingNum(followingNum);
			userProfileDto.setFeedList(feedDtoList);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("friendProfileDto", userProfileDto);
			map.put("isFollowForFollow", isFollowForFollow);

			return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HashMap<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}
}

