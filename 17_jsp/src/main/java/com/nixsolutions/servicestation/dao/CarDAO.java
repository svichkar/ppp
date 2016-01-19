package com.nixsolutions.servicestation.dao;

import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.entity.extendedentity.CarBean;

import java.util.List;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public interface CarDAO extends JointDAO<Car>{
    List<CarBean> getUserCars();
    List<CarBean> getCarWithoutOrder();
}
