package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.CarOrderStatusDAO;
import com.nixsolutions.servicestation.entity.CarOrderStatus;
import com.nixsolutions.servicestation.service.CarOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
@Service("carOrderStatusService")
@Transactional
public class CarOrderStatusServiceImpl implements CarOrderStatusService {
    @Autowired
    private CarOrderStatusDAO carOrderStatusDAO;

    @Override
    public void create(CarOrderStatus entity) {
        carOrderStatusDAO.create(entity);
    }

    @Override
    public void update(CarOrderStatus entity) {
        carOrderStatusDAO.update(entity);
    }

    @Override
    public void delete(CarOrderStatus entity) {
        carOrderStatusDAO.delete(entity);
    }

    @Override
    public CarOrderStatus findById(Long id) {
        CarOrderStatus carOrderStatus = carOrderStatusDAO.findById(id);
        return carOrderStatus;
    }

    @Override
    public Set<CarOrderStatus> findAll() {
        Set<CarOrderStatus> carOrderStatusSet = carOrderStatusDAO.findAll();
        return carOrderStatusSet;
    }
}