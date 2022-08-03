package com.favshare.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPopContentIdDto {
	int userId;
	int popId;
	String content;
}
