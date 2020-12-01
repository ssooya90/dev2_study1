package com.ssooya.study1.web.member.dto;

import com.ssooya.study1.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by ssooya90@naver.com on 2020-02-24
 * Github : http://github.com/ssooya90
 */

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {

	private String userId;
	private String pw;
	private String corpNm;
	private String telNo;

	@Builder
	public MemberSaveRequestDto(String userId, String pw, String corpNm, String telNo){
		this.userId = userId;
		this.pw = pw;
		this.corpNm = corpNm;
		this.telNo = telNo;
	}

	public Member toEntity(){
		return Member.builder()
				.userId(userId)
				.pw(pw)
				.corpNm(corpNm)
				.telNo(telNo)
				.build();
	}
	// 비밀번호 암호화용
	public void setPw(String pw) {
		this.pw = pw;
	}


}
