package com.ssooya.study1.web.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ProjectController {

	@GetMapping("/project/list")
	public String projectList(Model model){
		model.addAttribute("currentNum","01");
		return "/project/project_list";
	}

	@GetMapping("/project/reg")
	public String projectReg(){
		return "/project/project_reg";
	}

	@GetMapping("/project/edit")
	public String projectEdit(){
		return "/project/project_edit";
	}
}
