package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favshare.entity.YoutubeEntity;

@Repository
public interface YoutubeRepository extends JpaRepository<YoutubeEntity, Integer>{
	
	YoutubeEntity findByUrl(String url);
}
