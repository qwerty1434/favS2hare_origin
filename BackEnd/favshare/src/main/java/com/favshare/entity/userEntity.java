package com.favshare.entity;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // javax.persistence와 org.hibernate.annotations중 javax.persistence사용 권장
@Table(name = "user") // 테이블 이름
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class userEntity {
	@Id // PK
	private String email;
	private String name; // 테이블의 변수명과 객체의 변수명이 동일하면 따로 매핑하지 않아도 됨
	@Column(name = "created_at") // 테이블의 created_at변수와 객체의 createdAt변수를 매핑하겠다는 의미
	private LocalDateTime createdAt;
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	// 롬복이 정상적으로 작동하면 없애야 할 코드
	public userEntity(String email, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.email = email;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public userEntity() {
		super();
	}
	
}
