package com.favshare.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.IdolDto;
import com.favshare.dto.InterestIdolDto;
import com.favshare.dto.InterestSaveDto;
import com.favshare.dto.InterestSongDto;
import com.favshare.dto.SongDto;
import com.favshare.entity.IdolEntity;
import com.favshare.entity.SongEntity;
import com.favshare.service.IdolService;
import com.favshare.service.InterestIdolService;
import com.favshare.service.InterestSongService;
import com.favshare.service.SongService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/interest")
public class UserInterestController{
	@Autowired
	private IdolService idolService;

	@Autowired
	private SongService songService;
	
	@Autowired
	private InterestIdolService interestIdolService;
	
	@Autowired
	private InterestSongService interestSongService;

	@Autowired
	private ModelMapper modelMapper;

	@ApiOperation(value = "유저가 선택한 취향 저장", response = ResponseEntity.class)
	@PostMapping
	public void saveInterest(InterestSaveDto interestSaveDto) { // {userId:1,Idol:{1,2,3},Song:{1,2,3}}
		int userId = interestSaveDto.getUserId();
		List<Integer> IdolList = interestSaveDto.getIdolList();
		List<Integer> SongList = interestSaveDto.getSongList();
		for (int i = 0; i < SongList.size(); i++) {		
			interestSongService.addSongFavorite(userId,SongList.get(i));
		}
		for (int i = 0; i < IdolList.size(); i++) {		
			interestIdolService.addIdolFavorite(userId,IdolList.get(i));
		}
		
	}

	@ApiOperation(value = "유저의 선호정보 반환", response = ResponseEntity.class)
	@GetMapping("/interestList/{userId}")
	public ResponseEntity<InterestSaveDto> showInterest(@PathVariable("userId") int userId) {
		try {
			List<Integer> SongList = interestSongService.findSongListById(userId);
			List<Integer> IdolList = interestIdolService.findIdolListById(userId);
			InterestSaveDto interestSaveDto = new InterestSaveDto(userId,IdolList,SongList);
			return new ResponseEntity<InterestSaveDto>(interestSaveDto, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@ApiOperation(value = "유저의 선호정보 변경", response = ResponseEntity.class)
	@PutMapping("/interestList/{userId}")
	public void changeInterest(InterestSaveDto interestSaveDto) {
		deleteInterest(interestSaveDto.getUserId()); // 기존의 선호정보 삭제
		saveInterest(interestSaveDto); // 유저가 입력한 선호정보로 변경
	}
	
	@ApiOperation(value = "유저의 선호정보 삭제", response = ResponseEntity.class)
	@DeleteMapping("/interestList/{userId}")
	public void deleteInterest(int userId) {
		interestSongService.deleteByUserId(userId);
		interestIdolService.deleteByUserId(userId);		
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
	@GetMapping("/findIdol/{name}")
	public ResponseEntity<List<IdolDto>> findIdol(@PathVariable("name") String name) {
		try {
			List<IdolEntity> idolEntityList = idolService.getIdolContains(name);
			List<IdolDto> idolDtoList = Arrays.asList(modelMapper.map(idolEntityList, IdolDto[].class));
			return new ResponseEntity<List<IdolDto>>(idolDtoList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<IdolDto>>(HttpStatus.BAD_REQUEST);
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
