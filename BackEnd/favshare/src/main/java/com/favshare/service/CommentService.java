package com.favshare.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favshare.dto.input.UserCommentContentIdDto;
import com.favshare.dto.input.UserCommentIdDto;
import com.favshare.dto.input.UserPopContentIdDto;
import com.favshare.entity.CommentEntity;
import com.favshare.entity.PopEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.CommentRepository;
import com.favshare.repository.PopRepository;
import com.favshare.repository.UserRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PopRepository popRepository;

	public List<CommentEntity> getCommentList(int popId) {
		return commentRepository.findAllByPopId(popId);
	}

	public void insertComment(UserPopContentIdDto userPopContentIdDto) {

		UserEntity userEntity = userRepository.findById(userPopContentIdDto.getUserId()).get();
		PopEntity popEntity = popRepository.findById(userPopContentIdDto.getPopId()).get();

		CommentEntity commentEntity = CommentEntity.builder().content(userPopContentIdDto.getContent())
				.createDate(LocalDateTime.now()).isModify(false).userEntity(userEntity).popEntity(popEntity).build();

		commentRepository.save(commentEntity);
	}

	public void updateComment(UserCommentContentIdDto userCommentContentIdDto) {
		CommentEntity commentEntity;
		commentEntity = commentRepository.findByUserCommentId(userCommentContentIdDto.getUserId(),
				userCommentContentIdDto.getCommentId());
		commentEntity.changeComment(userCommentContentIdDto.getContent());
		commentRepository.save(commentEntity);
	}

	public void deleteComment(UserCommentIdDto userCommentIdDto) {
		commentRepository.deleteByUserCommentId(userCommentIdDto.getUserId(), userCommentIdDto.getCommentId());
	}

}
