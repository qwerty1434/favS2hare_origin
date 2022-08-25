package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.YoutubeEntity;

@Repository
public interface YoutubeRepository extends JpaRepository<YoutubeEntity, Integer> {

	@Query(value = "select count(*) from youtube where url = :youtubeUrl", nativeQuery = true)
	public int isDuplicated(@Param("youtubeUrl") String youtubeUrl);

	YoutubeEntity findByUrl(String url);
}
