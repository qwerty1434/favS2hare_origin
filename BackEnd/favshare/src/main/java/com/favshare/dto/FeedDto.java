package com.favshare.dto;

import com.favshare.entity.FeedEntity;

import lombok.*;

@Data
@NoArgsConstructor
public class FeedDto {
	private int id;
	private String name;
	private boolean isFirst;
	private String feedImageUrl;

	// 외래키
	private int userId;

	public FeedDto(FeedEntity feedEntity) {
		this.id = feedEntity.getId();
		this.name = feedEntity.getName();
		this.isFirst = feedEntity.isFirst();
		this.feedImageUrl = feedEntity.getFeedImageUrl();
		this.userId = feedEntity.getUserEntity().getId();
	}
}
