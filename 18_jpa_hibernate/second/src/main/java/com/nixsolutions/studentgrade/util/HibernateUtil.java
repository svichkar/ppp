package com.nixsolutions.studentgrade.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by svichkar on 1/13/2016.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        return hibernateUtil.sessionFactory;
    }
}
