package com.nixsolutions.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;

	static {
		try {
			Configuration config = new Configuration();
			sessionFactory = config.configure().buildSessionFactory(
					new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null)
			sessionFactory.close();
	}

}
