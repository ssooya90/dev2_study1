package com.ssooya.study1.web.manager.dto;

import com.ssooya.study1.domain.manager.Manager;
import com.ssooya.study1.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagerUpdateRequestDto {

	private Long id;
	private Member member;
	private String managerNm;
	private String managerTelNo;

	@Builder public ManagerUpdateRequestDto(Long id, String managerNm, String managerTelNo){
		this.id = id;
		this.managerNm = managerNm;
		this.managerTelNo = managerTelNo;
	}

	// Dto 객체를 entity 객체로 변환
	public Manager toEntity(){
		return Manager.builder()
				.id(id)
				.member(member)
				.managerNm(managerNm)
				.managerTelNo(managerTelNo)
				.build();
	}

}
