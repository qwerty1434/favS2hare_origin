package com.favshare.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentContentIdDto {
	int userId;
	int commentId;
	String content;
}
