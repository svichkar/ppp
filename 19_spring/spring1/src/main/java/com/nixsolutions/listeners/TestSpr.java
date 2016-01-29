package com.nixsolutions.listeners;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;


public class TestSpr {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("persistence-beans.xml");
		SessionFactory factory = (SessionFactory) context.getBean("sessionFactory");
		
		Session session = factory.getCurrentSession();
		
		session.getClass();
	}
}
