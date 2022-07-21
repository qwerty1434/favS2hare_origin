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
	
	@Query(value ="select * from user where email = :email", nativeQuery = true) // nativeQuery는 뭐하는 거임?
	public userEntity search(@Param("email") String email); // public없으면 null이 반환, List<UserEntity>는 또 잘들어감 왜그럼?
	
	
}
