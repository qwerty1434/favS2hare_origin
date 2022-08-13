package com.favshare.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favshare.dto.FollowDto;
import com.favshare.entity.FollowEntity;
import com.favshare.entity.UserEntity;

public interface FollowRepository extends JpaRepository<FollowEntity, Integer> {

	@Transactional // 이게 필요한 이유가 뭘까?
	@Modifying // 이게 필요한 이유가 뭘까?
	@Query(value = "delete from follow where from_user_id = :fromUserId and to_user_id = :toUserId", nativeQuery = true)
	public void deleteFollowByUserId(@Param("fromUserId") UserEntity fromUserId,
			@Param("toUserId") UserEntity toUserId);

	@Query(value = "select count(*) from follow where from_user_id = :fromUserId and to_user_id = :toUserId", nativeQuery = true)
	public int countFollowFByUserId(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);

}