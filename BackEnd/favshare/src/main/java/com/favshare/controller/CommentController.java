package com.favshare.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.favshare.dto.CommentDto;
import com.favshare.dto.UserPopIdDto;
import com.favshare.dto.UserCommentContentIdDto;
import com.favshare.dto.UserCommentIdDto;
import com.favshare.dto.UserPopContentIdDto;
import com.favshare.dto.UserProfileDto;
import com.favshare.entity.CommentEntity;
import com.favshare.service.CommentService;
import com.favshare.service.LikeCommentService;
import com.favshare.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pop/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@Autowired
	private LikeCommentService likeCommentService;

	@ApiOperation(value = "댓글 버튼 클릭시 - 댓글 리스트 반환", response = ResponseEntity.class)
	@PostMapping("/list")
	public ResponseEntity<List<CommentDto>> showCommentList(@RequestBody UserPopIdDto userPopIdDto) {
		int userId = userPopIdDto.getUserId();
		int popId = userPopIdDto.getPopId();

		try {
			List<CommentEntity> commentEntityList = commentService.getCommentList(popId);
			List<CommentDto> result = new ArrayList<>();

			for (int i = 0; i < commentEntityList.size(); i++) {
				CommentEntity commentEntity = commentEntityList.get(i);
				UserProfileDto user = userService.getUserProfileById(commentEntity.getUserEntity().getId());
				boolean isLiked = likeCommentService.isLiked(userId, commentEntity.getId());

				result.add(new CommentDto(commentEntity, user.getNickname(), user.getProfileImageUrl(), isLiked));
			}

			return new ResponseEntity<List<CommentDto>>(result, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "댓글 등록", response = ResponseEntity.class)
	@PostMapping
	public ResponseEntity<?> addComment(@RequestBody UserPopContentIdDto userPopContentIdDto) {
		try {
			commentService.insertComment(userPopContentIdDto);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "댓글 수정", response = ResponseEntity.class)
	@PutMapping
	public ResponseEntity<?> changeComment(@RequestBody UserCommentContentIdDto userCommentContentIdDto) {
		try {
			commentService.updateComment(userCommentContentIdDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "댓글 삭제", response = ResponseEntity.class)
	@DeleteMapping
	public ResponseEntity<?> deleteComment(@RequestBody UserCommentIdDto userCommentIdDto) {
		try {
			commentService.deleteComment(userCommentIdDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}