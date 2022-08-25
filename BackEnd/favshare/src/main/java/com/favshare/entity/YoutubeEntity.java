package com.favshare.entity;

import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "youtube")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YoutubeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String url;

	@OneToMany(mappedBy = "youtubeEntity")
	private List<PopEntity> popList = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idol_id")
	private IdolEntity idolEntity;

	@OneToMany(mappedBy = "youtubeEntity")
	private List<StoreYoutubeEntity> storeYoutubeList = new ArrayList<>();

	@OneToMany(mappedBy = "youtubeEntity")
	private List<ShowYoutubeEntity> showYoutubeList = new ArrayList<>();

}
