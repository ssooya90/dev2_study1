package com.ssooya.study1.web.manager.dto;

import com.ssooya.study1.domain.manager.Manager;
import com.ssooya.study1.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagerSaveRequestDto {

	private Member member;
	private String managerNm;
	private String managerTelNo;

	@Builder public ManagerSaveRequestDto(Member member, String managerNm, String managerTelNo){
		this.member = member;
		this.managerNm = managerNm;
		this.managerTelNo = managerTelNo;
	}

	// Dto 객체를 entity 객체로 변환
	public Manager toEntity(){
		return Manager.builder()
				.member(member)
				.managerNm(managerNm)
				.managerTelNo(managerTelNo)
				.build();
	}

}
