package com.ssooya.study1.web.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ProjectController {
	@GetMapping("/project/list")
	public String projectList(){
		return "/project/project_list";
	}
}
