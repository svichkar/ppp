package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */

@Service("carService")
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDAO carDAO;

    public Set<Car> getCarWithoutOrder() {
        Set<Car> carSet = carDAO.getCarWithoutOrder();
        return carSet;
    }

    public Set<Car> getUserCarOrders(String login) {
        Set<Car> carSet = carDAO.getUserCarOrders(login);
        return carSet;
    }

    @Override
    public void create(Car entity) {
        carDAO.create(entity);
    }

    @Override
    public void update(Car entity) {
        carDAO.update(entity);
    }

    @Override
    public void delete(Car entity) {
        carDAO.delete(entity);
    }

    @Override
    public Car findById(Long id) {
        Car car = carDAO.findById(id);
        return car;
    }

    @Override
    public Set<Car> findAll() {
        Set<Car> carSet = carDAO.findAll();
        return carSet;
    }
}
