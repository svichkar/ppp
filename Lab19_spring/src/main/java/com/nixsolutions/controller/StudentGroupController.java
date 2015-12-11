package com.nixsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.entity.StudentGroup;
import com.nixsolutions.service.StudentGroupService;

@Controller
public class StudentGroupController {

	@Autowired
	private StudentGroupService studentGroupService;
	
	@RequestMapping(value = "/StudentGroups.do", method = { RequestMethod.GET, RequestMethod.POST })
	protected String studentGroupGet(Model model) {
		model.addAttribute("studentGroups", studentGroupService.getAll());
		return "/WEB-INF/jsp/studentGroup/StudentGroups.jsp";
	}

	@RequestMapping(value = "/addNewStudentGroup.do", method = RequestMethod.GET)
	protected String addNewStudentGroupGet(Model model) {
		return "/WEB-INF/jsp/studentGroup/AddNewStudentGroup.jsp";
	}

	@RequestMapping(value = "/addNewStudentGroup.do", method = RequestMethod.POST)
	protected String addNewStudentGroupPost(@ModelAttribute("studentGroupName") String studentGroupName, Model model) {
		StudentGroup studentGroup = new StudentGroup();
		studentGroup.setStudentGroupName(studentGroupName);
		studentGroupService.create(studentGroup);
		model.addAttribute("studentGroups", studentGroupService.getAll());
		return "/WEB-INF/jsp/studentGroup/StudentGroup.jsp";
	}

	@RequestMapping(value = "/editStudentGroup.do", method = RequestMethod.GET)
	protected String editStudentGroupGet(@ModelAttribute("studentGroupId") String studentGroupId, Model model) {
		StudentGroup studentGroup = studentGroupService.getByStudentGroupId(Integer.parseInt(studentGroupId));
		model.addAttribute("studentGroup", studentGroup);
		return "/WEB-INF/jsp/studentGroup/EditStudentGroup.jsp";
	}

	@RequestMapping(value = "/editStudentGroup.do", method = RequestMethod.POST)
	protected String editStudentGroupPost(@ModelAttribute("studentGroupId") String studentGroupId,
			@ModelAttribute("studentGroupName") String studentGroupName, Model model) {
		StudentGroup studentGroup = studentGroupService.getByStudentGroupId(Integer.parseInt(studentGroupId));
		studentGroup.setStudentGroupName(studentGroupName);
		studentGroupService.update(studentGroup);
		model.addAttribute("studentGroups", studentGroupService.getAll());
		return "/WEB-INF/jsp/studentGroup/StudentGroup.jsp";
	}

	@RequestMapping(value = "/deleteStudentGroup.do", method = RequestMethod.POST)
	protected String deleteStudentGroupPost(@ModelAttribute("studentGroupId") String studentGroupId, Model model) {
		studentGroupService.delete(studentGroupService.getByStudentGroupId(Integer.parseInt(studentGroupId)));
		model.addAttribute("studentGroups", studentGroupService.getAll());
		return "/WEB-INF/jsp/studentGroup/StudentGroup.jsp";
	}
}
