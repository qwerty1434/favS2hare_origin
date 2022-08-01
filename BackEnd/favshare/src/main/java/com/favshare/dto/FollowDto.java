package com.favshare.dto;


import com.favshare.entity.FollowEntity;
import com.favshare.entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class FollowDto {
	private int id;
    private UserEntity fromUserEntity;
    private UserEntity toUserEntity;
    
	public FollowDto(FollowEntity followEntity) {
		this.id = followEntity.getId();
		this.fromUserEntity = followEntity.getFromUserEntity();
		this.toUserEntity = followEntity.getToUserEntity();
	}
    
}
