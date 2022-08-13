package com.favshare.entity;

import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "idol")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class IdolEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	private String content;
	@Column(name = "idol_image_url")
	private String idolImageUrl;

	@OneToMany(mappedBy = "idolEntity")
	private List<IdolMemberEntity> idolMemberList = new ArrayList<>();

	@OneToMany(mappedBy = "idolEntity")
	private List<YoutubeEntity> youtubeList = new ArrayList<>();

	@OneToMany(mappedBy = "idolEntity")
	private List<InterestIdolEntity> interestIdolList = new ArrayList<>();

}
