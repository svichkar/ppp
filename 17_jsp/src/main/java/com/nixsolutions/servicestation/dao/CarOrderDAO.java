package com.nixsolutions.servicestation.dao;

import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.entity.extendedentity.UserCarOrder;

import java.util.List;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public interface CarOrderDAO extends JointDAO<CarOrder>{
    List<UserCarOrder> getUserCarOrders(String login);
    List<UserCarOrder> getUserCarOrders();
}
