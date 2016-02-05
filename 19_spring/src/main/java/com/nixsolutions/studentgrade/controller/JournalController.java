package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.studentgrade.service.GradeService;
import com.nixsolutions.studentgrade.service.JournalService;
import com.nixsolutions.studentgrade.service.StudentService;

@Controller
public class JournalController {
	@Autowired
	JournalService journalService;

	@Autowired
	StudentService studentService;

	@Autowired
	GradeService gradeService;

	@RequestMapping(value = "/journal")
	protected String journal(@RequestParam(value = "openJournal", required = false) String openJournal,			
			@RequestParam(value = "student_id", required = false) Long studentId,
			@RequestParam(value = "term_id", required = false) Long termId,
			Model model) {
		if (openJournal != null) {
			model.addAttribute("journals", journalService.findJournalsByStudentIdAndTermId(studentId, termId));
			model.addAttribute("gpas", gradeService.findGradeById(journalService.findGPAByStudentIdAndTermId(studentId, termId)));
			model.addAttribute("student", studentService.findStudentById(studentId));
			model.addAttribute("term", studentService.findStudentById(studentId).getTerm());
			return "journal";
		}
		model.addAttribute("student", studentService.findStudentById(studentId));
		model.addAttribute("term", studentService.findStudentById(studentId).getTerm());
		return "journal";
	}
}
