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
	private String name;
	@Column(name = "start_second", nullable = false)
	private int startSecond;
	@Column(name = "end_second", nullable = false)
	private int endSecond;
	private String content;
	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;
	@Column(nullable = false)
	private int views;
	@Column(name = "is_muted", nullable = false)
	private boolean isMuted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "youtube_id", nullable = false)
	private YoutubeEntity youtubeEntity;

	@OneToMany(mappedBy = "popEntity", cascade = CascadeType.ALL)
	private List<CommentEntity> commentList = new ArrayList<>();

	@OneToMany(mappedBy = "popEntity", cascade = CascadeType.ALL)
	private List<LikePopEntity> likePopList = new ArrayList<>();

	@OneToMany(mappedBy = "popEntity", cascade = CascadeType.ALL)
	private List<ShowPopEntity> showPopList = new ArrayList<>();

	@OneToMany(mappedBy = "popEntity", cascade = CascadeType.ALL)
	private List<PopInFeedEntity> popInFeedList = new ArrayList<>();

	public void changeView() {
		this.views++;
	}

}
