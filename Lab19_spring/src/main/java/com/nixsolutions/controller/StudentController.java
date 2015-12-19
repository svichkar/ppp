package com.nixsolutions.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nixsolutions.entity.Student;
import com.nixsolutions.service.StatusService;
import com.nixsolutions.service.StudentGroupService;
import com.nixsolutions.service.StudentService;
import com.nixsolutions.service.TermService;

@Controller
public class StudentController {
	@Autowired
	private TermService termService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private StudentGroupService studentGroupService;
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/Students.do", method = RequestMethod.GET)
	protected String studentGet(Model model) {
		model.addAttribute("students", studentService.getAll());
		model.addAttribute("groups", studentGroupService.getAll());
		return "/WEB-INF/jsp/student/Students.jsp";
	}

	@RequestMapping(value = "/Students.do", method = RequestMethod.POST)
	protected String studentPost(@ModelAttribute("firstName") String firstName,
			@ModelAttribute("lastName") String lastName, @ModelAttribute("studentGroup") String studentGroupName,
			Model model) {
		if (!firstName.isEmpty()) {
			model.addAttribute("students", studentService.getByStudentsByName(firstName, lastName));
		} else {
			model.addAttribute("students",
					studentService.getStudentsByGroupId(studentGroupService.getByStudentGroupName(studentGroupName).getStudentGroupId()));
		}
		model.addAttribute("groups", studentGroupService.getAll());
		return "/WEB-INF/jsp/student/Students.jsp";
	}

	@RequestMapping(value = "/addNewStudent.do", method = RequestMethod.GET)
	protected String addNewStudentGet(Model model) {
		return "/WEB-INF/jsp/student/AddNewStudent.jsp";
	}

	@RequestMapping(value = "/addNewStudent.do", method = RequestMethod.POST)
	protected String addNewStudentPost(@ModelAttribute("firstName") String firstName,
			@ModelAttribute("lastName") String lastName, @ModelAttribute("bday") String dateBirthday,
			@ModelAttribute("eday") String dateEntry, @ModelAttribute("group") String studentGroupName,
			@ModelAttribute("term") String termAlias, @ModelAttribute("status") String statusName, Model model) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setDateBirthday(Date.valueOf(dateBirthday));
		student.setDateEntry(Date.valueOf(dateEntry));
		student.setStudentGroup(studentGroupService.getByStudentGroupName(studentGroupName));
		student.setTerm(termService.getByTermAlias(termAlias));
		student.setStatus(statusService.getByStatusName(statusName));
		studentService.create(student);
		model.addAttribute("students", studentService.getAll());
		return "/WEB-INF/jsp/student/Student.jsp";
	}

	@RequestMapping(value = "/editStudent.do", method = RequestMethod.GET)
	protected String editStudentGet(@ModelAttribute("studentId") String studentId, Model model) {
		Student student = studentService.getByStudentId(Integer.parseInt(studentId));
		model.addAttribute("student", student);
		model.addAttribute("groups", studentGroupService.getAll());
		model.addAttribute("terms", termService.getAll());
		model.addAttribute("statuses", statusService.getAll());
		return "/WEB-INF/jsp/student/EditStudent.jsp";
	}

	@RequestMapping(value = "/editStudent.do", method = RequestMethod.POST)
	protected String editStudentPost(@ModelAttribute("studentId") String studentId,
			@ModelAttribute("firstName") String firstName, @ModelAttribute("lastName") String lastName,
			@ModelAttribute("bday") String dateBirthday, @ModelAttribute("eday") String dateEntry,
			@ModelAttribute("group") String studentGroupName, @ModelAttribute("term") String termAlias,
			@ModelAttribute("status") String statusName, Model model) {
		Student student = studentService.getByStudentId(Integer.parseInt(studentId));
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setDateBirthday(Date.valueOf(dateBirthday));
		student.setDateEntry(Date.valueOf(dateEntry));
		student.setStudentGroup(studentGroupService.getByStudentGroupName(studentGroupName));
		student.setTerm(termService.getByTermAlias(termAlias));
		student.setStatus(statusService.getByStatusName(statusName));
		studentService.update(student);
		model.addAttribute("students", studentService.getAll());
		return "/WEB-INF/jsp/student/Student.jsp";
	}

	@RequestMapping(value = "/deleteStudent.do", method = RequestMethod.POST)
	protected String deleteStudentPost(@ModelAttribute("studentId") String studentId, Model model) {
		studentService.delete(studentService.getByStudentId(Integer.parseInt(studentId)));
		model.addAttribute("students", studentService.getAll());
		return "/WEB-INF/jsp/student/Student.jsp";
	}

}
