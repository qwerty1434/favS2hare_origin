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
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;
	
    @ManyToOne
    @JoinColumn(name="pop_id", nullable = false)
    private PopEntity popEntity;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity userEntity;

    
}
