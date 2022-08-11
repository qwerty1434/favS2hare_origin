package com.favshare.controller;

import java.util.Arrays;

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
import com.favshare.dto.InterestSaveDto;
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
	public void saveInterest(@RequestBody InterestSaveDto interestSaveDto) { // {userId:1,Idol:{1,2,3},Song:{1,2,3}} 이런 형식으로 값이 들어왔으면 좋겠음
		int userId = interestSaveDto.getUserId();
		System.out.println(interestSaveDto.toString());
		List<Integer> IdolList = interestSaveDto.getIdolList();
		List<Integer> SongList = interestSaveDto.getSongList();
		for (int i = 0; i < SongList.size(); i++) {		
			interestSongService.addSongFavorite(userId,SongList.get(i));
		}
		for (int i = 0; i < IdolList.size(); i++) {		
			interestIdolService.addIdolFavorite(userId,IdolList.get(i));
		}
		
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
