package com.favshare.entity;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 객체와 테이블 매핑하는 어노테이션 - 테이블과의 매핑
@Table(name = "feed") // 객체와 테이블 매핑하는 어노테이션 - 엔티티와 매핑할 테이블을 지정
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 접근 권한을 prorected로 지정하여 어느곳에서나 객체를 생성할 수 있는 상황을 막는다.
@Builder(builderMethodName = "FeedEntityBuilder")
public class FeedEntity {
	@Id // PK
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private int userId;
	private int isFirst;
	private String feedImageUrl; 
	

//	public static FeedEntityBuilder builder(FeedDto feedDto) {
//  return FeedEntityBuilder()
           // Dto 만든 뒤 작성해야함 아래는 예시 
//  		 .idx(studyDto.getIdx())
//           .studyName(studyDto.getStudyName())
//           .regUserIdx(studyDto.getRegUserIdx())
//           .maxCnt(studyDto.getMaxCnt())
//           .startDate(studyDto.getStartDate())
//           .endDate(studyDto.getEndDate());
//}
	

}
