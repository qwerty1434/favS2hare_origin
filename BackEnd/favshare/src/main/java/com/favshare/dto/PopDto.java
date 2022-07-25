package com.favshare.dto;

import java.time.LocalDateTime;

public class PopDto {
	
	private int id;
	private int userId;
	private int youtubeId;
	private String name;
	private int startSecond;
	private int endSecond; // DB에서 int로 변경해야함
	private String content;
	private LocalDateTime createDate;
	private int views;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getYoutubeId() {
		return youtubeId;
	}
	public void setYoutubeId(int youtubeId) {
		this.youtubeId = youtubeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStartSecond() {
		return startSecond;
	}
	public void setStartSecond(int startSecond) {
		this.startSecond = startSecond;
	}
	public int getEndSecond() {
		return endSecond;
	}
	public void setEndSecond(int endSecond) {
		this.endSecond = endSecond;
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
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
	public PopDto(int id, int userId, int youtubeId, String name, int startSecond, int endSecond, String content,
			LocalDateTime createDate, int views) {
		super();
		this.id = id;
		this.userId = userId;
		this.youtubeId = youtubeId;
		this.name = name;
		this.startSecond = startSecond;
		this.endSecond = endSecond;
		this.content = content;
		this.createDate = createDate;
		this.views = views;
	}
	
	@Override
	public String toString() {
		return "PopDto [id=" + id + ", userId=" + userId + ", youtubeId=" + youtubeId + ", name=" + name
				+ ", startSecond=" + startSecond + ", endSecond=" + endSecond + ", content=" + content + ", createDate="
				+ createDate + ", views=" + views + "]";
	}
	
	
	
}
