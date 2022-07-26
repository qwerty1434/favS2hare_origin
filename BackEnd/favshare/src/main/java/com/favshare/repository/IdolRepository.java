package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favshare.entity.IdolEntity;

@Repository
public interface IdolRepository extends JpaRepository<IdolEntity, Integer>{
	
	IdolEntity findByName(String name);

}
