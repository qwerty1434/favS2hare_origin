package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.entity.UserEntity;



public interface UserRepository extends JpaRepository<UserEntity,Integer>{
	
	UserEntity findByEmail(String email);
	

}
