
package com.nixsolutions.servicestation.controllers;

import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.entity.Employees;
import com.nixsolutions.servicestation.service.EmployeeCategoryService;
import com.nixsolutions.servicestation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


/**
 * Created by rybkinrolla on 14.01.2016.
 */

@Controller
public class WorkersController {
    private static final String URL = "http://localhost:8080/services/worker";
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeCategoryService employeeCategoryService;


    @RequestMapping(value = "/workers", method = RequestMethod.GET)
    protected String getWorkers(Model model) {
        fillPage(model);
        return "workers";
    }

    @RequestMapping(value = "/workers", method = RequestMethod.POST)
    protected String crudWorkers(@RequestParam(value = "first_name") String firstName,
                                 @RequestParam(value = "last_name") String lastName,
                                 @RequestParam(value = "worker_id", required = false) Long workerId,
                                 @RequestParam(value = "category") Long categoryId,
                                 @RequestParam(value = "submitButton") String submitButton,
                                 Model model) {

        if (submitButton.equals("delete")) {
            restTemplate = new RestTemplate();
            restTemplate.delete(URL + "/{id}", employeeService.prepareEmployee(firstName, lastName, workerId, categoryId).getEmployeeId());
            model.addAttribute("msg", "Row with worker_id = " + workerId + " was deleted");
        }
        if (submitButton.equals("edit")) {
            restTemplate = new RestTemplate();
            restTemplate.put(URL + "/update", employeeService.prepareEmployee(firstName, lastName, workerId, categoryId));
            model.addAttribute("msg", "Row with worker_id = " + workerId + " was edited");
        }
        if (submitButton.equals("add")) {
            restTemplate = new RestTemplate();
            restTemplate.postForEntity(URL + "/create", employeeService.prepareEmployee(firstName, lastName, workerId, categoryId), Employee.class);
            model.addAttribute("msg", "New row was created");
        }
        fillPage(model);
        return "workers";
    }

    private void fillPage(Model model) {
        model.addAttribute("employeeSet", new RestTemplate().getForObject(URL + "/findAll", Employees.class));
        model.addAttribute("employeeCategorySet", employeeCategoryService.findAll());
    }
}

