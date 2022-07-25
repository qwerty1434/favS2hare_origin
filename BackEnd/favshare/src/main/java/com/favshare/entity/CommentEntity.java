package com.favshare.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "comment")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
    @Column(nullable = false)
	private String content;

	@Column(name= "create_date", nullable = false)
	private LocalDateTime createDate;
    
    @Column(name= "is_modify", nullable = false)
	private boolean isModify; 
	
	@ManyToOne
	@JoinColumn(name ="user_id", nullable = false)
	private UserEntity userEntity;
    
	@ManyToOne
	@JoinColumn(name ="pop_id", nullable = false)
	private PopEntity popEntity;
	
	@OneToMany(mappedBy="commentEntity")
	private List<LikeCommentEntity> likeCommentList = new ArrayList<>();
	
	
	
	
}
