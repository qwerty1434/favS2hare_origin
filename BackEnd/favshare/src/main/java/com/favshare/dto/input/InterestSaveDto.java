package com.favshare.dto.input;

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
