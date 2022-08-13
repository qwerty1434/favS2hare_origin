package com.favshare.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class PopAlgoDto {
	private int id;
	private int views;
	private int likeCount;
	private int followers;
	private int algoScore;
	
	public PopAlgoDto(int id, int views, int likeCount, int followers, int algoScore) {
		this.id = id;
		this.views = views;
		this.likeCount = likeCount;
		this.followers = followers;
		this.algoScore = algoScore;
	}
}
