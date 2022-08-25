package com.favshare.dto.input;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPopYoutubeIdDto {
	int userId;
	int popId;
	private int youtubeId;

}
