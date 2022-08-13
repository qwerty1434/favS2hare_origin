package com.favshare.dto.input;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
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
