package com.nixsolutions.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderPart;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;

@WebServlet(urlPatterns = { "/addOrder.do", "/editOrder.do", "/deleteOrder.do" })
public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static RoleDAO roleDao;
	private static UserDAO userDao;
	private static OrderInWorkDAO orderDao;
	private static CarDAO carDao;
	private static OrderWorkerDAO orderWorkerDao;
	private static OrderPartDAO orderPartDao;
	private static PartDAO partDao;
	private static WorkerDAO workerDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		roleDao = daoFactory.getRoleDAO();
		userDao = daoFactory.getUserDAO();
		orderDao = daoFactory.getOrderInWorkDAO();
		carDao = daoFactory.getCarDAO();
		orderWorkerDao = daoFactory.getOrderWorkerDAO();
		orderPartDao = daoFactory.getOrderPartDAO();
		partDao = daoFactory.getPartDAO();
		workerDao = daoFactory.getWorkerDAO();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				request.setAttribute("action", "add");
				List<Car> carList = carDao.getAll();
				request.setAttribute("cars", carList);
				List<Part> partList = partDao.getAll();
				request.setAttribute("parts", partList);
				List<Worker> workerList = workerDao.getAll();
				request.setAttribute("workers", workerList);
				request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
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
		String order_id = (String) request.getParameter("order_id");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				if (action.equals("Edit")) {
					OrderInWork order = orderDao.getByPK(Integer.parseInt(order_id));
					request.setAttribute("order", order);
					request.setAttribute("action", "edit");
					List<Car> carList = carDao.getAll();
					request.setAttribute("cars", carList);
					List<Part> partList = partDao.getAll();
					request.setAttribute("parts", partList);
					List<Worker> workerList = workerDao.getAll();
					request.setAttribute("workers", workerList);
					request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
				} else {
					OrderInWork order = orderDao.getByPK(Integer.parseInt(order_id));
					List<OrderWorker> owList = orderWorkerDao.getByOrderId(order.getId());
					for (OrderWorker ow : owList) {
						orderWorkerDao.delete(ow);
					}
					List<OrderPart> opList = orderPartDao.getByOrderId(order.getId());
					for (OrderPart op : opList) {
						orderPartDao.delete(op);
					}
					orderDao.delete(order);
					request.setAttribute("target", "Orders");
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
