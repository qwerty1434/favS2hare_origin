package com.favshare.dto;

import com.favshare.entity.FeedEntity;
import com.favshare.entity.PopEntity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopInFeedDto {
	private int id;
	private int popId;
	private int feedId;
}
