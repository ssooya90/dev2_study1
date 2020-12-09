package com.ssooya.study1.web.manager;

import com.ssooya.study1.service.manager.ManagerService;
import com.ssooya.study1.web.manager.dto.ManagerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ManagerController {

	private final ManagerService managerService;


	@GetMapping("/manager/list")
	public String managerList(){
		return "/manager/manager_list";
	}

	@GetMapping("/manager/reg")
	public String managerReg(){
		return "/manager/manager_reg";
	}

	@PostMapping("/manager/reg")
	@ResponseBody
	public Long managerRegProc(@RequestBody ManagerSaveRequestDto requestDto, Principal principal){


		managerService.save(requestDto, principal);


		return null;
	}



}
