package com.favshare.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "song_idol")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class SongIdolEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_id", nullable = false)
	private SongEntity songEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idol_id", nullable = false)
	private IdolEntity idolEntity;

}
