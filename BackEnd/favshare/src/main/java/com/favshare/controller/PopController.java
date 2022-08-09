package com.favshare.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.FollowDto;
import com.favshare.dto.FriendFeedDto;
import com.favshare.dto.IdolDto;
import com.favshare.dto.PopDto;
import com.favshare.dto.PopInfoDto;
import com.favshare.dto.SongDto;
import com.favshare.dto.UserPopIdDto;
import com.favshare.dto.UserProfileDto;
import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeDto;
import com.favshare.entity.FollowEntity;
import com.favshare.entity.PopEntity;
import com.favshare.service.LikePopService;
import com.favshare.service.PopService;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pop")
public class PopController {

	@Autowired
	private PopService popService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LikePopService likePopService;
	
	@ApiOperation(value = "사용자에게 맞는 팝 리스트", response = List.class)
	@GetMapping("/{userId}")
	public ResponseEntity<List<PopDto>> showPopList(@PathVariable("userId") int userId) {

		//알고리즘을 통해 팝 리스트 반환 
		return null;
	}
	
	@ApiOperation(value = "팝 시청시 조회수 증가", response = ResponseEntity.class)
	@PutMapping("/detail/{popId}")
	public ResponseEntity modifyPopView(@PathVariable("popId") int popId) {
		try {
			popService.updatePopView(popId);
			return new ResponseEntity(HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		}
		
	}
	
	@ApiOperation(value = "팝 정보", response = PopInfoDto.class)
	@PostMapping("/info")
	public ResponseEntity<HashMap<String,Object>> showPopInfo(@RequestBody UserPopIdDto userPopIdDto) {
		try {
			PopInfoDto popInfoDto  = popService.getPopInfoById(userPopIdDto.getPopId());
			UserProfileDto userProfileDto = userService.getUserProfileById(userPopIdDto.getUserId());
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("popInfoDto", popInfoDto);
			map.put("userProfileDto", userProfileDto);
			
			
			return new ResponseEntity<HashMap<String,Object>>(map , HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity<HashMap<String,Object>>(HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation(value = "팝 좋아요", response = ResponseEntity.class)
	@PostMapping("/like")
	public ResponseEntity likePop(@RequestBody UserPopIdDto userPopIdDto) {
		try {
			likePopService.insertLikePop(userPopIdDto);
			return new ResponseEntity(HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation(value = "팝 좋아요 취소", response = ResponseEntity.class)
	@DeleteMapping("/like")
	public ResponseEntity dislikePop(@RequestBody UserPopIdDto userPopIdDto) {
		try {
			likePopService.deleteLikePop(userPopIdDto);
			return new ResponseEntity(HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation(value = "유튜브 원본 영상에 만들어진 팝 리스트", response = PopInfoDto.class)
	@GetMapping("/youtube/{popId}")
	public ResponseEntity<List<PopDto>> showOrginYoutubePopInfo(@PathVariable("popId") int popId) {
		try {
			PopInfoDto popInfoDto  = popService.getPopInfoById(popId);
			List<PopDto> popList = popService.getPopListById(popInfoDto.getYoutubeId());
			return new ResponseEntity<List<PopDto>>(popList, HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity<List<PopDto>>(HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation(value = "팝 삭제 - 한개, 여러개 모두 삭제 가능", response = PopInfoDto.class)
	@DeleteMapping
	public ResponseEntity deletePop(@RequestBody List<Integer> popIdList) {
		try {
			popService.deletePop(popIdList);
			return new ResponseEntity(HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation(value = "친구피드 탭을 클릭했을 때", response = PopInfoDto.class)
	@GetMapping("/friend/{userId}")
	public ResponseEntity<List<FriendFeedDto>> showFriendList(@PathVariable int userId) {
		// 친구숫자 세기 (친구: 내가 팔로우 한 사람)
		List<FriendFeedDto> friendsPopList = userService.getFollowingList(userId);
		
		
		if(friendsPopList.size()==0) { // 친구가 없으면 -> 알고리즘으로 뿌려주기
			System.out.println("난 친구가 없어!!!!!!!!!!!!!!!!!!!!!!!!!");
			List<FriendFeedDto> result = new ArrayList<FriendFeedDto>();

			// 1. 나를 팔로우 한 사람
			List<UserProfileDto> UserProfileDtoList = userService.getFollowerList(userId);
			for (int i = 0; i < UserProfileDtoList.size(); i++) {
				int id = UserProfileDtoList.get(i).getId();
				// id로 이사람의 userProfileDto 가져오기
				UserProfileDto userProfileDto = userService.getUserProfileById(id);
				// id로 이사람의 popDtoList가져오기
				List<PopDto> popDtoList = popService.popDtoListByUserId(id);
				// popDtoList를 for문으로 돌면서
				for (int j = 0; j < popDtoList.size(); j++) {
					// new FriendFeedDto한걸 result에 넣어주기
					result.add(new FriendFeedDto(userProfileDto,popDtoList.get(j)));					
				}
			}
							
			// 3. 관심사가 비슷한 사람
			// 내 관심사
			List<IdolDto>myInterestIdolList = userService.getInterestIdolList(userId);
			List<SongDto>myInterestSongList = userService.getInterestSongList(userId);
			
			// 나와 관심사가 동일한 사람
			HashSet<Integer> similarPersonId = new HashSet<Integer>();
			
			for (int i = 0; i < myInterestIdolList.size(); i++) {
				int idolId = myInterestIdolList.get(i).getId();
				List<Integer> similarIdolInterest = popService.findSimilarSongInterst(userId, idolId);
				for (int j = 0; j < similarIdolInterest.size(); j++) {
					similarPersonId.add(similarIdolInterest.get(j));
				}
			}

			for (int i = 0; i < myInterestSongList.size(); i++) {
				int idolId = myInterestSongList.get(i).getId();
				List<Integer> similarSongInterest = popService.findSimilarSongInterst(userId, idolId);
				for (int j = 0; j < similarSongInterest.size(); j++) {
					similarPersonId.add(similarSongInterest.get(j));
				}
			}

			Iterator<Integer> iter = similarPersonId.iterator();
			while(iter.hasNext()) {
				int id = iter.next();
				// id로 이사람의 userProfileDto 가져오기
				UserProfileDto userProfileDto = userService.getUserProfileById(id);
				// id로 이사람의 popDtoList가져오기
				List<PopDto> popDtoList = popService.popDtoListByUserId(id);
				// popDtoList를 for문으로 돌면서
				for (int j = 0; j < popDtoList.size(); j++) {
					// new FriendFeedDto한걸 result에 넣어주기
					result.add(new FriendFeedDto(userProfileDto,popDtoList.get(j)));					
				}

			}
			return new ResponseEntity<List<FriendFeedDto>>(result,HttpStatus.OK);
			
			// 4. 랜덤
		} else { // 친구가 있으면 -> 내 친구들의 팝들 최신순으로 보여주기

			// [[친구1의 팝1,친구1의 팝2],[친구2의 팝1],[친구3의 팝1]] 을 [친구1의 팝1, 친구2의 팝2, 친구2의 팝1, 친구3의 팝1]로 변경
			friendsPopList.sort(new Comparator<FriendFeedDto>() {
				@Override
				public int compare(FriendFeedDto o1, FriendFeedDto o2) {
					return o2.getPopDto().getCreateDate().compareTo(o1.getPopDto().getCreateDate());
				}});
			return new ResponseEntity<List<FriendFeedDto>>(friendsPopList,HttpStatus.OK);			
		}

		
		
	}
}
