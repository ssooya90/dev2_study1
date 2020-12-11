package com.ssooya.study1.web.member;

import com.ssooya.study1.service.member.MemberService;
import com.ssooya.study1.web.member.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;



	@GetMapping("/sign/signup")
	public String signup() {
		return "/sign/signup";
	}

	@GetMapping("/sign/signin")
	public String memberLogin() {
		return "/sign/signin";
	}

	@GetMapping("/loginSuccess")
	public String loginSuccess(Model model) {
		return "/sign/loginSuccess";
	}


	@PostMapping("/sign/signup")
	@ResponseBody
	public Long save(@RequestBody MemberSaveRequestDto requestDto) {

		requestDto.setPw(passwordEncoder.encode(requestDto.getPassword()));
		Long id = memberService.save(requestDto);

		return id;
	}


}

