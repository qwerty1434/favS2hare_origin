package com.favshare.dto;

import java.time.LocalDateTime;

import com.favshare.entity.CommentEntity;

public class CommentDto {
	private int id;
	private String userId;
	private String popId;
	private String content;
	private LocalDateTime createDate;
	private int isModify;
	
	public CommentDto(int id, String userId, String popId, String content, LocalDateTime createDate, int isModify) {
		super();
		this.id = id;
		this.userId = userId;
		this.popId = popId;
		this.content = content;
		this.createDate = createDate;
		this.isModify = isModify;
	}
	
	public CommentDto(CommentEntity commentEntity) {
		super();
		this.id = commentEntity.getId();
		this.userId = commentEntity.getUserId();
		this.popId = commentEntity.getPopId();
		this.content = commentEntity.getContent();
		this.createDate = commentEntity.getCreateDate();
		this.isModify = commentEntity.getIsModify();		
	}
	
	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", userId=" + userId + ", popId=" + popId + ", content=" + content
				+ ", createDate=" + createDate + ", isModify=" + isModify + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPopId() {
		return popId;
	}
	public void setPopId(String popId) {
		this.popId = popId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public int getIsModify() {
		return isModify;
	}
	public void setIsModify(int isModify) {
		this.isModify = isModify;
	}	

	
}
