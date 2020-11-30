package com.ssooya.study1.service.member;

import com.ssooya.study1.domain.member.MemberRepository;
import com.ssooya.study1.web.member.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

	Logger logger = LoggerFactory.getLogger(MemberService.class);

	private final MemberRepository memberRepository;


	@Transactional
	public Long save(MemberSaveRequestDto requestDto){
		return memberRepository.save(requestDto.toEntity()).getId();
	}


}
