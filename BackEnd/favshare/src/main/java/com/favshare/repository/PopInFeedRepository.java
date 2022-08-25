package com.favshare.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.entity.PopInFeedEntity;

public interface PopInFeedRepository extends JpaRepository<PopInFeedEntity, Integer> {
	@Transactional
	@Modifying
	@Query(value = "delete from pop_in_feed where feed_id = :feedId and pop_id = :popId", nativeQuery = true)
	public void deleteByPopFeedId(@Param("feedId") int feedId, @Param("popId") int popId);
}
