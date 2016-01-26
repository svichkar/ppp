package com.nixsolutions.servicestation.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by rybkinrolla on 20.01.2016.
 */
public class HibernateAppListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        HiberUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HiberUtil.closeSessionFactory();
    }
}
