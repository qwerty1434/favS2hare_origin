package com.favshare.dto;


import com.favshare.entity.FollowEntity;
import com.favshare.entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class FollowDto {
	private String nickname;
	private boolean isFollowForFollow;
	private String profileImageUrl;
	
	
	public FollowDto(String nickname, boolean isFollowForFollow, String profileImageUrl) {
		super();
		this.nickname = nickname;
		this.isFollowForFollow = isFollowForFollow;
		this.profileImageUrl = profileImageUrl;
	}
   
}
