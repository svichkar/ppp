package com.nixsolutions.hibernate;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();


	private static SessionFactory buildSessionFactory() {
		Configuration cf = new Configuration().configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder();
		srb.applySettings(cf.getProperties());
		ServiceRegistry sr = srb.build();
		SessionFactory sf = cf.buildSessionFactory(sr);
		return sf;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
