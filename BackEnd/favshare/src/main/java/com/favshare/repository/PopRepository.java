package com.favshare.repository;


import java.util.*;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.PopEntity;


@Repository
public interface PopRepository extends JpaRepository<PopEntity,Integer>{
	
	@Query(value ="select * from pop where id = :id", nativeQuery = true)
	public PopEntity search(@Param("id") int id); 
	
	@Query(value = "select count(*) from pop where user_id = :userId", nativeQuery = true)
	public int countPopByUserId(@Param("userId") int userId);
	
	
}
