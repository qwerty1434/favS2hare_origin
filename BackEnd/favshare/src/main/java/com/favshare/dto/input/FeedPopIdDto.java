package com.favshare.dto.input;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedPopIdDto {
	int feedId;
	List<Integer> popId;

}
