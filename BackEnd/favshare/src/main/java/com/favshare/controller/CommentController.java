package com.favshare.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.CommentDto;
import com.favshare.entity.CommentEntity;
import com.favshare.repository.CommentRepository;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@GetMapping("/searchAll")
	@Transactional()
	public String searchAll() {
		String result = commentRepository.findAll().toString();
		System.out.println(result);
		return result;
	}
	
	@GetMapping("/search")
	@Transactional()
	public CommentDto search(Integer id) {
		CommentEntity entityValue = commentRepository.search(id);
		CommentDto result = new CommentDto(entityValue.getId(),entityValue.getUserId(),entityValue.getPopId(),entityValue.getContent(),entityValue.getCreateDate(),entityValue.getIsModify());
		return result;
	}
	
	@GetMapping("/insert")
	@Transactional()
	public void insert(CommentDto dto) {
		CommentEntity result = CommentEntity.builder().userId(dto.getUserId()).popId(dto.getPopId()).content(dto.getContent()).createDate(dto.getCreateDate()).isModify(dto.getIsModify()).build();
		commentRepository.save(result);
	}
	
	@GetMapping("/delete")
	@Transactional()
	public void delete(Integer id) {
		commentRepository.deleteById(id);
	}

}
