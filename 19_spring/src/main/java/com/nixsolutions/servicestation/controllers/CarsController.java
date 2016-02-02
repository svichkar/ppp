
package com.nixsolutions.servicestation.controllers;

import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.entity.CarType;
import com.nixsolutions.servicestation.entity.Client;
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

import java.util.Set;


/**
 * Created by rybkinrolla on 15.01.2016.
 */

@Controller
public class CarsController {
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private CarOrderService carOrderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    protected String getCars(Model model) {
        model.addAttribute("carsSet", carService.findAll());
        model.addAttribute("clientsSet", clientService.findAll());
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
        Car car = new Car();
        Client client = new Client();
        car.setSerialVIN(serialVIN);
        if ((submitButton.equals("add")) || (submitButton.equals("edit"))) {
            Set<CarType> carTypeSet = carTypeService.findAll();
            CarType carType = new CarType();
            carType.setBrand(brand);
            carType.setModelName(modelName);
            if (!carTypeSet.contains(carType)) {
                carTypeService.create(carType);
                carTypeSet = carTypeService.findAll();
            }
            for (CarType ct : carTypeSet) {
                if (ct.getBrand().equals(brand) && ct.getModelName().equals(modelName)) {
                    carType.setCarTypeId(ct.getCarTypeId());
                    car.setCarType(carType);
                }
            }
            if (submitButton.equals("add")) {
                client.setClientId(clientId);
                car.setClient(client);
                carService.create(car);
                model.addAttribute("msg","New row was created");
            }
            if (submitButton.equals("edit")) {
                client.setClientId(clientIdForEdit);
                car.setClient(client);
                car.setCarId(carId);
                carService.update(car);
                model.addAttribute("msg", "Row with car_id = " + car.getCarId() + " was edited");
            }
        }
        if (submitButton.equals("delete")) {
            for (CarOrder co : carOrderService.findAll()) {
                if (co.getCar().getCarId().equals(carId)) {
                    carOrderService.delete(co);
                }
            }
            car.setCarId(carId);
            carService.delete(car);
            model.addAttribute("msg", "Row with car_id = " + car.getCarId() + " was deleted");
        }
        model.addAttribute("carsSet", carService.findAll());
        model.addAttribute("clientsSet", clientService.findAll());
        return "cars";
    }
}

