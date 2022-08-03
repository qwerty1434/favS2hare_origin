package com.favshare.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.entity.LikeCommentEntity;

public interface LikeCommentRepository extends JpaRepository<LikeCommentEntity, Integer> {
	@Transactional 
	@Modifying 
	@Query(value ="delete from like_comment where user_id = :userId and comment_id = :commentId", nativeQuery = true)
	public void deleteLikeByUserCommentId(@Param("userId") int userId, @Param("commentId") int commentId);
}
