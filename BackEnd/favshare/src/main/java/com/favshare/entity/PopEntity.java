package com.favshare.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "pop")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PopEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
    @Column(nullable = false)
	private String name; // 무슨 이름임 영상 이름?? -> 이거 DB에는 없음
    @Column(name= "start_second", nullable = false)
	private int startSecond;
    @Column(name= "end_second", nullable = false)
	private int endSecond; 
	private String content;
	@Column(name= "create_date", nullable = false)
	private LocalDateTime createDate;
    @Column(nullable = false)
	private int views;
    @Column(name= "is_muted", nullable = false)
	private boolean isMuted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	// 1. @Column이 없는 Integer는 default로 not null속성이 추가됨
	// 2. @Column이 붙은 Integer는 기본으로 nullable = true임
	// 3. @Column이 붙은 Integer는 nullable = true를 해아 not null속성이 가능
	@JoinColumn(name ="user_id",nullable = false)
	private UserEntity userEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="youtube_id", nullable = false)
	private YoutubeEntity youtubeEntity;

	@OneToMany(mappedBy = "popEntity", cascade = CascadeType.ALL) //CommentEntity.userEntity
    private List<CommentEntity> commentList = new ArrayList<>();	

	@OneToMany(mappedBy="popEntity", cascade = CascadeType.ALL)
	private List<LikePopEntity> likePopList = new ArrayList<>();

	@OneToMany(mappedBy="popEntity", cascade = CascadeType.ALL)
	private List<ShowPopEntity> showPopList = new ArrayList<>();
	
	@OneToMany(mappedBy="popEntity", cascade = CascadeType.ALL)
	private List<PopInFeedEntity> popInFeedList = new ArrayList<>();
	
	
	public void changeView() {
		this.views++;
	}
	
}
