package com.nixsolutions.app;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Status;
import com.nixsolutions.hibernate.entity.User;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext(App.class.getClassLoader().getResource("Beans.xml").toString());
		SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");
		UserDAO userDao = (UserDAO) context.getBean("userDAO");
		User user = userDao.getByPK(1);
		CarDAO carDao = (CarDAO) context.getBean("carDAO"); 
		Car car = carDao.getByPK(1);
		car.setDescription("test");
		carDao.update(car);
		List<Car> carList = carDao.getAll();
		Car tCar = new Car("tt", "tt", "tt", null);
		carDao.createFrom(tCar);
		carList = carDao.getAll();
		carDao.delete(tCar);
		carList = carDao.getAll();
		StatusDAO statusDao = (StatusDAO) context.getBean("statusDAO");
		Status s = statusDao.getByPK(1);
		((ConfigurableApplicationContext) context).close();
	}

}
