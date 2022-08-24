package com.favshare.dto;

import java.util.List;

import com.favshare.entity.UserEntity;

import lombok.*;

@Data
@NoArgsConstructor
public class UserProfileDto {
	private int id;
	private String nickname;
	private String content;
	private String profileImageUrl;

	private int popCount;
	private int followerNum;
	private int followingNum;

	public UserProfileDto(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.nickname = userEntity.getNickname();
		this.content = userEntity.getContent();
		this.profileImageUrl = userEntity.getProfileImageUrl();

	}

}
