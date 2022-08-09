package com.favshare.entity;


import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="interest_idol")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder

public class InterestIdolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private int id;
    
    @ManyToOne
    @JoinColumn(name="idol_id", nullable = false)
    private IdolEntity idolEntity;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity userEntity;

	public InterestIdolEntity(IdolEntity idolEntity, UserEntity userEntity) {
		super();
		this.idolEntity = idolEntity;
		this.userEntity = userEntity;
	}

    
}
