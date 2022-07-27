package com.favshare.dto;

import java.util.List;

import com.favshare.entity.UserEntity;

import lombok.*;

@Data
@NoArgsConstructor
public class UserProfileDto {
	private int id;
//	private String name;
//	private String email;
//	private String password;
	private String nickname;    
//	private LocalDateTime birthDate;
//	private String phone;
	private String content;
	private String profileImageUrl;
	
	private int popCount;
	
//	private int feedId;
//	
//	List<PopDto> popInFeedList; 
	
	public UserProfileDto(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.nickname = userEntity.getNickname();
		this.content = userEntity.getContent();
		this.profileImageUrl = userEntity.getProfileImageUrl();
		
	}
	
//	public UserProfileDto(UserEntity userEntity, int popCount, ....)
	
	//팔러우 팔러워 feed popinfeedlist 도 생성해야함 
	

	
}
