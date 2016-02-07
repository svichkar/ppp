package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarTypeDAO;
import com.nixsolutions.servicestation.entity.CarType;
import org.springframework.stereotype.Repository;

/**
 * Created by rybkinrolla on 29.12.2015.
 */

@Repository("carTypeDao")
public class CarTypeDAOImpl extends GenericAbstractDAO<CarType> implements CarTypeDAO {

}
