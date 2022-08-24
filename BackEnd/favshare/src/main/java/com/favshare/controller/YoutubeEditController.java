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

import com.favshare.dto.YoutubeDetailDto;
import com.favshare.dto.YoutubeDto;
import com.favshare.dto.YoutubeEditPopDto;
import com.favshare.dto.YoutubeInfoDto;
import com.favshare.dto.YoutubeUserIdDto;
import com.favshare.service.PopService;
import com.favshare.service.YoutubeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/youtube/edit")
public class YoutubeEditController {

	@Autowired
	private YoutubeService youtubeService;

	@Autowired
	private PopService PopService;

	@ApiOperation(value = "유튜브 편집 화면 정보", response = List.class)
	@PostMapping("/info")
	public ResponseEntity<YoutubeInfoDto> showYoutubeEdit(@RequestBody YoutubeUserIdDto youtubeUserIdDto) {
		try {
			YoutubeInfoDto youtubeEditDto = youtubeService.getEditInfoByUrl(youtubeUserIdDto);
			return new ResponseEntity<YoutubeInfoDto>(youtubeEditDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<YoutubeInfoDto>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "유튜브 편집 정보 저장", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity saveYoutubeEdit(@RequestBody YoutubeEditPopDto youtubeEditPopDto) {
		try {
			PopService.insertPop(youtubeEditPopDto);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
