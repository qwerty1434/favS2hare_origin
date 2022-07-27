package com.favshare.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.favshare.entity.UserEntity;

import lombok.*;

@Data
@NoArgsConstructor
public class UserInfoDto {
	private int id;
	private String name;
	private String email;
	private String password;
//	private String nickname;    
	private LocalDateTime birthDate;
	private String phone;
//	private String content;
//	private String profileImageUrl;
	
	
	
	public UserInfoDto(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.name = userEntity.getName();
		this.email = userEntity.getEmail();
		this.password = userEntity.getPassword();
		this.birthDate = userEntity.getBirthDate();
		this.phone = userEntity.getPhone();
	}	
	
	
	
}
