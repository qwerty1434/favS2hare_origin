package com.favshare.repo;


import java.util.*;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.userEntity;


@Repository
public interface Repo extends JpaRepository<userEntity,String>{
	
	@Query(value ="select * from user where email = :email", nativeQuery = true)
	public userEntity search(@Param("email") String email); 
	
	
}
