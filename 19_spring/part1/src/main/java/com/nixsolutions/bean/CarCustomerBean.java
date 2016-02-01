package com.nixsolutions.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.nixsolutions.app.HibernateUtil;
import com.nixsolutions.dao.CarCustomerDAO;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.CarCustomer;

public class CarCustomerBean implements CarCustomerDAO<CarCustomer> {

	private final static Logger LOG = LogManager.getLogger(CarCustomerBean.class);
	public static SessionFactory sessionFactory;

	public CarCustomerBean() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Deprecated
	@Override
	public void create(CarCustomer t) {

	}

	@Deprecated
	@Override
	public void update(CarCustomer t) {

	}

	@Deprecated
	@Override
	public void delete(CarCustomer t) {

	}

	@Override
	public CarCustomer findByPK(long carid) {
		CarCustomer carCustomer = null;
		Car car = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			car = (Car) session.createCriteria(Car.class).add(Restrictions.eq("car_id", carid)).uniqueResult();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}
		if (car != null) {
			carCustomer = new CarCustomer();
			carCustomer.setCustomer_id(car.getCustomer().getCustomerId());
			carCustomer.setDescription(car.getDescription());
			carCustomer.setF_name(car.getCustomer().getF_name());
			carCustomer.setL_name(car.getCustomer().getL_name());
			carCustomer.setId(car.getCarId());
			carCustomer.setModel(car.getModel());
			carCustomer.setVin(car.getVin());
		}

		return carCustomer;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CarCustomer> getAll() {
		List<CarCustomer> results = new ArrayList<>();
		List<Car> cars = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			cars.addAll(session.createCriteria(Car.class).list());
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
				session.close();
			}
		}

		for (Car car : cars) {
			CarCustomer carCustomer = new CarCustomer();
			carCustomer.setCustomer_id(car.getCustomer().getCustomerId());
			carCustomer.setDescription(car.getDescription());
			carCustomer.setF_name(car.getCustomer().getF_name());
			carCustomer.setL_name(car.getCustomer().getL_name());
			carCustomer.setId(car.getCarId());
			carCustomer.setModel(car.getModel());
			carCustomer.setVin(car.getVin());
			results.add(carCustomer);
		}

		return results;
	}

}
