package com.nixsolutions.servicestation.dao;

import com.nixsolutions.servicestation.entity.Car;

import java.util.List;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public interface CarDAO extends GenericDAO<Car> {
    List<Car> getUserCars();
    List<Car> getCarWithoutOrder();
}
