package com.favshare.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowForFollowDto {

	private int fromUserId;
	private int toUserId;

	public FollowForFollowDto(int fromUserId, int toUserId) {
		super();
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
	}

}
