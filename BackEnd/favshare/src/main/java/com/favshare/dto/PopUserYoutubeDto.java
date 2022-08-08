package com.favshare.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopUserYoutubeDto {
	int popId;
	int userId; 
	String nickname;
	String userPicture; 
	int youtubeId;
	String youtubeName; 
	String youtubeUrl; 
	int startSecond; 
	int endSecond; 
	String title;
	
	
}
