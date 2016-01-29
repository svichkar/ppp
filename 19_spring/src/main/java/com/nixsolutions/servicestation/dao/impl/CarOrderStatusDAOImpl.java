package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarOrderStatusDAO;
import com.nixsolutions.servicestation.entity.CarOrderStatus;
import org.springframework.stereotype.Repository;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
@Repository("carOrderStatusDao")
public class CarOrderStatusDAOImpl extends GenericAbstractDAO<CarOrderStatus> implements CarOrderStatusDAO{

}