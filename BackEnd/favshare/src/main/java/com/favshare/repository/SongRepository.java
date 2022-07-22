package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.SongEntity;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Integer>{
	
	@Query(value="select * from song where id = :id", nativeQuery = true)
	public SongEntity search(@Param("id") int id);
}
