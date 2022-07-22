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
@RequestMapping("/Comment")
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@GetMapping("/searchAll")
	@Transactional()
	public String searchAll() {
		String result = commentRepo.findAll().toString();
		System.out.println(result);
		return result;
	}
	
	@GetMapping("/search")
	@Transactional()
	public CommentDto search(Integer id) {
		CommentDto result = new CommentDto();
		return result;
	}
	
	public void insert(CommentDto dto) {
		commentRepo.save(CommentEntity.builder().userId(dto.getUserId()).popId(dto.getPopId()).content(dto.getContent()).createDate(dto.getCreateDate()).isModify(dto.getIsModify()).build());
	}
	
	@GetMapping("/delete")
	@Transactional()
	public void delete(Integer id) {
		commentRepo.deleteById(id);
	}

}
