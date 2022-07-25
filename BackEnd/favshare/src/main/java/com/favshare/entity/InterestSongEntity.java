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
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;
    
    @ManyToOne
    @JoinColumn(name="song_id", nullable = false)
    private SongEntity songEntity;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity userEntity;
    
	
}
