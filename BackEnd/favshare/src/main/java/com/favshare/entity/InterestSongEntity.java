package com.favshare.entity;


import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="interest_song")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder

public class InterestSongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="song_id", nullable = false)
    private SongEntity songEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity userEntity;

	public InterestSongEntity(SongEntity songEntity, UserEntity userEntity) {
		super();
		this.songEntity = songEntity;
		this.userEntity = userEntity;
	}
    
    
	
}
