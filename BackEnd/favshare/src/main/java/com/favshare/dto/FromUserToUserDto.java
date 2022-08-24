package com.favshare.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FromUserToUserDto {
	int fromUserId;
	int toUserId;
}
