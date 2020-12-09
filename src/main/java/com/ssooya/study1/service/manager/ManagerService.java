package com.ssooya.study1.service.manager;

import com.ssooya.study1.domain.manager.ManagerRepository;
import com.ssooya.study1.domain.member.Member;
import com.ssooya.study1.domain.member.MemberRepository;
import com.ssooya.study1.web.manager.dto.ManagerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class ManagerService {

	Logger logger = LoggerFactory.getLogger(ManagerService.class);

	private final MemberRepository memberRepository;
	private final ManagerRepository managerRepository;

	public Long save(ManagerSaveRequestDto requestDto, Principal principal) {

		Member member = memberRepository.findByUserId(principal.getName()).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다"));

		requestDto = ManagerSaveRequestDto.builder()
				.member(member)
				.managerNm(requestDto.getManagerNm())
				.managerNm(requestDto.getManagerTelNo())
				.build();

		return managerRepository.save(requestDto.toEntity()).getId();

	}
}
