package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.StoreYoutubeEntity;

@Repository
public interface StoreYoutubeRepository extends JpaRepository<StoreYoutubeEntity, Integer> {

	@Query(value = "select * from store_youtube where user_id = :userId and youtube_id = :youtubeId", nativeQuery = true)
	public StoreYoutubeEntity searchByUserIdAndYoutubeId(@Param("userId") int userId,
			@Param("youtubeId") int youtubeId);

	@Query(value = "select count(*) from store_youtube where user_id = :userId and youtube_id = :youtubeId", nativeQuery = true)
	public int isBookmarked(@Param("userId") int userId, @Param("youtubeId") int youtubeId);
}
