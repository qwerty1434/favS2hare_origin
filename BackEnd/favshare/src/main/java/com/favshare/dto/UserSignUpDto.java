package com.favshare.dto;

import java.time.LocalDateTime;

import com.favshare.entity.UserEntity;

import lombok.*;

@Data
@NoArgsConstructor
public class UserSignUpDto {
//	private int id;
	private String name;
	private String email;
	private String password;
	private String nickname;    
	private LocalDateTime birthDate;
	private String phone;
//	private String content;
//	private String profileImageUrl;
	
	
	public UserSignUpDto(UserEntity userEntity) {
		this.name = userEntity.getName();
		this.email = userEntity.getEmail();
		this.password = userEntity.getPassword();
		this.nickname = userEntity.getNickname();
		this.birthDate = userEntity.getBirthDate();
		this.phone = userEntity.getPhone();
	}
	

	
}
