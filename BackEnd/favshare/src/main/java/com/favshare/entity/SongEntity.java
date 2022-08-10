package com.favshare.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "song")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SongEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @Column(nullable = false)
	private String name;

    private String content;
    
	@OneToMany(mappedBy="songEntity")
	private List<InterestSongEntity> interestSongList = new ArrayList<>();
    
	
}
