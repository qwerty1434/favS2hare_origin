package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.ShowPopEntity;
import com.favshare.entity.StoreYoutubeEntity;

@Repository
public interface ShowPopRepository extends JpaRepository<ShowPopEntity, Integer>{
	
	@Query(value="select count(*) from show_pop where user_id = :userId and pop_id = :popId", nativeQuery = true)
	public int searchByUserPopId(@Param("userId") int userId, @Param("popId") int popId);
}
