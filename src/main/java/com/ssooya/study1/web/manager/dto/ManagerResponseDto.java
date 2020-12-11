package com.ssooya.study1.web.manager.dto;

import com.ssooya.study1.domain.manager.Manager;
import com.ssooya.study1.domain.member.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ManagerResponseDto {

	private Member member;
	private String managerNm;
	private String managerTelNo;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;



	public ManagerResponseDto(Manager entity) {
		this.member = entity.getMember();
		this.managerNm = entity.getManagerNm();
		this.managerTelNo = entity.getManagerTelNo();
	}

}
