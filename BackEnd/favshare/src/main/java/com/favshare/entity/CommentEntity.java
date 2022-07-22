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
@Table(name="comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;
	private String userId;
	private String popId;
	private String content;
	private LocalDateTime createDate;
	private int isModify;
}
