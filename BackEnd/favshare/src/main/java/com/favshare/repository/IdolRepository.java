package com.favshare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.IdolEntity;

@Repository
public interface IdolRepository extends JpaRepository<IdolEntity, Integer> {

	public IdolEntity findByName(String name);

	@Query(value = "SELECT * FROM idol WHERE name LIKE CONCAT('%',:keyword,'%')", nativeQuery = true)
	public List<IdolEntity> findByKeywordContains(@Param("keyword") String keyword);

}
