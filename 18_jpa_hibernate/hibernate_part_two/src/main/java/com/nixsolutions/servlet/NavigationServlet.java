package com.nixsolutions.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.bean.CarBean;
import com.nixsolutions.bean.OrderBean;
import com.nixsolutions.bean.WorkerBean;
import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.Status;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.entity.Worker;
import com.nixsolutions.hibernate.entity.WorkerSpecialization;

@WebServlet("/nav.do")
public class NavigationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static OrderInWorkDAO orderDao;
	private static CarDAO carDao;
	private static CustomerDAO customerDao;
	private static PartDAO partDao;
	private static WorkerDAO workerDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		userDao = daoFactory.getUserDAO();
		orderDao = daoFactory.getOrderInWorkDAO();
		carDao = daoFactory.getCarDAO();
		customerDao = daoFactory.getCustomerDAO();
		partDao = daoFactory.getPartDAO();
		workerDao = daoFactory.getWorkerDAO();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				List<OrderInWork> orderList = orderDao.getAll();
				request.setAttribute("orders", getOrdersAsBean(orderList));
				request.getRequestDispatcher("/WEB-INF/jsp/ordersPage.jsp").forward(request, response);
			} else if (role.getRoleName().equals("User")) {
				List<OrderInWork> orderList = orderDao.getOrdersByUser(user);
				request.setAttribute("orders", getOrdersAsBean(orderList));
				request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp").forward(request, response);
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
		String target = (String) request.getParameter("target");
		target = target != null ? target : (String) request.getAttribute("target"); 
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				if (target == null || target.equals("Orders")) {
					List<OrderInWork> orderList = orderDao.getAll();
					request.setAttribute("orders", getOrdersAsBean(orderList));
					request.getRequestDispatcher("/WEB-INF/jsp/ordersPage.jsp").forward(request, response);
				} else if (target.equals("Cars")) {
					List<Car> carList = carDao.getAll();
					request.setAttribute("cars", getCarsAsBean(carList));
					request.getRequestDispatcher("WEB-INF/jsp/carsPage.jsp").forward(request, response);
				} else if (target.equals("Customers")) {
					List<Customer> customerList = customerDao.getAll();
					request.setAttribute("customers", customerList);
					request.getRequestDispatcher("WEB-INF/jsp/customersPage.jsp").forward(request, response);
				} else if (target.equals("Parts")) {
					List<Part> partList = partDao.getAll();
					request.setAttribute("parts", partList);
					request.getRequestDispatcher("WEB-INF/jsp/partsPage.jsp").forward(request, response);
				} else if (target.equals("Workers")) {
					List<Worker> workerList = workerDao.getAll();
					request.setAttribute("workers", getWorkersAsBean(workerList));
					request.getRequestDispatcher("WEB-INF/jsp/workersPage.jsp").forward(request, response);
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

	private List<OrderBean> getOrdersAsBean(List<OrderInWork> orderList) {
		List<OrderBean> resultList = new ArrayList<>();
		for (OrderInWork item : orderList) {
			OrderBean ob = new OrderBean();
			ob.setOrderId(item.getOrderId());
			ob.setOrderStatus(item.getOrderStatus().getOrderStatusName());
			ob.setDescription(item.getDescription());
			Car c = item.getCar();
			ob.setCarVin(c.getVin());
			ob.setCarModel(c.getModel());
			ob.setCarDescription(c.getDescription());
			ob.setTimestampStart(item.getTimestampStart());
			ob.setTimestampFinish(item.getTimestampFinish());
			resultList.add(ob);
		}
		return resultList;
	}
	
	private List<CarBean> getCarsAsBean(List<Car> carList) {
		List<CarBean> resultList = new ArrayList<>();
		for (Car item : carList) {
			CarBean cb = new CarBean();
			cb.setCarId(item.getCarId());
			cb.setCarModel(item.getModel());
			cb.setCarVin(item.getVin());
			cb.setCarDescription(item.getDescription());
			Customer c = item.getCustomer();
			cb.setCustomerName(c.getFirstName() + " " + c.getLastName());
			resultList.add(cb);
		}
		return resultList;
	}
	
	private List<WorkerBean> getWorkersAsBean(List<Worker> workerList) {
		List<WorkerBean> resultList = new ArrayList<>();
		for (Worker item : workerList) {
			WorkerBean wb = new WorkerBean();
			wb.setWorkerId(item.getWorkerId());
			wb.setFirstName(item.getFirstName());
			wb.setLastName(item.getLastName());
			Status s = item.getStatus();
			wb.setStatus(s.getStatusName());
			WorkerSpecialization ws = item.getWorkerSpecialization();
			wb.setWorkerSpecialization(ws.getSpecializationName());
			resultList.add(wb);
		}
		return resultList;
	}
}
