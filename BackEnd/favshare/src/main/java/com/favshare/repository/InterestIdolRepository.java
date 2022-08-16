package com.favshare.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.entity.InterestIdolEntity;

public interface InterestIdolRepository extends JpaRepository<InterestIdolEntity, Integer>{
	@Query(value ="select * from interest_idol where user_id = :userId", nativeQuery = true)
	public List<InterestIdolEntity> findAllByUserId(@Param("userId") int userId);

	@Query(value ="select * from interest_idol where idol_id = :idolId and user_id != :userId", nativeQuery = true)
	public List<InterestIdolEntity> findByIdolIdExceptUserId(@Param("userId") int userId,@Param("idolId") int idolId);

	@Query(value ="select count(*) from interest_idol where idol_id = :idolId and user_id = :userId", nativeQuery = true)
	public int findByIdolIdUserId(@Param("userId") int userId,@Param("idolId") int songId);
	
	@Transactional
	@Modifying
	@Query(value ="delete from interest_idol where user_id = :userId", nativeQuery = true)
	public void deleteByUserId(@Param("userId") int userId);	
}
