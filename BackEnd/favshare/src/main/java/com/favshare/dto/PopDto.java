package com.favshare.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.favshare.entity.PopEntity;

import lombok.*;

@Data
@NoArgsConstructor
public class PopDto {
	private int id;
	private String name;
	private int startSecond;
	private int endSecond;
	private String content;
	private LocalDateTime createDate;
	private int views;
	private int likeCount;
	private String youtubeUrl;
	private boolean isLiked;
	private int userId;
	private boolean isMuted;

	public PopDto(PopEntity popEntity, boolean isLiked) {
		this.id = popEntity.getId();
		this.name = popEntity.getName();
		this.startSecond = popEntity.getStartSecond();
		this.endSecond = popEntity.getEndSecond();
		this.content = popEntity.getContent();
		this.createDate = popEntity.getCreateDate();
		this.views = popEntity.getViews();
		this.likeCount = popEntity.getLikePopList().size();
		this.youtubeUrl = popEntity.getYoutubeEntity().getUrl();
		this.isLiked = isLiked;
		this.userId = popEntity.getUserEntity().getId();
		this.isMuted = popEntity.isMuted();
	}
}
