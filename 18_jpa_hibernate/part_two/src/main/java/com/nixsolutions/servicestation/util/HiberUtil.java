package com.nixsolutions.servicestation.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by rybkinrolla on 13.01.2016.
 */
public class HiberUtil {
    private static final SessionFactory sFactory = configureSessionFactory();

    private static SessionFactory configureSessionFactory() {
        Configuration cf = new Configuration();
        cf.configure("hibernate.cfg.xml");
        SessionFactory sf = cf.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cf.getProperties()).build());
        return sf;
    }

    public static SessionFactory getSessionFactory() {
        return sFactory;
    }

    public static void closeSessionFactory(){
        sFactory.close();
    }
}
