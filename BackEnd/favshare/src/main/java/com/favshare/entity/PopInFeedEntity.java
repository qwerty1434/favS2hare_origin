package com.favshare.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "pop_in_feed")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder

public class PopInFeedEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pop_id", nullable = false)
	private PopEntity popEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feed_id", nullable = false)
	private FeedEntity feedEntity;

}
