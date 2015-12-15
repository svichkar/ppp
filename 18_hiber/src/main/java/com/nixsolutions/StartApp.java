package com.nixsolutions;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class StartApp {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			sessionFactory.openSession();
			sessionFactory.close();
			
			
			
			
	}

}
