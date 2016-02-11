package com.nixsolutions.studentgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.studentgrade.service.GradeService;
import com.nixsolutions.studentgrade.service.JournalService;
import com.nixsolutions.studentgrade.service.StudentService;
import com.nixsolutions.studentgrade.service.SubjectService;

@Controller
public class JournalsController {
	@Autowired
	JournalService journalService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	GradeService gradeService;
	
	@RequestMapping(value = "/journals")
	protected String journals(@RequestParam(value = "add", required = false) String add,
			@RequestParam(value = "delete", required = false) String delete,
			@RequestParam(value = "newStudent", required = false) Long studentId,
			@RequestParam(value = "newSubjectName", required = false) Long subjectId,
			@RequestParam(value = "newGradeName", required = false) Long gradeId,
			@RequestParam(value = "journal_id", required = false) Long journalId,
			@RequestParam(value = "message", required = false) String message,
			Model model) {
		
		if (message != null){
			model.addAttribute("message", message);
		}
			
		if (add != null) {
			model.addAttribute("message", "Added journal record with id "
					+ journalService.createJournal(studentId, subjectId, gradeId));
		}
		if (delete != null) {
			journalService.deleteJournal(journalId);
			model.addAttribute("message", "Deleted journal with id " + journalId);
		}		
		
		model.addAttribute("journals", journalService.findAllJournals());
		model.addAttribute("students", studentService.findAllStudents());
		model.addAttribute("subjects", subjectService.findAllSubjects());
		model.addAttribute("grades", gradeService.findAllGrades());
		return "journals";
	}

}
