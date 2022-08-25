package com.favshare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);

	@Query(value = "SELECT * FROM user where nickname LIKE CONCAT('%',:keyword,'%')", nativeQuery = true)
	public List<UserEntity> findByKeywordContains(@Param("keyword") String keyword);

}
