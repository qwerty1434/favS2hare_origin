package com.favshare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.entity.InterestIdolEntity;

public interface InterestIdolRepository extends JpaRepository<InterestIdolEntity, Integer>{
	@Query(value ="select * from interest_idol where user_id = :userId", nativeQuery = true)
	public List<InterestIdolEntity> findAllByUserId(@Param("userId") int userId);
}
