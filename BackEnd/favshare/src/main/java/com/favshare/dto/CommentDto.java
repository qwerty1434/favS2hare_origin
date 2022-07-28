package com.favshare.dto;

import java.time.LocalDateTime;

import com.favshare.entity.CommentEntity;

import lombok.*;

@Data
@NoArgsConstructor
public class CommentDto {
	
	private int id;
	private String content;
	private LocalDateTime createDate;
	private boolean isModify;
	
	//외래키
	private int userId;
	private int popId;
	
	public CommentDto(CommentEntity commentEntity) {
		this.id = commentEntity.getId();
		this.content = commentEntity.getContent();
		this.createDate = commentEntity.getCreateDate();
		this.isModify = commentEntity.isModify();
		this.userId = commentEntity.getUserEntity().getId();
		this.popId = commentEntity.getPopEntity().getId();
	}
	
}
