package com.favshare.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	
	@Query(value ="select * from comment where user_id = :userId and id = :commentId", nativeQuery = true)
	public CommentEntity findByUserCommentId(@Param("userId") int userId, @Param("commentId") int commentId);
	
	@Transactional // 이게 필요한 이유가 뭘까?
	@Modifying // 이게 필요한 이유가 뭘까?
	@Query(value ="delete from comment where user_id = :userId and id = :commentId", nativeQuery = true)
	public void deleteByUserCommentId(@Param("userId") int userId, @Param("commentId") int commentId);
	
	
	
	@Query(value ="select * from comment where pop_id = :popId", nativeQuery = true)
	public List<CommentEntity> findAllByPopId(@Param("popId") int popId);

}
