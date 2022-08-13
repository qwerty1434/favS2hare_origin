package com.favshare.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.favshare.entity.UserEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAllDto {
	private int id;
	private String name;
	private String email;
	private String password;
	private String nickname; 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	private String phone;
	private String content;
	private String profileImageUrl;
	
	public UserAllDto(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.name = userEntity.getName();
		this.email = userEntity.getEmail();
		this.password = userEntity.getPassword();
		this.nickname = userEntity.getNickname();
		this.birthDate = userEntity.getBirthDate();
		this.phone = userEntity.getPhone();
		this.content = userEntity.getContent();
		this.profileImageUrl = userEntity.getProfileImageUrl();
	}
	
	
	
	
	
	
}