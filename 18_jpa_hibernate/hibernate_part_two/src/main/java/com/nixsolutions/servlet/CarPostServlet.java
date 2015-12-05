package com.nixsolutions.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;

@WebServlet("/carPost.do")
public class CarPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static CarDAO carDao;
	private static CustomerDAO customerDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		userDao = daoFactory.getUserDAO();
		carDao = daoFactory.getCarDAO();
		customerDao = daoFactory.getCustomerDAO();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				String car_id = request.getParameter("id");
				String car_description = request.getParameter("description");
				String car_model = request.getParameter("model");
				String car_vin = request.getParameter("vin");
				String customer_id = request.getParameter("customer_id");
				if (car_id == null || car_id.equals("")) {
					Car car = new Car(car_model, car_vin, car_description, customerDao.getByPK(Integer.parseInt(customer_id)));
					carDao.createFrom(car);
				} else {
					Car car = carDao.getByPK(Integer.parseInt(car_id));
					car.setModel(car_model);
					car.setVin(car_vin);
					car.setDescription(car_description);
					car.setCustomer(customerDao.getByPK(Integer.parseInt(customer_id)));
					carDao.update(car);
				}
				request.setAttribute("target", "Cars");
				request.getRequestDispatcher("/nav.do").forward(request, response);
			} else {
				//
				response.sendRedirect("");
			}
		} else {
			//
			response.sendRedirect("");
		}
	}

}
