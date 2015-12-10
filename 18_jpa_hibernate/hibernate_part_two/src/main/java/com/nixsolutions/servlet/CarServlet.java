package com.nixsolutions.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.hibernate.DaoFactoryHibernate;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.hibernate.entity.OrderWorker;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;

@WebServlet(urlPatterns = { "/addCar.do", "/editCar.do", "/deleteCar.do" })
public class CarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static OrderInWorkDAO orderDao;
	private static CarDAO carDao;
	private static OrderWorkerDAO orderWorkerDao;
	private static OrderPartDAO orderPartDao;
	private static CustomerDAO customerDao;

	@Override
	public void init() {
		DaoFactoryHibernate daoFactory = new DaoFactoryHibernate();
		userDao = daoFactory.getUserDAO();
		orderDao = daoFactory.getOrderInWorkDAO();
		carDao = daoFactory.getCarDAO();
		orderWorkerDao = daoFactory.getOrderWorkerDAO();
		orderPartDao = daoFactory.getOrderPartDAO();
		customerDao = daoFactory.getCustomerDAO();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				request.setAttribute("action", "add");
				List<Customer> customerList = customerDao.getAll();
				request.setAttribute("customers", customerList);
				request.getRequestDispatcher("/WEB-INF/jsp/car.jsp").forward(request, response);
			} else {
				//
				response.sendRedirect("");
			}
		} else {
			//
			response.sendRedirect("");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		String action = (String) request.getParameter("action");
		String car_id = (String) request.getParameter("car_id");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				if (action.equals("Edit")) {
					Car car = carDao.getByPK(Integer.parseInt(car_id));
					request.setAttribute("car", car);
					request.setAttribute("action", "edit");
					List<Customer> customerList = customerDao.getAll();
					request.setAttribute("customers", customerList);
					request.getRequestDispatcher("/WEB-INF/jsp/car.jsp").forward(request, response);
				} else {
					Car car = carDao.getByPK(Integer.parseInt(car_id));
					List<OrderInWork> orderList = orderDao.getOrdersByCarId(Integer.parseInt(car_id));
					for (OrderInWork order : orderList) {
						List<OrderWorker> owList = orderWorkerDao.getByOrderId(order.getOrderId());
						for (OrderWorker ow : owList) {
							orderWorkerDao.delete(ow);
						}
						List<OrderPart> opList = orderPartDao.getByOrderId(order.getOrderId());
						for (OrderPart op : opList) {
							orderPartDao.delete(op);
						}
						orderDao.delete(order);
					}
					carDao.delete(car);
					request.setAttribute("target", "Cars");
					request.getRequestDispatcher("/nav.do").forward(request, response);
				}
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
