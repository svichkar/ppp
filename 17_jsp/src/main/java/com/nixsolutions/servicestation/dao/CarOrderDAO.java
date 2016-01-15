package com.nixsolutions.servicestation.dao;

import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.entity.extendedentity.UserCarOrderBean;

import java.util.List;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public interface CarOrderDAO extends JointDAO<CarOrder>{
    List<UserCarOrderBean> getUserCarOrders(String login);
    List<UserCarOrderBean> getUserCarOrders();
}
