package com.favshare.dto;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestSaveDto {
	int userId;
	List<Integer> IdolList;
	List<Integer> SongList;
	
	
}
