package com.nixsolutions.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

@WebServlet("/orderPost.do")
public class OrderPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static RoleDAO roleDao;
	private static UserDAO userDao;
	private static OrderInWorkDAO orderDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		roleDao = daoFactory.getRoleDAO();
		userDao = daoFactory.getUserDAO();
		orderDao = daoFactory.getOrderInWorkDAO();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				String order_id = request.getParameter("id");
				String order_status_id = request.getParameter("order_status_id");
				String order_description = request.getParameter("order_description").replace("\t", "");
				String car_id = request.getParameter("car_id");
				String timestamp_started = request.getParameter("timestamp_started");
				String timestamp_finished = request.getParameter("timestamp_finished");
				if (order_id == null || order_id.equals("")) {
					OrderInWork order = new OrderInWork(Integer.parseInt(order_status_id), order_description, 
							Integer.parseInt(car_id), new Timestamp(new Date().getTime()), null);
					orderDao.createFrom(order);
				} else {
					OrderInWork order = orderDao.getByPK(Integer.parseInt(order_id));
					order.setOrderStatusId(Integer.parseInt(order_status_id));
					order.setDescription(order_description);
					order.setCarId(Integer.parseInt(car_id));
					//order.setTimestampStart(Timestamp.valueOf(timestamp_started));
					
					//
					if (timestamp_finished != null && timestamp_finished != "") {
						order.setTimestampFinish(Timestamp.valueOf(timestamp_finished));
					} else {
						order.setTimestampFinish(null);
					}
					orderDao.update(order);
				}
				request.setAttribute("target", "Orders");
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
