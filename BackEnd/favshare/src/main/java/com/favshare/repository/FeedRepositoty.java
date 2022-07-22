package com.favshare.repository;


import java.util.*;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.FeedEntity;
import com.favshare.entity.PopEntity;


@Repository
public interface FeedRepositoty extends JpaRepository<FeedEntity,Integer>{
	
	@Query(value ="select * from feed where id = :id", nativeQuery = true)
	public FeedEntity search(@Param("id") int id); 
	
	
}
