package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.dao.CarOrderDAO;
import com.nixsolutions.servicestation.dao.CarTypeDAO;
import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.entity.CarType;
import com.nixsolutions.servicestation.entity.Client;
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
    private CarOrderDAO carOrderDAO;
    @Autowired
    private CarDAO carDAO;
    @Autowired
    private CarTypeDAO carTypeDAO;

    public Set<Car> getCarWithoutOrder() {
        Set<Car> carSet = carDAO.getCarWithoutOrder();
        return carSet;
    }

    public Set<Car> getUserCarOrders(String login) {
        Set<Car> carSet = carDAO.getUserCarOrders(login);
        return carSet;
    }

    @Override
    public void updateCarTypeAndCar(String brand, String modelName, Long clientId, String serialVIN, Long carId) {
        Car car = new Car();
        car.setSerialVIN(serialVIN);
        Client client = new Client();
        Set<CarType> carTypeSet = carTypeDAO.findAll();
        CarType carType = new CarType();
        carType.setBrand(brand);
        carType.setModelName(modelName);
        if (!carTypeSet.contains(carType)) {
            carTypeDAO.create(carType);
            carTypeSet.add(carType);
        }
        for (CarType ct : carTypeSet) {
            if (ct.equals(carType)) {
                carType.setCarTypeId(ct.getCarTypeId());
                car.setCarType(carType);
            }
        }
        client.setClientId(clientId);
        car.setClient(client);
        car.setCarId(carId);
        carDAO.update(car);
    }
    @Override
    public void createCarTypeAndCar(String brand, String modelName, Long clientId, String serialVIN) {
        Car car = new Car();
        car.setSerialVIN(serialVIN);
        Client client = new Client();
        Set<CarType> carTypeSet = carTypeDAO.findAll();
        CarType carType = new CarType();
        carType.setBrand(brand);
        carType.setModelName(modelName);
        if (!carTypeSet.contains(carType)) {
            carTypeDAO.create(carType);
            carTypeSet.add(carType);
        }
        for (CarType ct : carTypeSet) {
            if (ct.equals(carType)) {
                carType.setCarTypeId(ct.getCarTypeId());
                car.setCarType(carType);
            }
        }
        client.setClientId(clientId);
        car.setClient(client);
        carDAO.create(car);
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
        for (CarOrder co : carOrderDAO.findAll()) {
            if (co.getCar().getCarId().equals(entity.getCarId())) {
                carOrderDAO.delete(co);
            }
        }
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

    private void fillObjects(){

    }
}
