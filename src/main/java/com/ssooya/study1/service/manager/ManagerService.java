package com.ssooya.study1.service.manager;

import com.ssooya.study1.domain.manager.Manager;
import com.ssooya.study1.domain.manager.ManagerRepository;
import com.ssooya.study1.domain.member.Member;
import com.ssooya.study1.domain.member.MemberRepository;
import com.ssooya.study1.domain.project.Project;
import com.ssooya.study1.web.manager.dto.ManagerResponseDto;
import com.ssooya.study1.web.manager.dto.ManagerSaveRequestDto;
import com.ssooya.study1.web.project.dto.ProjectListResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
				.managerTelNo(requestDto.getManagerTelNo())
				.build();

		return managerRepository.save(requestDto.toEntity()).getId();

	}

	public List findManagerByPageRequest(Pageable pageable, String member_id) {
		Page<Manager> page = managerRepository.findAllByMemberId(pageable, member_id);
		return page.stream().map(ManagerResponseDto::new).collect(Collectors.toList());


	}
}
