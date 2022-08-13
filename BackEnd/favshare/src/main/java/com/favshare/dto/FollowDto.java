package com.favshare.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowDto {
	private int fromUserId;
	private int toUserId;
	private String nickname;
	private boolean isFollowForFollow;
	private String profileImageUrl;

	public FollowDto(int fromUserId, int toUserId, String nickname, boolean isFollowForFollow, String profileImageUrl) {
		super();
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.nickname = nickname;
		this.isFollowForFollow = isFollowForFollow;
		this.profileImageUrl = profileImageUrl;
	}

}
