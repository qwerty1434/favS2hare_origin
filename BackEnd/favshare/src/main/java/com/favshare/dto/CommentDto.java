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
	private int userId;
	private int popId;
	private String nickname;
	private String profileImageUrl;
	private int countCommentLikes;
	private boolean isLiked;

	public CommentDto(CommentEntity commentEntity, String nickname, String profileImageUrl, boolean isLiked) {
		this.id = commentEntity.getId();
		this.content = commentEntity.getContent();
		this.createDate = commentEntity.getCreateDate();
		this.isModify = commentEntity.isModify();
		this.userId = commentEntity.getUserEntity().getId();
		this.popId = commentEntity.getPopEntity().getId();
		this.nickname = nickname;
		this.profileImageUrl = profileImageUrl;
		this.countCommentLikes = commentEntity.getLikeCommentList().size();
		this.isLiked = isLiked;
	}

}
