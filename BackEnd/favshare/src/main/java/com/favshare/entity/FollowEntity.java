package com.favshare.entity;

import java.util.List;
import javax.persistence.*;


import lombok.*;

@Entity
@Table(name = "follow")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder



public class FollowEntity {
	// PK를 없애고 복합키를 사용하기로 했습니다.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "from_user_id", nullable = false)
	private UserEntity fromUserEntity;

	@ManyToOne
	@JoinColumn(name = "to_user_id", nullable = false)
	private UserEntity toUserEntity;
	
}
