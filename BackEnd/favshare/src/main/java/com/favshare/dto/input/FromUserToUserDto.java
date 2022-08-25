package com.favshare.dto.input;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FromUserToUserDto {
	int fromUserId;
	int toUserId;
}
