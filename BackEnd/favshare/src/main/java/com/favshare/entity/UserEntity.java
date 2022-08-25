package com.favshare.entity;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = { "popList", "commentList", "feedList", "likePopList", "showPopList", "storeYoutubeList",
		"showYoutubeList", "likeCommentList", "interestIdolList", "interestSongList", "fromUserEntityList",
		"toUserEntityList" })
@Builder
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String nickname;

	@Column(name = "birth_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	private String phone;
	private String content;
	@Column(name = "profile_image_url")
	private String profileImageUrl;

	private String auth;

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<PopEntity> popList = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<CommentEntity> commentList = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<FeedEntity> feedList = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<LikePopEntity> likePopList = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<ShowPopEntity> showPopList = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<StoreYoutubeEntity> storeYoutubeList = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<ShowYoutubeEntity> showYoutubeList = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<LikeCommentEntity> likeCommentList = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<InterestIdolEntity> interestIdolList = new ArrayList<>();

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<InterestSongEntity> interestSongList = new ArrayList<>();

	@OneToMany(mappedBy = "fromUserEntity", cascade = CascadeType.ALL)
	private List<FollowEntity> fromUserEntityList = new ArrayList<>();

	@OneToMany(mappedBy = "toUserEntity", cascade = CascadeType.ALL)
	private List<FollowEntity> toUserEntityList = new ArrayList<>();

	public void changePassword(String password) {
		this.password = password;
	}

	public void changeAuth(String auth) {
		this.auth = auth;
	}

	public void changeProfile(String nickname, String content, String profileImageUrl) {
		this.nickname = nickname;
		this.content = content;
		this.profileImageUrl = profileImageUrl;
	}

	public void changeUserInfo(String name, String password, String phone, Date birthDate) {
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.birthDate = birthDate;
	}

}
