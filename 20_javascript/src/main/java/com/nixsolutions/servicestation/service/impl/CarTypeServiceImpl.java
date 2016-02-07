package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.CarTypeDAO;
import com.nixsolutions.servicestation.entity.CarType;
import com.nixsolutions.servicestation.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by rybkinrolla on 29.12.2015.
 */

@Service("carTypeService")
@Transactional
public class CarTypeServiceImpl implements CarTypeService {
    @Autowired
    CarTypeDAO carTypeDAO;

    @Override
    public void create(CarType entity) {
        carTypeDAO.create(entity);
    }

    @Override
    public void update(CarType entity) {
        carTypeDAO.update(entity);
    }

    @Override
    public void delete(CarType entity) {
        carTypeDAO.delete(entity);
    }

    @Override
    public CarType findById(Long id) {
        CarType carType = carTypeDAO.findById(id);
        return carType;
    }

    @Override
    public Set<CarType> findAll() {
        Set<CarType> carTypeSet = carTypeDAO.findAll();
        return carTypeSet;
    }
}
