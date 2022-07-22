package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.entity.CommentEntity;


public interface CommentRepository extends JpaRepository<CommentEntity,Integer>{
	@Query(value ="select * from comment where id = :id", nativeQuery = true)
	public CommentEntity search(@Param("id") Integer id); 
}
