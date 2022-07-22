package com.favshare.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;
	private String name;
	private String email;
	private String password;
	private String nickname;
	private LocalDateTime birthDate;
	private String phone;
	private String content;
	private String profileImageUrl;
}
