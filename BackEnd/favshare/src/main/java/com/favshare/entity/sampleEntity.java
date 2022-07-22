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

@Entity 
@Table(name = "user") 
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class userEntity {
	@Id // PK
	private String email;
	private String name; 
	@Column(name = "created_at") 
	private LocalDateTime createdAt;
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
}
