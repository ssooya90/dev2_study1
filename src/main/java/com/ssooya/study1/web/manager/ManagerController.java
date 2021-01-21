package com.ssooya.study1.web.manager;

import com.ssooya.study1.common.paging.PageRequest;
import com.ssooya.study1.domain.manager.Manager;
import com.ssooya.study1.service.manager.ManagerService;
import com.ssooya.study1.web.manager.dto.ManagerResponseDto;
import com.ssooya.study1.web.manager.dto.ManagerSaveRequestDto;
import com.ssooya.study1.web.manager.dto.ManagerUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ManagerController {

	private final ManagerService managerService;


	@GetMapping("/manager/edit/{id}")
	public String managerEdit(@PathVariable("id") Long id, Model model){
		ManagerResponseDto managerResponseDto = managerService.findById(id);
		model.addAttribute("managerDto",managerResponseDto);

		return "/manager/manager_edit";
	}

	@GetMapping("/manager/list")
	public String managerList(Model model, final PageRequest pageable, Principal principal){

		Page<Manager> managers = managerService.findManagerByPageRequest(pageable.of(), principal.getName());
		model.addAttribute("managerList", managers);

		return "/manager/manager_list";
	}

	@PostMapping("/manager/update")
	@ResponseBody
	public Long managerUpdate (@RequestBody ManagerUpdateRequestDto requestDto) {
		return managerService.update(requestDto);
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
