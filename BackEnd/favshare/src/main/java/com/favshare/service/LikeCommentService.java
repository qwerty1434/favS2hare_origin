package com.favshare.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.favshare.entity.CommentEntity;
import com.favshare.entity.LikeCommentEntity;
import com.favshare.entity.UserEntity;
import com.favshare.repository.CommentRepository;
import com.favshare.repository.LikeCommentRepository;
import com.favshare.repository.UserRepository;

@Service
public class LikeCommentService {

	@Autowired
	private LikeCommentRepository likeCommentRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	public void insertLike(@RequestBody HashMap<String, String> likeCommentInfo) {
		LikeCommentEntity likeCommentEntity = new LikeCommentEntity();

		UserEntity userEntity = userRepository.findById(Integer.parseInt(likeCommentInfo.get("userId"))).get();
		CommentEntity commentEntity = commentRepository.findById(Integer.parseInt(likeCommentInfo.get("commentId")))
				.get();

		likeCommentEntity = likeCommentEntity.builder().userEntity(userEntity).commentEntity(commentEntity).build();
		likeCommentRepository.save(likeCommentEntity);
	}

	public void deleteCommentLike(@RequestBody HashMap<String, String> likeCommentInfo) {
		likeCommentRepository.deleteLikeByUserCommentId(Integer.parseInt(likeCommentInfo.get("userId")),
				Integer.parseInt(likeCommentInfo.get("commentId")));
	}

}
