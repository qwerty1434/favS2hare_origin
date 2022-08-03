package com.favshare.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favshare.dto.UserCommentIdDto;
import com.favshare.service.LikeCommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pop/likeComment")
public class LikeCommentController {
	
	@Autowired
	private LikeCommentService likeCommentService;
	
	@ApiOperation(value="댓글 좋아요 클릭 시",response=ResponseEntity.class)	
	@PostMapping
	public ResponseEntity addLikeComment(@RequestBody UserCommentIdDto userCommentIdDto) {
		try {
			likeCommentService.insertLike(userCommentIdDto);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity(HttpStatus.BAD_GATEWAY);
		}
	}
	
	@ApiOperation(value="댓글 좋아요 취소 시",response=ResponseEntity.class)	
	@DeleteMapping
	public ResponseEntity deleteLikeComment(@RequestBody UserCommentIdDto userCommentIdDto) {
		try {
			likeCommentService.deleteCommentLike(userCommentIdDto);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity(HttpStatus.BAD_GATEWAY);
		}
	}
}
