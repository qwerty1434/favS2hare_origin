package com.favshare.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.PopEntity;

@Repository
public interface PopRepository extends JpaRepository<PopEntity, Integer> {

	@Query(value = "SELECT * FROM pop where content LIKE CONCAT('%',:keyword,'%') OR name LIKE CONCAT('%',:keyword,'%')", nativeQuery = true)
	public List<PopEntity> findByKeywordContains(@Param("keyword") String keyword);

}
