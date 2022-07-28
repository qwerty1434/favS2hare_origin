package com.favshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.favshare.entity.PopInFeedEntity;

@Repository
public interface PopInFeedRepository extends JpaRepository<PopInFeedEntity,Integer>{

}
