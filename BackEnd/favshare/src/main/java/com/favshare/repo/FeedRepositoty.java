package com.favshare.repo;


import java.util.*;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.FeedEntity;
import com.favshare.entity.PopEntity;
import com.favshare.entity.userEntity;


@Repository
public interface FeedRepositoty extends JpaRepository<userEntity,String>{
	
	@Query(value ="select * from feed where id = :id", nativeQuery = true)
	public FeedEntity search(@Param("id") int id); 
	
	
}
