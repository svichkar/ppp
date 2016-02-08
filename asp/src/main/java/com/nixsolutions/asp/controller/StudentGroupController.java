package com.nixsolutions.asp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.asp.entity.StudentGroup;
import com.nixsolutions.asp.service.StudentGroupService;

@Controller
@RequestMapping(value = "/studentGroups")
public class StudentGroupController {

	@Autowired
	private StudentGroupService studentGroupService;
	
	@RequestMapping(value = "/student-groups", method = { RequestMethod.GET, RequestMethod.POST })
	protected String studentGroupGet(Model model) {
		model.addAttribute("studentGroups", studentGroupService.getAll());
		return "studentGroup/studentGroups";
	}

	@RequestMapping(value = "/add-new-student-group", method = RequestMethod.GET)
	protected String addNewStudentGroupGet(Model model) {
		return "studentGroup/addNewStudentGroup";
	}

	@RequestMapping(value = "/create-student-group", method = RequestMethod.POST)
	protected String addNewStudentGroupPost(@ModelAttribute("studentGroupName") String studentGroupName, Model model) {
		StudentGroup studentGroup = new StudentGroup();
		studentGroup.setStudentGroupName(studentGroupName);
		studentGroupService.create(studentGroup);
		model.addAttribute("studentGroups", studentGroupService.getAll());
		return "studentGroup/studentGroups";
	}

	@RequestMapping(value = "/edit-student-group", method = RequestMethod.GET)
	protected String editStudentGroupGet(@ModelAttribute("studentGroupId") String studentGroupId, Model model) {
		StudentGroup studentGroup = studentGroupService.getByStudentGroupId(Integer.parseInt(studentGroupId));
		model.addAttribute("studentGroup", studentGroup);
		return "studentGroup/editStudentGroup";
	}

	@RequestMapping(value = "/save-student-group", method = RequestMethod.POST)
	protected String editStudentGroupPost(@ModelAttribute("studentGroupId") String studentGroupId,
			@ModelAttribute("studentGroupName") String studentGroupName, Model model) {
		StudentGroup studentGroup = studentGroupService.getByStudentGroupId(Integer.parseInt(studentGroupId));
		studentGroup.setStudentGroupName(studentGroupName);
		studentGroupService.update(studentGroup);
		model.addAttribute("studentGroups", studentGroupService.getAll());
		return "studentGroup/studentGroups";
	}

	@RequestMapping(value = "/delete-student-group", method = RequestMethod.POST)
	protected String deleteStudentGroupPost(@ModelAttribute("studentGroupId") String studentGroupId, Model model) {
		studentGroupService.delete(studentGroupService.getByStudentGroupId(Integer.parseInt(studentGroupId)));
		model.addAttribute("studentGroups", studentGroupService.getAll());
		return "studentGroup/studentGroups";
	}
}
