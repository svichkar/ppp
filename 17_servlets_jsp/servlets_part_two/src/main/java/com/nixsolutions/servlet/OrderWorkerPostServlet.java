package com.nixsolutions.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

@WebServlet("/orderWorkerPost.do")
public class OrderWorkerPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static RoleDAO roleDao;
	private static UserDAO userDao;
	private static OrderWorkerDAO orderWorkerDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		roleDao = daoFactory.getRoleDAO();
		userDao = daoFactory.getUserDAO();
		orderWorkerDao = daoFactory.getOrderWorkerDAO();
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
				//request.getRequestDispatcher("/nav.do").forward(request, response);
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

}
