package com.favshare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.CommentDto;
import com.favshare.entity.CommentEntity;
import com.favshare.repository.CommentRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@ApiOperation(value = "모든 댓글 정보를 반환합니다", response = CommentEntity.class)
	@GetMapping("/searchAll")
	@Transactional()
	public ResponseEntity<List<CommentEntity>> searchAll() {
		List<CommentEntity> result = commentRepository.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "id 값으로 찾은 특정 댓글 정보를 반환합니다", response = CommentEntity.class)
	@GetMapping("/search")
	@Transactional()
	public ResponseEntity<CommentDto> search(Integer id) {
		CommentEntity entityValue = commentRepository.search(id);
		CommentDto result = new CommentDto(entityValue);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/insert")
	@Transactional()
	public void insert(CommentDto dto) {
		CommentEntity result = CommentEntity.builder().userId(dto.getUserId()).popId(dto.getPopId())
				.content(dto.getContent()).createDate(dto.getCreateDate()).isModify(dto.getIsModify()).build();
		commentRepository.save(result);
	}

	@GetMapping("/delete")
	@Transactional()
	public void delete(Integer id) {
		commentRepository.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
