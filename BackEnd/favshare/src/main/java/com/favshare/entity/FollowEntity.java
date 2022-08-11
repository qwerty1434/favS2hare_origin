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
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_user_id", nullable = false)
	private UserEntity fromUserEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_user_id", nullable = false)
	private UserEntity toUserEntity;
	
}
