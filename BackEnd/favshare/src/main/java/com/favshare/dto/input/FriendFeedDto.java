package com.favshare.dto.input;

import java.util.List;

import com.favshare.dto.PopDto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendFeedDto {
	UserProfileDto userProfileDto;
	PopDto popDto;

}
