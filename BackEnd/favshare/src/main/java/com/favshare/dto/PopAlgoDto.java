package com.favshare.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.favshare.entity.PopEntity;

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
