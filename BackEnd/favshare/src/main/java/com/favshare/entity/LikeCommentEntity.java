package com.favshare.entity;


import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="like_comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder

public class LikeCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comment_id", nullable = false)
    private CommentEntity commentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity userEntity;
    
}
