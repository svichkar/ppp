package com.nixsolutions.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

@WebServlet("/carPost.do")
public class CarPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static RoleDAO roleDao;
	private static UserDAO userDao;
	private static CarDAO carDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		roleDao = daoFactory.getRoleDAO();
		userDao = daoFactory.getUserDAO();
		carDao = daoFactory.getCarDAO();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				String car_id = request.getParameter("id");
				String car_description = request.getParameter("description");
				String car_model = request.getParameter("model");
				String car_vin = request.getParameter("vin");
				String customer_id = request.getParameter("customer_id");
				if (car_id == null || car_id.equals("")) {
					Car car = new Car(car_model, car_vin, car_description, Integer.parseInt(customer_id));
					carDao.createFrom(car);
				} else {
					Car car = carDao.getByPK(Integer.parseInt(car_id));
					car.setModel(car_model);
					car.setVin(car_vin);
					car.setDescription(car_description);
					car.setCustomerId(Integer.parseInt(customer_id));
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
