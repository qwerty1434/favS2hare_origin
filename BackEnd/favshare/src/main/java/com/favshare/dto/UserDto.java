package com.favshare.dto;

import java.time.LocalDateTime;

public class UserDto {
	private int id;
	private String name;
	private String email;
	private String password;
	private String nickname;
	private LocalDateTime birthDate;
	private String phone;
	private String content;
	private String profileImageUrl;
	
	public UserDto(int id, String name, String email, String password, String nickname, LocalDateTime birthDate,
			String phone, String content, String profileImageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.birthDate = birthDate;
		this.phone = phone;
		this.content = content;
		this.profileImageUrl = profileImageUrl;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", nickname="
				+ nickname + ", birthDate=" + birthDate + ", phone=" + phone + ", content=" + content
				+ ", profileImageUrl=" + profileImageUrl + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	
	

}
