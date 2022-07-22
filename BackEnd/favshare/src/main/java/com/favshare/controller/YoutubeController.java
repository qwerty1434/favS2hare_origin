package com.favshare.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.YoutubeDto;
import com.favshare.entity.YoutubeEntity;
import com.favshare.repository.YoutubeRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/youtube")
public class YoutubeController {

	@Autowired
	private YoutubeRepository youtubeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@ApiOperation(value = "사용자 정보에 맞는 모든 유튜브 정보를 반환한다.", response = List.class)
	@GetMapping("/{userId}")
	public ResponseEntity<List<YoutubeDto>> getYoutubeList(@PathVariable int userId) {
		List<YoutubeEntity> youtubeEntityList = youtubeRepository.findAll();
		//entity list -> dto list변환 방법
//		List<YoutubeDto> youtubeDtoList = youtubeEntityList.stream().map(youtubeEntity -> modelMapper.map(youtubeEntity, YoutubeDto.class)).collect(Collectors.toList());
		List<YoutubeDto> youtubeDtoList = Arrays.asList(modelMapper.map(youtubeEntityList, YoutubeDto[].class));
		return new ResponseEntity<>(youtubeDtoList, HttpStatus.OK);
	}

	@ApiOperation(value = "선택한 유튜브 정보를 반환한다.", response = List.class)
	@GetMapping("/detail/{youtubeId}")
	public ResponseEntity<YoutubeDto> getYoutube(@PathVariable int youtubeId) {
		YoutubeEntity youtubeEntity = youtubeRepository.findById(youtubeId);
		//entity -> dto 변환 방법
		YoutubeDto youtubeDto = modelMapper.map(youtubeEntity, YoutubeDto.class);
		return new ResponseEntity<>(youtubeDto, HttpStatus.OK);
	}
}
