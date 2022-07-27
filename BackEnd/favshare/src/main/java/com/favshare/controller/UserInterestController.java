package com.favshare.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.IdolDto;
import com.favshare.dto.SongDto;
import com.favshare.entity.IdolEntity;
import com.favshare.entity.SongEntity;
import com.favshare.service.IdolService;
import com.favshare.service.SongService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/interest")
public class UserInterestController {
	@Autowired
	IdolService idolService;

	@Autowired
	SongService songService;

	@Autowired
	private ModelMapper modelMapper;

	@ApiOperation(value = "아직 미구현", response = ResponseEntity.class)
	@PostMapping
	public void saveInterest() {

	}

	@ApiOperation(value = "모든 노래 정보 반환", response = ResponseEntity.class)
	@GetMapping("/song")
	public ResponseEntity<List<SongDto>> showSong() {
		try {
			List<SongEntity> SongEntityList = songService.getSongList();
			List<SongDto> songDtoList = Arrays.asList(modelMapper.map(SongEntityList, SongDto[].class));
			return new ResponseEntity<List<SongDto>>(songDtoList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<SongDto>>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "이름으로 아이돌 찾기", response = ResponseEntity.class)
	@GetMapping("/findIdol/{message}")
	public ResponseEntity<IdolDto> findIdol(@PathVariable("message") String message) {
		try {
			IdolEntity idolEntity = idolService.getIdolByName(message);
			IdolDto result = new IdolDto(idolEntity);
			return new ResponseEntity<IdolDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<IdolDto>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "모든 아이돌 정보 반환", response = ResponseEntity.class)
	@GetMapping("/idol")
	public ResponseEntity<List<IdolDto>> showIdol() {
		try {
			List<IdolEntity> idolEntityList = idolService.getIdolList();
			List<IdolDto> idolDtoList = Arrays.asList(modelMapper.map(idolEntityList, IdolDto[].class));
			return new ResponseEntity<List<IdolDto>>(idolDtoList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<IdolDto>>(HttpStatus.BAD_REQUEST);
		}
	}
}
