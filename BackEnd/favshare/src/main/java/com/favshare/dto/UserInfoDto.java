package com.favshare.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.favshare.entity.UserEntity;

import lombok.*;

@Data
@NoArgsConstructor
public class UserInfoDto {
	private int id;
	private String name;
	private String email;
	private String password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	private String phone;

	public UserInfoDto(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.name = userEntity.getName();
		this.email = userEntity.getEmail();
		this.password = userEntity.getPassword();
		this.birthDate = userEntity.getBirthDate();
		this.phone = userEntity.getPhone();
	}

}
