package com.favshare.controller;

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

import com.favshare.dto.PopDto;
import com.favshare.dto.PopInfoDto;
import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeDto;
import com.favshare.service.LikePopService;
import com.favshare.service.PopService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pop")
public class PopController {

	@Autowired
	private PopService popService;
	
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
	@GetMapping("/info/{popId}")
	public ResponseEntity<PopInfoDto> showPopInfo(@PathVariable("popId") int popId) {
		try {
			PopInfoDto popInfoDto  = popService.getPopInfoById(popId);
			return new ResponseEntity<PopInfoDto>(popInfoDto , HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity<PopInfoDto>(HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation(value = "팝 좋아요", response = ResponseEntity.class)
	@PostMapping("/like")
	public ResponseEntity likePop(@RequestBody Map<String,String> likeInfo) {
		try {
			likePopService.insertLikePop(likeInfo);
			return new ResponseEntity(HttpStatus.OK);  
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation(value = "팝 좋아요 취소", response = ResponseEntity.class)
	@DeleteMapping("/like")
	public ResponseEntity dislikePop(@RequestBody Map<String,String> likeInfo) {
		try {
			likePopService.deleteLikePop(likeInfo);
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
}
