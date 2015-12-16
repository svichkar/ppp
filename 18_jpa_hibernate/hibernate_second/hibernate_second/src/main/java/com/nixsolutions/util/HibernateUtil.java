package com.nixsolutions.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
	if (sessionFactory == null) {
	    Configuration configuration = new Configuration();
	    configuration.configure("hibernate_config.xml");
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		    .applySettings(configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	return sessionFactory;
    }
    
    public static SessionFactory getSessionFactory(String configFileName) {
	if (sessionFactory == null) {
	    Configuration configuration = new Configuration();
	    configuration.configure(configFileName);
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		    .applySettings(configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	return sessionFactory;
    }

    public static void close() {
	sessionFactory.close();
    }
}
