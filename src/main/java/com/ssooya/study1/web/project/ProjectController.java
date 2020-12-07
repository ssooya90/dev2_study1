package com.ssooya.study1.web.project;

import com.ssooya.study1.service.project.ProjectService;
import com.ssooya.study1.web.project.dto.ProjectListResponseDto;
import com.ssooya.study1.web.project.dto.ProjectRegRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ProjectController {

	private final ProjectService projectService;

	@GetMapping("/project/list")
	public String projectList(Model model, final Pageable pageable){
		model.addAttribute("currentNum","01");
		model.addAttribute("projectList",projectService.findProjectByPageRequest(pageable));

		return "/project/project_list";
	}

	@GetMapping("/project/edit/{id}")
	public String detail(@PathVariable("id") Long id, Model model){

		ProjectListResponseDto projectListResponseDto = projectService.findById(id);
		model.addAttribute("projectDto", projectListResponseDto);

		return "/project/project_edit";
	}

	@GetMapping("/project/reg")
	public String projectReg(){

		return "/project/project_reg";
	}

	@PostMapping("/project/reg")
	@ResponseBody
	public Long projectReg(@RequestBody ProjectRegRequestDto requestDto, Principal principal, Model model){

		return projectService.save(requestDto, principal);
	}






	@GetMapping("/project/edit")
	public String projectEdit(){
		return "/project/project_edit";
	}
}
