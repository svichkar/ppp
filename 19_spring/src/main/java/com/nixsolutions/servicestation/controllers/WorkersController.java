
package com.nixsolutions.servicestation.controllers;

import com.nixsolutions.servicestation.entity.Employee;
import com.nixsolutions.servicestation.entity.EmployeeCategory;
import com.nixsolutions.servicestation.service.EmployeeCategoryService;
import com.nixsolutions.servicestation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by rybkinrolla on 14.01.2016.
 */

@Controller
public class WorkersController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeCategoryService employeeCategoryService;

    @RequestMapping(value = "/workers", method = RequestMethod.GET)
    protected String getWorkers(Model model) {
        model.addAttribute("employeeSet", employeeService.findAll());
        model.addAttribute("employeeCategorySet", employeeCategoryService.findAll());
        return "workers";
    }

    @RequestMapping(value = "/workers", method = RequestMethod.POST)
    protected String crudWorkers(@RequestParam(value = "first_name") String firstName,
                                 @RequestParam(value = "last_name") String lastName,
                                 @RequestParam(value = "worker_id", required = false) Long workerId,
                                 @RequestParam(value = "category") Long categoryId,
                                 @RequestParam(value = "submitButton") String submitButton,
                                 Model model) {
        Employee employee = new Employee();
        EmployeeCategory employeeCategory = new EmployeeCategory();
        employeeCategory.setEmployeeCategoryId(categoryId);
        employee.setEmployeeCategory(employeeCategory);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        if (submitButton.equals("delete")) {
            employee.setEmployeeId(workerId);
            employeeService.delete(employee);
            model.addAttribute("msg","Row with worker_id = " + employee.getEmployeeId() + " was deleted");
        }
        if (submitButton.equals("edit")) {
            employee.setEmployeeId(workerId);
            employeeService.update(employee);
            model.addAttribute("msg","Row with worker_id = " + employee.getEmployeeId() + " was edited");
        }
        if (submitButton.equals("add")) {
            employeeService.create(employee);
            model.addAttribute("msg","New row was created");
        }
        model.addAttribute("employeeSet", employeeService.findAll());
        model.addAttribute("employeeCategorySet", employeeCategoryService.findAll());
        return "workers";
    }
}

