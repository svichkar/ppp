package com.nixsolutions.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.bean.OrderWorkerBean;
import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderPart;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;

@WebServlet(urlPatterns = { "/addOrderWorker.do", "/editOrderWorker.do", "/deleteOrderWorker.do" })
public class OrderWorkerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static RoleDAO roleDao;
	private static UserDAO userDao;
	private static OrderInWorkDAO orderDao;
	private static CarDAO carDao;
	private static OrderWorkerDAO orderWorkerDao;
	private static OrderPartDAO orderPartDao;
	private static PartDAO partDao;
	private static WorkerDAO workerDao;
	private static OrderStatusDAO orderStatusDao;

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
		orderStatusDao = daoFactory.getOrderStatusDAO();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		String order_id = (String) request.getParameter("order_id");
		String worker_id = (String) request.getParameter("worker_id");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				request.setAttribute("action", "add");
				List<Worker> workerList = workerDao.getAll();
				request.setAttribute("workers", workerList);
				List<OrderWorker> orderWorkerList = new ArrayList<OrderWorker>() {
					{
						add(orderWorkerDao.getByPK(Integer.parseInt(order_id), Integer.parseInt(worker_id)));
					}
				};
				request.setAttribute("worker", getOrderWorkerAsBean(orderWorkerList).get(0));

				request.getRequestDispatcher("/WEB-INF/jsp/orderWorker.jsp").forward(request, response);
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
		String worker_id = (String) request.getParameter("worker_id");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				if (action.equals("Edit")) {
					request.setAttribute("action", "edit");
					List<Worker> workerList = workerDao.getAll();
					request.setAttribute("workers", workerList);
					List<OrderWorker> orderWorkerList = new ArrayList<OrderWorker>() {
						{
							add(orderWorkerDao.getByPK(Integer.parseInt(order_id), Integer.parseInt(worker_id)));
						}
					};
					request.setAttribute("worker", getOrderWorkerAsBean(orderWorkerList).get(0));
					request.getRequestDispatcher("/WEB-INF/jsp/orderWorker.jsp").forward(request, response);
				} else {
					OrderWorker orderWorker = orderWorkerDao.getByPK(Integer.parseInt(order_id),
							Integer.parseInt(worker_id));
					orderWorkerDao.delete(orderWorker);
					//
					request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
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

	private List<OrderWorkerBean> getOrderWorkerAsBean(List<OrderWorker> orderWorkerList) {
		List<OrderWorkerBean> resultList = new ArrayList<>();
		for (OrderWorker item : orderWorkerList) {
			OrderWorkerBean owb = new OrderWorkerBean();
			owb.setOrderId(item.getId());
			owb.setWorkerId(item.getWorkerId());
			Worker w = workerDao.getByPK(item.getWorkerId());
			owb.setWorkerName(w.getFirstName() + " " + w.getLastName());
			owb.setIsCompleted(item.getIsCompleted());
			resultList.add(owb);
		}
		return resultList;
	}
}
