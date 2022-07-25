package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.IdolEntity;

@Repository
public interface IdolRepository extends JpaRepository<IdolEntity, Integer>{

	@Query(value="select * from idol where id = :id", nativeQuery = true)
	public IdolEntity search(@Param("id") int id);
}
