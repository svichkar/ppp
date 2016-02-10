package com.nixsolutions.servicestation.service.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.dao.CarOrderDAO;
import com.nixsolutions.servicestation.dao.CarOrderStatusDAO;
import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.service.CarOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
@Service("carOrderService")
@Transactional
public class CarOrderServiceImpl implements CarOrderService {
    @Autowired
    private CarOrderDAO carOrderDAO;
    @Autowired
    private CarOrderStatusDAO carOrderStatusDAO;
    @Autowired
    private CarDAO carDAO;

    @Override
    public void create(CarOrder entity) {
        carOrderDAO.create(entity);
    }

    @Override
    public void update(CarOrder entity) {
        carOrderDAO.update(entity);
    }

    @Override
    public void delete(CarOrder entity) {
        carOrderDAO.delete(entity);
    }

    @Override
    public CarOrder findById(Long id) {
        CarOrder carOrder = carOrderDAO.findById(id);
        return carOrder;
    }

    @Override
    public Set<CarOrder> findAll() {
        Set<CarOrder> carOrderSet = carOrderDAO.findAll();
        return carOrderSet;
    }

    public CarOrder prepareCarOrderForEdit(Long orderId,Long statusId){
        CarOrder carOrder = carOrderDAO.findById(orderId);
        carOrder.setCarOrderStatus(carOrderStatusDAO.findById(statusId));
        if (carOrder.getCarOrderStatus().getCarOrderStatusName().equals("Complete")) {
            carOrder.setEndDate(new Date());
        } else {
            carOrder.setEndDate(null);
        }
        return carOrder;
    }

    public CarOrder prepareCarOrderForAdd(Long carId,Long statusId){
        CarOrder carOrder = new CarOrder();
        carOrder.setCar(carDAO.findById(carId));
        carOrder.setStartDate(new Date());
        carOrder.setCarOrderStatus(carOrderStatusDAO.findById(statusId));
        if (carOrder.getCarOrderStatus().getCarOrderStatusName().equals("Complete")) {
            carOrder.setEndDate(new Date());
        } else {
            carOrder.setEndDate(null);
        }
        return carOrder;
    }
}
