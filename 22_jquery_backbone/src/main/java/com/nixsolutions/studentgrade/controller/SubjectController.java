package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.Subject;
import com.nixsolutions.studentgrade.model.Term;
import com.nixsolutions.studentgrade.service.SubjectService;
import com.nixsolutions.studentgrade.service.TermService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by konstantin on 2/2/2016.
 */
@Controller
public class SubjectController {

    private static final Logger LOG = LogManager.getLogger(SubjectController.class);

    @Autowired
    SubjectService subjectService;

    @Autowired
    TermService termService;

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public ModelAndView subjectPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Subject Page");
        model.addObject("subjects", subjectService.findAll());
        model.addObject("terms", termService.findAll());
        model.addObject("subjectForm", new Subject());
        model.setViewName("subject");
        return model;
    }

    @RequestMapping(value = "/subject", params = "operation", method = RequestMethod.GET)
    public String searchSubject(@ModelAttribute("subjectName") String subject,
                                @ModelAttribute("selectedTerm") String term) {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Subject Page");
        model.addObject("subjectForm", new Subject());
        return "redirect:subjectResult";
    }

    @RequestMapping(value = "/subjectResult", method = RequestMethod.GET)
    public ModelAndView showResults(@ModelAttribute("subjectName") String subject,
                                    @ModelAttribute("selectedTerm") String term) {
        ModelAndView model = new ModelAndView();
        List<Subject> subjectList = subjectService.findByNameAndTerm(subject, term);

        if (subjectList.isEmpty())
            model.addObject("message", "No data available. Please change search criteria.");

        model.addObject("subjects", subjectList);
        model.setViewName("subjectResult");
        return model;
    }

    @RequestMapping(value = "/subject", params = "add", method = RequestMethod.POST)
    public String addTerm(@ModelAttribute("subject") Subject subject,
                          @ModelAttribute("selectedTerm") String selectedTerm, Model model) {
        try {
            Term term = termService.findByName(selectedTerm);
            subject.setTerm(term);
            subjectService.create(subject);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Subject cannot be added");
        }
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("terms", termService.findAll());
        return "subject";
    }

    @RequestMapping(value = "/subject", params = "update", method = RequestMethod.POST)
    public String updateTerm(@ModelAttribute("subject") Subject subject,
                             @ModelAttribute("selectedTerm") String selectedTerm, Model model) {
        try {
            Term term = termService.findByName(selectedTerm);
            subject.setTerm(term);
            subjectService.update(subject);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Subject cannot be updated");
        }
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("terms", termService.findAll());
        return "subject";
    }

    @RequestMapping(value = "/subject", params = "delete", method = RequestMethod.POST)
    public String deleteTerm(@ModelAttribute("subjectForm") Subject subject, Model model) {
        try {
            subjectService.delete(subject);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            model.addAttribute("message", "Subject cannot be deleted");
        }
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("terms", termService.findAll());
        return "subject";
    }


}
