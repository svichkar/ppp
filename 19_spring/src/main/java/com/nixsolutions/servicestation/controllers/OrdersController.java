
package com.nixsolutions.servicestation.controllers;


import com.nixsolutions.servicestation.service.CarOrderService;
import com.nixsolutions.servicestation.service.CarOrderStatusService;
import com.nixsolutions.servicestation.service.CarService;
import com.nixsolutions.servicestation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by rybkinrolla on 18.01.2016.
 */

@Controller
public class OrdersController {
    @Autowired
    private CarOrderService carOrderService;
    @Autowired
    private CarOrderStatusService carOrderStatusService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    protected String getOrders(Model model) {
        fillPage(model);
        return "orders";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    protected String crudOrders(@RequestParam(value = "employees", required = false) Long employeeId,
                                @RequestParam(value = "orders", required = false) Long orderId,
                                @RequestParam(value = "carOrderId", required = false) Long orderIdForEdit,
                                @RequestParam(value = "cars", required = false) Long carId,
                                @RequestParam(value = "statuses2", required = false) Long status2Id,
                                @RequestParam(value = "statuses", required = false) Long statusId,
                                @RequestParam(value = "submitButton") String submitButton,
                                Model model) {
        if (submitButton.equals("reOrder")) {
            employeeService.update(employeeService.reorderEmployee(employeeId,orderId));
            model.addAttribute("msg","Employee with employee_id = " + employeeId + " was attached to " +
                    "order with order_id = " + orderId);
        }
        if (submitButton.equals("delete")) {
            carOrderService.delete(carOrderService.findById(orderIdForEdit));
            model.addAttribute("msg","Row with order_id = " + orderIdForEdit + " was deleted");
        }
        if (submitButton.equals("add")) {
            carOrderService.create(carOrderService.prepareCarOrderForAdd(carId,status2Id));
            model.addAttribute("msg","New row was created");
        }
        if (submitButton.equals("edit")) {
            carOrderService.update(carOrderService.prepareCarOrderForEdit(orderIdForEdit,statusId));
            model.addAttribute("msg","Row with order_id = " + orderIdForEdit + " was edited");
        }
        fillPage(model);
        return "orders";
    }

    private void fillPage(Model model){
        model.addAttribute("employeeSet", employeeService.findAll());
        model.addAttribute("cosSet", carOrderStatusService.findAll());
        model.addAttribute("coSet", carOrderService.findAll());
        model.addAttribute("cSet", carService.getCarWithoutOrder());
    }
}
