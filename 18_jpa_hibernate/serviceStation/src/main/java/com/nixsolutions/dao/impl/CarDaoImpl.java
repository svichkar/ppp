/**
 * 
 */
package com.nixsolutions.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.dao.CarDao;
import com.nixsolutions.entity.Car;
import com.nixsolutions.util.HibernateUtil;

/**
 * @author mixeyes
 *
 */
public class CarDaoImpl implements CarDao {

	private final static Logger logger = LogManager.getLogger();

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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Car> carList = null;
		Transaction tx = session.beginTransaction();
		try {
			carList = session.createCriteria(Car.class).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
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
	public Car getCarByVINNumber(String vin_number) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Car car = null;
		Transaction tx = session.beginTransaction();
		try {
			car = (Car) session.createCriteria(Car.class).add(Restrictions.eq("vin_number", vin_number)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
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
	public Car getCarByRegNumber(String reg_number) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Car car = new Car();
		Transaction tx = session.beginTransaction();
		try {
			car = (Car) session.createCriteria(Car.class).add(Restrictions.eq("regNumber", reg_number)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
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
	public Car getCarByID(Integer car_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Car car = null;
		Transaction tx = session.beginTransaction();
		try {
			car = (Car) session.createCriteria(Car.class).add(Restrictions.eq("carId", car_id)).uniqueResult();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
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
	public List<Car> getCarsByCustomerName(String last_name, String first_name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Car> carList = null;
		Transaction tx = session.beginTransaction();
		try {
			carList = session.createCriteria(Car.class).add(Restrictions.eq("customer.last_name", last_name))
					.add(Restrictions.eq("customer.first_name", first_name)).list();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		} finally {
			if (session.isOpen())
				session.close();
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(car);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
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
	public boolean deleteCarByVINNumber(String vin_number) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Car car = getCarByVINNumber(vin_number);
		Transaction tx = session.beginTransaction();
		try {

			session.delete(car);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}
		car = getCarByVINNumber(vin_number);
		if (car == null)
			return true;
		else
			return false;

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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(car);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			logger.error(ex);
		}

	}
}
