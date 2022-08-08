package com.favshare.entity;

import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "youtube")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class YoutubeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;
    @Column(nullable = false)
	private String url;
	
	
  	@OneToMany(mappedBy = "youtubeEntity") // Team.members는 연관관계의 주인이 아님, Member.team은 연관관계의 주인임
  	private List<PopEntity> popList = new ArrayList<>();		

  	@ManyToOne // Team.members는 연관관계의 주인이 아님, Member.team은 연관관계의 주인임
  	@JoinColumn(name="idol_id")
  	private IdolEntity idolEntity;
  	
	@OneToMany(mappedBy="youtubeEntity")
	private List<StoreYoutubeEntity> storeYoutubeList = new ArrayList<>();
	
	@OneToMany(mappedBy="youtubeEntity")
	private List<ShowYoutubeEntity> showYoutubeList = new ArrayList<>();
  	
}
