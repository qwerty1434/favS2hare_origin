package com.favshare.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.entity.InterestSongEntity;

public interface InterestSongRepository extends JpaRepository<InterestSongEntity, Integer> {
	@Query(value = "select * from interest_song where user_id = :userId", nativeQuery = true)
	public List<InterestSongEntity> findAllByUserId(@Param("userId") int userId);

	@Query(value = "select * from interest_song where song_id = :songId and user_id != :userId", nativeQuery = true)
	public List<InterestSongEntity> findBySongIdExceptUserId(@Param("userId") int userId, @Param("songId") int songId);

}
