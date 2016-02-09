package com.nixsolutions.servicestation.service;

import com.nixsolutions.servicestation.entity.Car;

import java.util.Set;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public interface CarService extends GenericService<Car> {
    Set<Car> getCarWithoutOrder();

    Set<Car> getUserCarOrders(String login);

    void createCarTypeAndCar(String brand, String modelName, Long clientId, String serialVIN);

    void updateCarTypeAndCar(String brand, String modelName, Long clientId, String serialVIN, Long carId);
}
