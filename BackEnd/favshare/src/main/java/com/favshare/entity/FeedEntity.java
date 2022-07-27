package com.favshare.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "feed")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;


    @Column(nullable = false)
	private String name;
    @Column(name="is_first",nullable = false)
    private boolean isFirst;
    @Column(name="feed_image_url")
    private String feedImageUrl;
    
	@ManyToOne
	@JoinColumn(name ="user_id", nullable = false)
	private UserEntity userEntity;

	@OneToMany(mappedBy="feedEntity")
	private List<PopInFeedEntity> popInFeedList = new ArrayList<>();
	
	public void changeName(String name) {
		this.name = name;
	}
	
	public void changeImageUrl(String feedImageUrl) {
		this.feedImageUrl = feedImageUrl;
	}
	
	// 이 함수를 2개를 만들어서 해야할까? (대표 -> 일반, 일반 -> 대표)
//	public void changeIsFirst() {
//		this.isFirst = !this.isFirst;
//	
//	}
	
	public void changeIsFirst() {
		this.isFirst = true;
	
	}
	
	public void changeIsNotFirst() {
		this.isFirst = false;
	}
	

    
}

