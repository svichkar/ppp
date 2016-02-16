package com.nixsolutions.studentgrade.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nixsolutions.studentgrade.service.StatusService;
import com.nixsolutions.studentgrade.service.StudentGroupService;
import com.nixsolutions.studentgrade.service.StudentService;
import com.nixsolutions.studentgrade.service.TermService;

@Controller
public class UpdateStudentController {
	@Autowired
	StudentService studentService;

	@Autowired
	StudentGroupService groupService;

	@Autowired
	StatusService statusService;

	@Autowired
	TermService termService;

	@RequestMapping(value = "/updateStudent")
	protected String updateStudent(@RequestParam(value = "update", required = false) String update,			
			@RequestParam(value = "updatedFirstName", required = false) String firstName,
			@RequestParam(value = "updatedLastName", required = false) String lastName,
			@RequestParam(value = "updatedGroupName", required = false) Long groupId,
			@RequestParam(value = "updatedAdmissionDate", required = false) Date admissionDate,
			@RequestParam(value = "updatedStatusName", required = false) Long statusId,
			@RequestParam(value = "updatedTermName", required = false) Long termId,
			@RequestParam(value = "student_id", required = false) Long studentId,
			RedirectAttributes redirectAttributes,
			Model model) {
		if (update != null) {
			studentService.updateStudent(studentId, firstName, lastName, groupId, admissionDate, statusId, termId);
			redirectAttributes.addAttribute("message", "Updated student with id "+ studentId);
			return "redirect:students";			
		}
		model.addAttribute("updateStudent", studentService.findStudentById(studentId));
		model.addAttribute("groups", groupService.findAllStudentGroups());
		model.addAttribute("statuses", statusService.findAllStatuses());
		model.addAttribute("terms", termService.findAllTerms());
		return "updateStudent";
	}
}
