package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.favshare.entity.IdolMemberEntity;

@Repository
public interface IdolMemberRepository extends JpaRepository<IdolMemberEntity, Integer>{

	@Query(value="select * from idolMember where id = :id", nativeQuery = true)
	public IdolMemberEntity search(@Param("id") int id);
}
