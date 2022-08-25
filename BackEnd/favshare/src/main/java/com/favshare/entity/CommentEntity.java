package com.favshare.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
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

	@Column(name = "create_date", nullable = false)
	private LocalDateTime createDate;

	@Column(name = "is_modify", nullable = false)
	private boolean isModify;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pop_id", nullable = false)
	private PopEntity popEntity;

	@OneToMany(mappedBy = "commentEntity", cascade = CascadeType.ALL)
	private List<LikeCommentEntity> likeCommentList = new ArrayList<>();

	public void changeComment(String content) {
		this.content = content;
		this.isModify = true;
	}

}
