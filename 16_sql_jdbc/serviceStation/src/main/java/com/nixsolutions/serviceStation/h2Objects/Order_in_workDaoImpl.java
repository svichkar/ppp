/**
 * 
 */
package com.nixsolutions.serviceStation.h2Objects;

import java.util.List;

import com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao;
import com.nixsolutions.serviceStation.dbObjects.Car;
import com.nixsolutions.serviceStation.dbObjects.Order_in_work;

/**
 * @author mixeyes
 *
 */
public class Order_in_workDaoImpl implements Order_in_workDao {

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#createNewTable()
	 */
	@Override
	public void createNewTable() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dbCommon.DBTables#deleteTableWithAllData()
	 */
	@Override
	public void deleteTableWithAllData() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getAllOrders()
	 */
	@Override
	public List<Order_in_work> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getOrderByCar()
	 */
	@Override
	public Order_in_work getOrderByCar() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getOrderByCustomer()
	 */
	@Override
	public Order_in_work getOrderByCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#getOrderByID()
	 */
	@Override
	public Order_in_work getOrderByID() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#createNewOrder(com.nixsolutions.serviceStation.dbObjects.Car, java.lang.String)
	 */
	@Override
	public void createNewOrder(Car car, String description) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.nixsolutions.serviceStation.dAOFabrica.Order_in_workDao#setOrderStatus(java.lang.String, com.nixsolutions.serviceStation.dbObjects.Order_in_work)
	 */
	@Override
	public void setOrderStatus(String status, Order_in_work order_in_work) {
		// TODO Auto-generated method stub

	}

}
