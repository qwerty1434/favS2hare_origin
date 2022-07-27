package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.favshare.entity.IdolEntity;
import com.favshare.entity.IdolMemberEntity;


public interface IdolMemberRepository extends JpaRepository<IdolMemberEntity, Integer>{
	IdolEntity findByName(String name);
}
