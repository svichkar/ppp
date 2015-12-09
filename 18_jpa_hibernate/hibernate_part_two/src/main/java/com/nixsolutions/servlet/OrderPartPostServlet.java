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
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.hibernate.entity.OrderStatus;
import com.nixsolutions.hibernate.entity.OrderWorker;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.entity.Worker;

@WebServlet("/orderPartPost.do")
public class OrderPartPostServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static OrderInWorkDAO orderDao;
	private static CarDAO carDao;
	private static OrderWorkerDAO orderWorkerDao;
	private static OrderPartDAO orderPartDao;
	private static PartDAO partDao;
	private static OrderStatusDAO orderStatusDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		userDao = daoFactory.getUserDAO();
		userDao = daoFactory.getUserDAO();
		orderDao = daoFactory.getOrderInWorkDAO();
		carDao = daoFactory.getCarDAO();
		orderWorkerDao = daoFactory.getOrderWorkerDAO();
		orderPartDao = daoFactory.getOrderPartDAO();
		partDao = daoFactory.getPartDAO();
		orderStatusDao = daoFactory.getOrderStatusDAO();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				String order_id = request.getParameter("order_id");
				String part_id = request.getParameter("part_id");
				String used_amount = request.getParameter("used_amount");
				if (part_id != null) {
					OrderPart orderPart = orderPartDao.getByPK(Integer.parseInt(order_id), Integer.parseInt(part_id));
					if (orderPart == null) {
						orderPart = new OrderPart(orderDao.getByPK(Long.parseLong(order_id)), partDao.getByPK(Long.parseLong(part_id)), Long.parseLong(used_amount));
						orderPartDao.createFrom(orderPart);
					} else {
						orderPart.setUsedAmount(Long.parseLong(used_amount));
						orderPartDao.update(orderPart);
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
			opb.setOrderId(item.getOrder().getOrderId());
			Part p = item.getPart();
			opb.setPartId(p.getPartId());
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
			owb.setOrderId(item.getOrder().getOrderId());
			Worker w = item.getWorker();
			owb.setWorkerId(w.getWorkerId());
			owb.setWorkerName(w.getFirstName() + " " + w.getLastName());
			owb.setIsCompleted(item.getIsCompleted().toString());
			resultList.add(owb);
		}
		return resultList;
	}
}
