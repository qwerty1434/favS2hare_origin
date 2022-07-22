package com.favshare.dto;

public class FeedDto {
	
	private int id;
	private String name;
	private int userId;
	private int isFirst;
	private String feedImageUrl;
	
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(int isFirst) {
		this.isFirst = isFirst;
	}
	public String getFeedImageUrl() {
		return feedImageUrl;
	}
	public void setFeedImageUrl(String feedImageUrl) {
		this.feedImageUrl = feedImageUrl;
	}
	
	public FeedDto(int id, String name, int userId, int isFirst, String feedImageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.isFirst = isFirst;
		this.feedImageUrl = feedImageUrl;
	}
	
	@Override
	public String toString() {
		return "FeedDto [id=" + id + ", name=" + name + ", userId=" + userId + ", isFirst=" + isFirst
				+ ", feedImageUrl=" + feedImageUrl + "]";
	} 
	
	
}
