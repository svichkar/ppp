package com.nixsolutions.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.nixsolutions.entities.Customer;

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
	public CarCustomer findByPK(long car_id) {
		CarCustomer cc = new CarCustomer();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Car car = (Car) session.createCriteria(Car.class).add(Restrictions.eq("car_id", car_id)).uniqueResult();
		tx.commit();
		cc.setCustomer_id(car.getCustomer().getCustomerId());
		cc.setDescription(car.getDescription());
		cc.setF_name(car.getCustomer().getF_name());
		cc.setL_name(car.getCustomer().getL_name());
		cc.setId(car.getCarId());
		cc.setModel(car.getModel());
		cc.setVin(car.getVin());

		return cc;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CarCustomer> getAll() {
		List<CarCustomer> listResult = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Car> lCar = session.createCriteria(Car.class).list();
		tx.commit();

		for (Car car : lCar) {
			CarCustomer cc = new CarCustomer();

			cc.setCustomer_id(car.getCustomer().getCustomerId());
			cc.setDescription(car.getDescription());
			cc.setF_name(car.getCustomer().getF_name());
			cc.setL_name(car.getCustomer().getL_name());
			cc.setId(car.getCarId());
			cc.setModel(car.getModel());
			cc.setVin(car.getVin());

			listResult.add(cc);
		}

		return listResult;
	}

}
