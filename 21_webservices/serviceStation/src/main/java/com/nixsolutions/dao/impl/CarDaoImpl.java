/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nixsolutions.dao.CarDao;
import com.nixsolutions.entity.Car;

/**
 * @author mixeyes
 *
 */
@Repository("carDao")
@Transactional
public class CarDaoImpl implements CarDao {

	private final static Logger logger = LogManager.getLogger();
	@Autowired
	SessionFactory sessionFactory;

	/**
	 * get All Car from db
	 * 
	 * @return list of the car
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @see com.nixsolutions.dao.CarDao#getAllCar()
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getAllCar() {
		List<Car> carList = null;
		try {
			carList = sessionFactory.getCurrentSession().createCriteria(Car.class).list();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return carList;
	}

	/**
	 * get Car By VIN-Number
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarByVINNumber(java.
	 *      lang.String)
	 */
	@Override
	public Car getCarByVINNumber(String vinNumber) {
		Car car = null;
		try {
			car = (Car) sessionFactory.getCurrentSession().createCriteria(Car.class)
					.add(Restrictions.eq("vinNumber", vinNumber)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return car;
	}

	/**
	 * get Car By reg-Number
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarByVINNumber(java.
	 *      lang.String)
	 */
	@Override
	public Car getCarByRegNumber(String regNumber) {
		Car car = new Car();
		try {
			car = (Car) sessionFactory.getCurrentSession().createCriteria(Car.class)
					.add(Restrictions.eq("regNumber", regNumber)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return car;
	}

	/**
	 * get Car By VIN-Number
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarByVINNumber(java.
	 *      lang.String)
	 */
	@Override
	public Car getCarByID(Integer carId) {
		Car car = null;
		try {
			car = (Car) sessionFactory.getCurrentSession().createCriteria(Car.class)
					.add(Restrictions.eq("carId", carId)).uniqueResult();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return car;
	}

	/**
	 * get sqllab.car by sqllab.customer full name or sqllab.customer last name
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CarDao#getCarsByCustomerName(
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarsByCustomerName(String lastName, String firstName) {
		List<Car> carList = null;
		try {
			carList = sessionFactory.getCurrentSession().createCriteria(Car.class)
					.add(Restrictions.eq("customer.lastName", lastName))
					.add(Restrictions.eq("customer.firstName", firstName)).list();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return carList;
	}

	/**
	 * create new sqllab.car for existing customer
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CarDao#createNewCar(java.lang.
	 *      String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createNewCar(Car car) {
		try {
			sessionFactory.getCurrentSession().save(car);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	/**
	 * delete Car By VIN-Number
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CarDao#deleteCarByVINNumber(
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteCarByVINNumber(String vinNumber) {
		Car car = getCarByVINNumber(vinNumber);
		try {
			sessionFactory.getCurrentSession().delete(car);
		} catch (Exception ex) {
			logger.error(ex);
		}
		car = getCarByVINNumber(vinNumber);
		return car == null;

	}

	/**
	 * delete Car By VIN-Number
	 * 
	 * @see com.nixsolutions.serviceStation.dAOFabrica.CarDao#deleteCarByVINNumber(
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteCar(Car car) {
		try {
			sessionFactory.getCurrentSession().delete(car);
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.dao.CarDao#updateCarByVinNumber(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.Integer)
	 */
	@Override
	public void updateCarByID(Car car) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(car);
		} catch (Exception ex) {
			logger.error(ex);
		}

	}
}
