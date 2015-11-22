package com.nixsolutions.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderPart;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

@WebServlet(urlPatterns = { "/addPart.do", "/editPart.do", "/deletePart.do" })
public class PartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static RoleDAO roleDao;
	private static UserDAO userDao;
	private static OrderInWorkDAO orderDao;
	private static OrderWorkerDAO orderWorkerDao;
	private static OrderPartDAO orderPartDao;
	private static PartDAO partDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		roleDao = daoFactory.getRoleDAO();
		userDao = daoFactory.getUserDAO();
		orderDao = daoFactory.getOrderInWorkDAO();
		orderWorkerDao = daoFactory.getOrderWorkerDAO();
		orderPartDao = daoFactory.getOrderPartDAO();
		partDao = daoFactory.getPartDAO();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				request.setAttribute("action", "add");
				request.getRequestDispatcher("/WEB-INF/jsp/part.jsp").forward(request, response);
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
		String part_id = (String) request.getParameter("part_id");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				if (action.equals("Edit")) {
					Part part = partDao.getByPK(Integer.parseInt(part_id));
					request.setAttribute("part", part);
					request.setAttribute("action", "edit");
					request.getRequestDispatcher("/WEB-INF/jsp/part.jsp").forward(request, response);
				} else {
					Part part = partDao.getByPK(Integer.parseInt(part_id));
					List<OrderInWork> orderList = orderDao.getOrdersByPart(part);
					for (OrderInWork order : orderList) {
						List<OrderWorker> owList = orderWorkerDao.getByOrderId(order.getId());
						for (OrderWorker ow : owList) {
							orderWorkerDao.delete(ow);
						}
						List<OrderPart> opList = orderPartDao.getByOrderId(order.getId());
						for (OrderPart op : opList) {
							orderPartDao.delete(op);
						}
						orderDao.delete(order);
					}
					partDao.delete(part);
					request.setAttribute("target", "Parts");
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
