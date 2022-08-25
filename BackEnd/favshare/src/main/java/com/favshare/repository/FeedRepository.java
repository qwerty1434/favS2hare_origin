package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.entity.FeedEntity;

public interface FeedRepository extends JpaRepository<FeedEntity, Integer> {

	@Query(value = "select id from feed where is_first = true and user_id = :userId", nativeQuery = true)
	public int findFirstId(@Param("userId") int userId);

	@Query(value = "select count(*) from feed WHERE user_id = :userId", nativeQuery = true)
	public int countFeedByUserId(@Param("userId") int userId);

}
