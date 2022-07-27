package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.favshare.entity.SongEntity;



public interface SongRepository extends JpaRepository<SongEntity,Integer>{

}
