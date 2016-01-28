package com.nixsolutions.hibernate.util;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by kozlovskij on 1/26/2016.
 */
public class StartHibernate implements ServletContextListener {
    public  static final AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        context.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        context.stop();
    }

}
