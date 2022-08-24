package com.favshare.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.entity.LikeCommentEntity;

public interface LikeCommentRepository extends JpaRepository<LikeCommentEntity, Integer> {
	@Transactional
	@Modifying
	@Query(value = "delete from like_comment where user_id = :userId and comment_id = :commentId", nativeQuery = true)
	public void deleteLikeByUserCommentId(@Param("userId") int userId, @Param("commentId") int commentId);

	@Query(value = "select count(*) from like_comment where user_id = :userId and comment_id = :commentId", nativeQuery = true)
	public int isLiked(@Param("userId") int userId, @Param("commentId") int commentId);

	@Query(value = "SELECT COUNT(*) FROM like_comment WHERE user_id = :userId AND comment_id = :commentId", nativeQuery = true)
	public int findByUserIdCommentID(@Param("userId") int userId, @Param("commentId") int commentId);
}
