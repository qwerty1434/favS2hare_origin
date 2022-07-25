package com.favshare.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Table(name = "youtube") 
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class YoutubeEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String url;
	
	private int idolId;
}
