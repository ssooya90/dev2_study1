package com.ssooya.study1.web.member;

import com.ssooya.study1.service.member.MemberService;
import com.ssooya.study1.web.member.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService memberService;


	@GetMapping("/signup")
	public String signup(){
		return "sign/signup";
	}

	@GetMapping("/signin")
	public String signin(){
		return "sign/signin";
	}


	@PostMapping("/signup")
	public Long save(@RequestBody MemberSaveRequestDto requestDto) {

		Long id = memberService.save(requestDto);

		return id;
	}





}

