package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarOrderDAO;
import com.nixsolutions.servicestation.entity.CarOrder;
import org.springframework.stereotype.Repository;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
@Repository("carOrderDao")
public class CarOrderDAOImpl extends GenericAbstractDAO<CarOrder> implements CarOrderDAO {

}
