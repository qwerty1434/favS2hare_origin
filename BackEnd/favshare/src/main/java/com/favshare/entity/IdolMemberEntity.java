package com.favshare.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "idol_member")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdolMemberEntity { 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idol_id", nullable = false)
	private IdolEntity idolEntity;

}
