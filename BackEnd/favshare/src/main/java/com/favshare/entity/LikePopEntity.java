package com.favshare.entity;


import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="like_pop")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder

public class LikePopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pop_id", nullable = false)
    private PopEntity popEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity userEntity;

    
}
