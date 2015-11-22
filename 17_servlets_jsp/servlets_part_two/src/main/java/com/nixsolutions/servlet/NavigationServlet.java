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
import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dao.WorkerSpecializationDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.Status;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.entity.WorkerSpecialization;

@WebServlet("/nav.do")
public class NavigationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static RoleDAO roleDao;
	private static UserDAO userDao;
	private static OrderInWorkDAO orderDao;
	private static OrderStatusDAO orderStatusDao;
	private static CarDAO carDao;
	private static CustomerDAO customerDao;
	private static PartDAO partDao;
	private static WorkerDAO workerDao;
	private static StatusDAO statusDao;
	private static WorkerSpecializationDAO workerSpecDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		roleDao = daoFactory.getRoleDAO();
		userDao = daoFactory.getUserDAO();
		orderDao = daoFactory.getOrderInWorkDAO();
		orderStatusDao = daoFactory.getOrderStatusDAO();
		carDao = daoFactory.getCarDAO();
		customerDao = daoFactory.getCustomerDAO();
		partDao = daoFactory.getPartDAO();
		workerDao = daoFactory.getWorkerDAO();
		statusDao = daoFactory.getStatusDAO();
		workerSpecDao = daoFactory.getWorkerSpecializationDAO();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
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
			Role role = roleDao.getByPK(user.getRoleId());
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
			ob.setOrderId(item.getId());
			ob.setOrderStatus(orderStatusDao.getByPK(item.getOrderStatusId()).getOrderStatusName());
			ob.setDescription(item.getDescription());
			Car c = carDao.getByPK(item.getCarId());
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
			cb.setCarId(item.getId());
			cb.setCarModel(item.getModel());
			cb.setCarVin(item.getVin());
			cb.setCarDescription(item.getDescription());
			Customer c = customerDao.getByPK(item.getCustomerId());
			cb.setCustomerName(c.getFirstName() + " " + c.getLastName());
			resultList.add(cb);
		}
		return resultList;
	}
	
	private List<WorkerBean> getWorkersAsBean(List<Worker> workerList) {
		List<WorkerBean> resultList = new ArrayList<>();
		for (Worker item : workerList) {
			WorkerBean wb = new WorkerBean();
			wb.setWorkerId(item.getId());
			wb.setFirstName(item.getFirstName());
			wb.setLastName(item.getLastName());
			Status s = statusDao.getByPK(item.getStatusId());
			wb.setStatus(s.getStatusName());
			WorkerSpecialization ws = workerSpecDao.getByPK(item.getSpecId());
			wb.setWorkerSpecialization(ws.getSpecName());
			resultList.add(wb);
		}
		return resultList;
	}
}
