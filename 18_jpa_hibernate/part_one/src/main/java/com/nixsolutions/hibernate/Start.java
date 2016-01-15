package com.nixsolutions.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nixsolutions.entities.Car;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HibernateUtil hu = new HibernateUtil();
		SessionFactory sf = hu.getSessionFactory();
		Session ss = sf.getCurrentSession();
		Transaction tx = ss.beginTransaction();
		List<Car> lCar = ss.createCriteria(Car.class).list();
		tx.commit();
		///it works
		String carModel = lCar.get(0).getModel();
	}

}
