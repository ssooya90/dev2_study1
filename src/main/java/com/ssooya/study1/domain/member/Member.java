package com.ssooya.study1.domain.member;

import com.ssooya.study1.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_MEMBER")
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(length = 10, nullable = false, unique = true) // 유니크 설정
	private String userId;

	@Column(length = 100, nullable = false)
	private String pw;

	@Column(length = 100, nullable = false)
	private String corpNm;

	@Column(length = 100, nullable = false)
	private String telNo;

	@Builder
	public Member(Long id, String userId, String pw, String corpNm, String telNo) {
		this.id = id;
		this.userId = userId;
		this.pw = pw;
		this.corpNm = corpNm;
		this.telNo = telNo;
	}
}
