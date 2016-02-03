
package com.nixsolutions.servicestation.controllers;


import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.entity.CarOrderStatus;
import com.nixsolutions.servicestation.entity.Employee;
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

import java.util.Date;
import java.util.Set;


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
            Employee employee = employeeService.findById(employeeId);
            CarOrder carOrder = carOrderService.findById(orderId);
            Set<CarOrder> carOrderSet = employee.getCarOrderSet();
            carOrderSet.add(carOrder);
            employee.setCarOrderSet(carOrderSet);
            employeeService.update(employee);
            model.addAttribute("msg","Employee with employee_id = " + employee.getEmployeeId() + " was attached to " +
                    "order with order_id = " + carOrder.getCarOrderId());
        }
        if (submitButton.equals("delete")) {
            CarOrder carOrder = carOrderService.findById(orderIdForEdit);
            carOrderService.delete(carOrder);
            model.addAttribute("msg","Row with order_id = " + carOrder.getCarOrderId() + " was deleted");
        }
        if (submitButton.equals("add")) {
            CarOrder carOrder = new CarOrder();
            Car car = carService.findById(carId);
            carOrder.setCar(car);
            carOrder.setStartDate(new Date());
            CarOrderStatus carOrderStatus = carOrderStatusService.findById(status2Id);
            carOrder.setCarOrderStatus(carOrderStatus);
            carOrderService.create(carOrder);
            model.addAttribute("msg","New row was created");
        }
        if (submitButton.equals("edit")) {
            CarOrder carOrder = carOrderService.findById(orderIdForEdit);
            CarOrderStatus carOrderStatus = carOrderStatusService.findById(statusId);
            carOrder.setCarOrderStatus(carOrderStatus);
            if (carOrder.getCarOrderStatus().getCarOrderStatusName().equals("Complete")) {
                carOrder.setEndDate(new Date());
            } else {
                carOrder.setEndDate(null);
            }
            carOrderService.update(carOrder);
            model.addAttribute("msg","Row with order_id = " + carOrder.getCarOrderId() + " was edited");
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
