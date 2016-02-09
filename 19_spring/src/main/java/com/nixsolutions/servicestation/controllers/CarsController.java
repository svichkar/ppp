
package com.nixsolutions.servicestation.controllers;

import com.nixsolutions.servicestation.service.CarOrderService;
import com.nixsolutions.servicestation.service.CarService;
import com.nixsolutions.servicestation.service.CarTypeService;
import com.nixsolutions.servicestation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by rybkinrolla on 15.01.2016.
 */

@Controller
public class CarsController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    protected String getCars(Model model) {
        fillPage(model);
        return "cars";
    }

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    protected String crudCars(@RequestParam(value = "VIN") String serialVIN,
                              @RequestParam(value = "brand") String brand,
                              @RequestParam(value = "model_name") String modelName,
                              @RequestParam(value = "clients", required = false) Long clientId,
                              @RequestParam(value = "client_id", required = false) Long clientIdForEdit,
                              @RequestParam(value = "car_id", required = false) Long carId,
                              @RequestParam(value = "submitButton") String submitButton,
                              Model model) {
        if (submitButton.equals("add")) {
            carService.createCarTypeAndCar(brand, modelName, clientId, serialVIN);
            model.addAttribute("msg", "New row was created");
        }
        if (submitButton.equals("edit")) {
            carService.updateCarTypeAndCar(brand, modelName, clientIdForEdit, serialVIN, carId);
            model.addAttribute("msg", "Row with car_id = " + carId + " was edited");
        }
        if (submitButton.equals("delete")) {
            carService.delete(carService.findById(carId));
            model.addAttribute("msg", "Row with car_id = " + carId + " was deleted");
        }
        fillPage(model);
        return "cars";
    }

    private void fillPage(Model model) {
        model.addAttribute("carsSet", carService.findAll());
        model.addAttribute("clientsSet", clientService.findAll());
    }
}

