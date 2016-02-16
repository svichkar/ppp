package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nixsolutions.studentgrade.service.GradeService;
import com.nixsolutions.studentgrade.service.JournalService;
import com.nixsolutions.studentgrade.service.StudentService;
import com.nixsolutions.studentgrade.service.SubjectService;

@Controller
public class UpdateJournalController {
	@Autowired
	JournalService journalService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	GradeService gradeService;

	@RequestMapping(value = "/updateJournal")
	protected String updateJournal(@RequestParam(value = "update", required = false) String update,			
			@RequestParam(value = "updatedStudent", required = false) Long studentId,
			@RequestParam(value = "updatedSubject", required = false) Long subjectId,
			@RequestParam(value = "updatedGrade", required = false) Long gradeId,
			@RequestParam(value = "journal_id", required = false) Long journalId,
			RedirectAttributes redirectAttributes,
			Model model) {
		if (update != null) {
			journalService.updateJournal(journalId, studentId, subjectId, gradeId);
			redirectAttributes.addAttribute("message", "Updated journal record with id "+ journalId);
			return "redirect:journals";			
		}
		model.addAttribute("updateJournal", journalService.findJournalById(journalId));
		model.addAttribute("students", studentService.findAllStudents());
		model.addAttribute("subjects", subjectService.findAllSubjects());
		model.addAttribute("grades", gradeService.findAllGrades());
		return "updateJournal";
	}
}
