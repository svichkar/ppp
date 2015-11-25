package com.nixsolutions.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.bean.OrderPartBean;
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
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;

@WebServlet("/orderWorkerPost.do")
public class OrderWorkerPostServlet extends HttpServlet {

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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				String order_id = request.getParameter("order_id");
				String worker_id = request.getParameter("worker_id");
				String is_completed = request.getParameter("is_completed");
				if (worker_id != null) {
					OrderWorker orderWorker = orderWorkerDao.getByPK(Integer.parseInt(order_id), Integer.parseInt(worker_id));
					if (orderWorker == null) {
						orderWorker = new OrderWorker(Integer.parseInt(order_id), Integer.parseInt(worker_id), is_completed);
						orderWorkerDao.createFrom(orderWorker);
					} else {
						orderWorker.setIsCompleted(is_completed);
						orderWorkerDao.update(orderWorker);
					}					
				}
				//
				OrderInWork order = orderDao.getByPK(Integer.parseInt(order_id));
				request.setAttribute("order", order);
				request.setAttribute("action", "edit");
				List<Car> carList = carDao.getAll();
				request.setAttribute("cars", carList);
				List<OrderPart> orderPartList = orderPartDao.getByOrderId(Integer.parseInt(order_id));
				request.setAttribute("parts", getOrderPartAsBean(orderPartList));
				List<OrderWorker> orderWorkerList = orderWorkerDao.getByOrderId(Integer.parseInt(order_id));
				request.setAttribute("workers", getOrderWorkerAsBean(orderWorkerList));
				List<OrderStatus> orderStatusList = orderStatusDao.getAll();
				request.setAttribute("order_statuses", orderStatusList);
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
	
	private List<OrderPartBean> getOrderPartAsBean(List<OrderPart> orderPartList) {
		List<OrderPartBean> resultList = new ArrayList<>();
		for (OrderPart item : orderPartList) {
			OrderPartBean opb = new OrderPartBean();
			opb.setOrderId(item.getId());
			opb.setPartId(item.getPartId());
			Part p = partDao.getByPK(item.getPartId());
			opb.setPartName(p.getPartName());
			opb.setUsedAmount(item.getUsedAmount());
			resultList.add(opb);
		}
		return resultList;
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
