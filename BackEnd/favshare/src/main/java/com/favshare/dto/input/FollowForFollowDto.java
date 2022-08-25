package com.favshare.dto.input;

import com.favshare.entity.FollowEntity;
import com.favshare.entity.UserEntity;

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
