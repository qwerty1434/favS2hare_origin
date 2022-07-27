package com.favshare.dto;

import com.favshare.entity.SongEntity;

import lombok.*;

@Data
@NoArgsConstructor
public class SongDto {
	private int id;	
	private String name;
    private String content;
	public SongDto(SongEntity songEntity) {
		this.id = songEntity.getId();
		this.name = songEntity.getName();
		this.content = songEntity.getContent();
	}

    
}
