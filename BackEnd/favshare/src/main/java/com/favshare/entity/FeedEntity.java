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
	@JoinColumn(name ="feed_id", nullable = false)
	private UserEntity userEntity;

	@OneToMany(mappedBy="feedEntity")
	private List<PopInFeedEntity> popInFeedList = new ArrayList<>();
	
    
}
