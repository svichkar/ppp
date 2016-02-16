package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.Status;
import com.nixsolutions.studentgrade.model.Student;
import com.nixsolutions.studentgrade.model.StudentGroup;
import com.nixsolutions.studentgrade.model.Term;
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

import java.sql.Date;
import java.util.List;

/**
 * Created by konstantin on 2/1/2016.
 */
@Controller
public class StudentController {

    private static final Logger LOG = LogManager.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;
    @Autowired
    TermService termService;
    @Autowired
    StudentGroupService groupService;
    @Autowired
    StatusService statusService;
    @Autowired
    JournalService journalService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ModelAndView studentPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Student Page");
        model.addObject("students", studentService.findAll());
        model.addObject("groups", groupService.findAll());
        model.addObject("terms", termService.findAll());
        model.addObject("statusList", statusService.findAll());
        model.setViewName("student");
        return model;
    }


    @RequestMapping(value = "/student", params = "operation", method = RequestMethod.GET)
    public String searchStudent(@ModelAttribute("lastName") String lastName,
                                @ModelAttribute("group") String group) {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Student Page");
        return "redirect:studentResult";
    }

    @RequestMapping(value = "/studentResult", method = RequestMethod.GET)
    public ModelAndView showResults(@ModelAttribute("lastName") String lastName,
                                    @ModelAttribute("group") String group) {

        ModelAndView model = new ModelAndView();
        List<Student> studentList = studentService.findByLastNameAndGroup(lastName, group);

        if (studentList.isEmpty()) {
            model.addObject("message", "No data available. Please change search criteria.");
        }
        model.addObject("students", studentList);
        model.setViewName("studentResult");
        return model;
    }

    @RequestMapping(value = "/student", params = "add", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") Student student,
                             @ModelAttribute("date") String date,
                             @ModelAttribute("selectedGroup") String selectedGroup,
                             @ModelAttribute("selectedTerm") String selectedTerm,
                             @ModelAttribute("selectedStatus") String selectedStatus,
                             Model model) {

        try {
            StudentGroup group = groupService.findByName(selectedGroup);
            Term term = termService.findByName(selectedTerm);
            Status status = statusService.findByName(selectedStatus);
            student.setAdmissionDate(Date.valueOf(date));
            student.setStudentGroup(group);
            student.setTerm(term);
            student.setStatus(status);
            studentService.create(student);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Student cannot be added");
        }
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("terms", termService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        return "student";
    }

    @RequestMapping(value = "/student", params = "update", method = RequestMethod.POST)
    public String updateStudent(@ModelAttribute("student") Student student,
                                @ModelAttribute("date") String date,
                                @ModelAttribute("selectedGroup") String selectedGroup,
                                @ModelAttribute("selectedTerm") String selectedTerm,
                                @ModelAttribute("selectedStatus") String selectedStatus,
                                Model model) {

        try {
            StudentGroup group = groupService.findByName(selectedGroup);
            Term term = termService.findByName(selectedTerm);
            Status status = statusService.findByName(selectedStatus);
            student.setAdmissionDate(Date.valueOf(date));
            student.setStudentGroup(group);
            student.setTerm(term);
            student.setStatus(status);
            studentService.update(student);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Student cannot be updated");
        }
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("terms", termService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        return "student";
    }

    @RequestMapping(value = "/student", params = "delete", method = RequestMethod.POST)
    public String deleteStudent(@ModelAttribute("studentId") String studentId,
                                Model model) {

        try {
            Student st = studentService.findById(Long.valueOf(studentId));
            Status inactive = new Status();
            inactive.setStatusId(5L);
            inactive.setStatusName("inactive");
            st.setStatus(inactive);
            studentService.update(st);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Student cannot be deleted");
        }
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("terms", termService.findAll());
        model.addAttribute("statusList", statusService.findAll());
        return "student";
    }

    @RequestMapping(value = "/student", params = "show", method = RequestMethod.GET)
    public ModelAndView studentDetails(@ModelAttribute("studentId") String studentId,
                                       @ModelAttribute("student") Student student) {

        ModelAndView model = new ModelAndView();
        student = studentService.findById(Long.valueOf(studentId));
        model.addObject("student", student);
        model.setViewName("redirect:student/journal");
        return model;
    }

    @RequestMapping(value = "/student/journal", method = RequestMethod.GET)
    public ModelAndView journalDetails(@ModelAttribute("studentId") String studentId) {

        ModelAndView model = new ModelAndView();
        Student student = studentService.findById(Long.valueOf(studentId));
        model.addObject("student", student);
        model.addObject("terms", termService.findAll());
        model.setViewName("studentJournal");
        return model;
    }

    @RequestMapping(value = "/student/journal", params = "operation", method = RequestMethod.GET)
    public ModelAndView showDetails(@ModelAttribute("studentId") String studentId,
                                    @ModelAttribute("term") String term) {

        ModelAndView model = new ModelAndView();
        Term t = termService.findByName(term);
        model.addObject("selectedTerm", t);
        model.addObject("student", studentService.findById(Long.valueOf(studentId)));
        model.addObject("journals", journalService.findByStudentAndTerm(Long.valueOf(studentId), t.getTermId()));
        model.addObject("terms", termService.findAll());
        model.addObject("score", journalService.getAverageScore(Long.valueOf(studentId), t.getTermId()));
        model.setViewName("studentJournal");
        return model;
    }
}
