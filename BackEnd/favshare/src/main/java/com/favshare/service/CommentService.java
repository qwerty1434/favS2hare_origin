package com.favshare.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void insertComment(HashMap<String, String> commentInfo) {
		CommentEntity commentEntity = new CommentEntity();
		UserEntity userEntity = userRepository.findById(Integer.parseInt(commentInfo.get("userId"))).get();
		PopEntity popEntity = popRepository.findById(Integer.parseInt(commentInfo.get("popId"))).get();
		
		commentEntity = commentEntity.builder().content(commentInfo.get("content")).createDate(LocalDateTime.now()).isModify(false).userEntity(userEntity).popEntity(popEntity).build();
		
		commentRepository.save(commentEntity);
	}
	
	public void updateComment(HashMap<String, String> commentInfo) {
		CommentEntity commentEntity;
		commentEntity = commentRepository.findByUserCommentId(Integer.parseInt(commentInfo.get("userId")), Integer.parseInt(commentInfo.get("commentId")));
		commentEntity.changeComment(commentInfo.get("content"));
		commentRepository.save(commentEntity);
	}
	
	public void deleteComment(HashMap<String, String> commentInfo) {
		commentRepository.deleteByUserCommentId(Integer.parseInt(commentInfo.get("userId")), Integer.parseInt(commentInfo.get("commentId")));
	}
	

	
}
