package com.nixsolutions.studentgrade.controller;

import com.nixsolutions.studentgrade.model.Term;
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

/**
 * Created by konstantin on 2/2/2016.
 */
@Controller
public class TermController {

    private static final Logger LOG = LogManager.getLogger(TermController.class);

    @Autowired
    TermService termService;

    @RequestMapping(value = "/term", method = RequestMethod.GET)
    public ModelAndView termPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("terms", termService.findAll());
        model.setViewName("term");
        return model;
    }

    @RequestMapping(value = "/term", params = "add", method = RequestMethod.POST)
    public String addTerm(@ModelAttribute("term") Term term, Model model) {
        try {
            termService.create(term);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Term cannot be added");
        }
        model.addAttribute("terms", termService.findAll());
        return "term";
    }

    @RequestMapping(value = "/term", params = "update", method = RequestMethod.POST)
    public String updateTerm(@ModelAttribute("term") Term term, Model model) {
        try {
            termService.update(term);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Term cannot be updated");
        }
        model.addAttribute("terms", termService.findAll());
        return "term";
    }

    @RequestMapping(value = "/term", params = "delete", method = RequestMethod.POST)
    public String deleteTerm(@ModelAttribute("term") Term term, Model model) {
        try {
            termService.delete(term);
            model.addAttribute("message", "Successes");
            model.addAttribute("color", "color:#15DC13");
        } catch (Exception e) {
            LOG.error(e);
            model.addAttribute("message", "Term cannot be deleted");
        }
        model.addAttribute("terms", termService.findAll());
        return "term";
    }
}
