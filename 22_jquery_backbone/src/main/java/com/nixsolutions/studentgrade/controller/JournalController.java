package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.*;
import com.nixsolutions.studentgrade.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by konstantin on 2/2/2016.
 */
@Controller
public class JournalController {

    private static final Logger LOG = LogManager.getLogger(JournalController.class);

    @Autowired
    JournalService journalService;
    @Autowired
    GradeService gradeService;
    @Autowired
    StudentGroupService groupService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/journal", method = RequestMethod.GET)
    public ModelAndView journalPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("journals", journalService.findAll());
        model.addObject("grades", gradeService.findAll());
        model.addObject("groups", groupService.findAll());
        model.addObject("subjects", subjectService.findAll());
        model.setViewName("journal");
        return model;
    }

    @RequestMapping(value = "/journal", params = "add", method = RequestMethod.POST)
    public String addJournal(@ModelAttribute("journal") Journal journal,
                             @ModelAttribute("firstName") String firstName,
                             @ModelAttribute("lastName") String lastName,
                             @ModelAttribute("selectedGroup") String selectedGroup,
                             @ModelAttribute("selectedSubject") String selectedSubject,
                             @ModelAttribute("selectedGrade") String selectedGrade,
                             Model model) {

        try {
            StudentGroup group = groupService.findByName(selectedGroup);
            Subject subject = subjectService.findByName(selectedSubject);
            Grade grade = gradeService.findByName(selectedGrade);
            Student student = studentService.findByNameAndLastName(firstName, lastName);
            if (student!= null && !student.isEmpty()) {
                journal.setSubject(subject);
                journal.setGrade(grade);
                journal.setStudent(student);
                if (student.getStudentGroup().getGroupId() == group.getGroupId()) {
                    journalService.create(journal);
                    model.addAttribute("message", "Successes");
                    model.addAttribute("color", "color:#15DC13");
                } else {
                    model.addAttribute("message", "Group is not valid for entered student. Please check <a href=\"student\">Students List</a>");
                }
            } else {
                model.addAttribute("message", "Student doesn't exist. Please check <a href=\"student\">Students List</a>");
            }
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Journal record can't be added");
        }
        model.addAttribute("journals", journalService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("grades", gradeService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "journal";
    }


    @RequestMapping(value = "/journal", params = "update", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute("journalId") String journalId,
                                @ModelAttribute("selectedGrade") String selectedGrade,
                                Model model) {

        try {
            Journal j = journalService.findById(Long.valueOf(journalId));
            j.setGrade(gradeService.findByName(selectedGrade));
            journalService.update(j);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Journal record cannot be updated");
        }
        model.addAttribute("journals", journalService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("grades", gradeService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "journal";
    }

    @RequestMapping(value = "/journal", params = "delete", method = RequestMethod.POST)
    public String deleteStudent(@ModelAttribute("journalId") String journalId,
                                Model model) {

        try {
            Journal journal = journalService.findById(Long.valueOf(journalId));
            journalService.delete(journal);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Journal record cannot be deleted");
        }
        model.addAttribute("journals", journalService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("grades", gradeService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "journal";
    }
}
