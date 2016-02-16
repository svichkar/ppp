package com.nixsolutions.studentgrade.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.studentgrade.service.StatusService;
import com.nixsolutions.studentgrade.service.StudentGroupService;
import com.nixsolutions.studentgrade.service.StudentService;
import com.nixsolutions.studentgrade.service.TermService;

@Controller
public class StudentsController {
	@Autowired
	StudentService studentService;

	@Autowired
	StudentGroupService groupService;

	@Autowired
	StatusService statusService;

	@Autowired
	TermService termService;

	@RequestMapping(value = "/students")
	protected String students(@RequestParam(value = "add", required = false) String add,
			@RequestParam(value = "delete", required = false) String delete,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "newFirstName", required = false) String firstName,
			@RequestParam(value = "newLastName", required = false) String lastName,
			@RequestParam(value = "newGroupName", required = false) Long groupId,
			@RequestParam(value = "newAdmissionDate", required = false) Date admissionDate,
			@RequestParam(value = "newStatusName", required = false) Long statusId,
			@RequestParam(value = "newTermName", required = false) Long termId,
			@RequestParam(value = "student_id", required = false) Long studentId,
			@RequestParam(value = "searchLastName", required = false) String searchLastName,
			@RequestParam(value = "searchGroupId", required = false) Long searchGroupId,
			@RequestParam(value = "message", required = false) String message,
			Model model) {
		
		if (message != null){
			model.addAttribute("message", message);
		}
			
		if (add != null) {
			model.addAttribute("message", "Added student with id "
					+ studentService.createStudent(firstName, lastName, groupId, admissionDate, statusId, termId));
		}
		if (delete != null) {
			studentService.deleteStudent(studentId);
			model.addAttribute("message", "Deleted student with id " + studentId);
		}		
		
		model.addAttribute("students", studentService.findAllStudents());
		
		if (search != null) {
			if (searchLastName != null && searchGroupId != null) {
				model.addAttribute("students", studentService.findStudentsByLastNameAndGroupId(searchLastName, searchGroupId));
			}

			if (searchLastName != null && searchGroupId == null) {
				model.addAttribute("students", studentService.findStudentsByLastName(searchLastName));
			}

			if (searchGroupId != null && searchLastName.isEmpty()) {
				model.addAttribute("students", studentService.findStudentsByGroupId(searchGroupId));
			}
		}
		
		model.addAttribute("groups", groupService.findAllStudentGroups());
		model.addAttribute("statuses", statusService.findAllStatuses());
		model.addAttribute("terms", termService.findAllTerms());
		return "students";
	}
}
