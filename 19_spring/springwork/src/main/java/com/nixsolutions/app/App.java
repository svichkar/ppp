package com.nixsolutions.app;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.hibernate.entity.User;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext(App.class.getClassLoader().getResource("Beans.xml").toString());
		SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");
		UserDAO userDao = (UserDAO) context.getBean("userDAO");
		User user = userDao.getByPK(1);
		((ConfigurableApplicationContext) context).close();
	}

}
