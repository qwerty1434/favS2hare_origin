package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.LikePopEntity;

@Repository
public interface LikePopRepository extends JpaRepository<LikePopEntity, Integer>{

	@Query(value="select * from like_pop where user_id = :userId and pop_id = :popId", nativeQuery = true)
	public LikePopEntity searchByUserIdAndPopId(@Param("userId") int userId, @Param("popId") int popId);
}
