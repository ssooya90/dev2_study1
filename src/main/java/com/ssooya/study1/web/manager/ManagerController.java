package com.ssooya.study1.web.manager;

import com.ssooya.study1.service.manager.ManagerService;
import com.ssooya.study1.web.manager.dto.ManagerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String managerList(Model model, final Pageable pageable, Principal principal){

		model.addAttribute("managerList",managerService.findManagerByPageRequest(pageable, principal.getName()));



		return "/manager/manager_list";
	}

	@GetMapping("/manager/reg")
	public String managerReg(){
		return "/manager/manager_reg";
	}

	@PostMapping("/manager/reg")
	@ResponseBody
	public Long managerRegProc(@RequestBody ManagerSaveRequestDto requestDto, Principal principal){

		return managerService.save(requestDto, principal);
	}



}
