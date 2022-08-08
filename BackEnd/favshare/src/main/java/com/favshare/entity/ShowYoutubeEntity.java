package com.favshare.entity;


import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="show_youtube")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder

public class ShowYoutubeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;

    @ManyToOne
    @JoinColumn(name="youtube_id", nullable = false)
    private YoutubeEntity youtubeEntity;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity userEntity;
    
}
