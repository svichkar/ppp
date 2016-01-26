package com.nixsolutions.controllers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nixsolutions.hibernate.util.HibernateUtil;

public class HibernateListener implements ServletContextListener {  

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory().close();
		
	}  
}  