package com.nixsolutions;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBApp {
    public static void main(String[] args) throws ClassNotFoundException {
	Class.forName("org.h2.Driver");
	Configuration configuration = new Configuration();
	configuration.configure("hibernate_config.xml");

	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	sessionFactory.openSession();
	sessionFactory.close();
    }
}
