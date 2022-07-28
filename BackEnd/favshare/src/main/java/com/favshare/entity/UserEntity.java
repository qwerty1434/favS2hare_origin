package com.favshare.entity;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude= {"popList","commentList","feedList","likePopList","showPopList","storeYoutubeList","showYoutubeList","likeCommentList","interestIdolList","interestSongList","fromUserEntityList","toUserEntityList"})
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
    
    @Column(name= "birth_date")
	private LocalDateTime birthDate;
	private String phone;
	private String content;
    @Column(name= "profile_image_url")
	private String profileImageUrl;
    
    
    
	@OneToMany(mappedBy = "userEntity")
    private List<PopEntity> popList = new ArrayList<>();	
    
	@OneToMany(mappedBy = "userEntity")
    private List<CommentEntity> commentList = new ArrayList<>();	

	@OneToMany(mappedBy = "userEntity")
    private List<FeedEntity> feedList = new ArrayList<>();	

	@OneToMany(mappedBy="userEntity")
	private List<LikePopEntity> likePopList = new ArrayList<>();
	
	@OneToMany(mappedBy="userEntity")
	private List<ShowPopEntity> showPopList = new ArrayList<>();
	
	@OneToMany(mappedBy="userEntity")
	private List<StoreYoutubeEntity> storeYoutubeList = new ArrayList<>();
	
	@OneToMany(mappedBy="userEntity")
	private List<ShowYoutubeEntity> showYoutubeList = new ArrayList<>();
	
	@OneToMany(mappedBy="userEntity")
	private List<LikeCommentEntity> likeCommentList = new ArrayList<>();
	
	@OneToMany(mappedBy="userEntity")
	private List<InterestIdolEntity> interestIdolList = new ArrayList<>();
	
	@OneToMany(mappedBy="userEntity")
	private List<InterestSongEntity> interestSongList = new ArrayList<>();
	
	@OneToMany(mappedBy="fromUserEntity")
	private List<FollowEntity> fromUserEntityList = new ArrayList<>();
	
	@OneToMany(mappedBy="toUserEntity")
	private List<FollowEntity> toUserEntityList = new ArrayList<>();
	
	public void changePassword(String password) {
		this.password = password;
	}
	
	
	public void changeProfile(String nickname, String content, String profileImageUrl) {
		this.nickname = nickname;
		this.content = content;
		this.profileImageUrl = profileImageUrl;
	}
	
	public void changeUserInfo(String name, String password, String phone ,LocalDateTime birthDate) {
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.birthDate = birthDate;
	}
	
	//Team과 Member는 1:N
	//User와 Pop은 1:N
	//Youtube와 Pop은 1:N
	
	// Team은(User는)(Youtube)(Idol)
	/*
	// 단방향과 달리 추가된 부분
  	@OneToMany(mappedBy = "team") // Team.members는 연관관계의 주인이 아님, Member.team은 연관관계의 주인임
  	private List<Member> members = new ArrayList<>();	

	 * */
	
	// Member는 (Pop)(IdolMember)
	/*
	@ManyToOne
	@JoinColumn(name ="team_id") // FK
	private Team team;	 * */
	
}
