package com.nixsolutions.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dao.OrderStatusDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.hibernate.DaoFactoryHibernate;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;

@WebServlet("/orderPost.do")
public class OrderPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static OrderInWorkDAO orderDao;
	private static OrderStatusDAO orderStatusDao;
	private static CarDAO carDao;

	@Override
	public void init() {
		DaoFactoryHibernate daoFactory = new DaoFactoryHibernate();
		userDao = daoFactory.getUserDAO();
		orderDao = daoFactory.getOrderInWorkDAO();
		orderStatusDao = daoFactory.getOrderStatusDAO();
		carDao = daoFactory.getCarDAO();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				String order_id = request.getParameter("id");
				String order_status_id = request.getParameter("order_status_id");
				String order_description = request.getParameter("order_description").replace("\t", "");
				String car_id = request.getParameter("car_id");
				String timestamp_started = request.getParameter("timestamp_started");
				String timestamp_finished = request.getParameter("timestamp_finished");
				if (order_id == null || order_id.equals("")) {
					OrderInWork order = new OrderInWork(orderStatusDao.getByPK(Integer.parseInt(order_status_id)),
							order_description, carDao.getByPK(Integer.parseInt(car_id)),
							new Timestamp(new Date().getTime()), null);
					orderDao.createFrom(order);
				} else {
					OrderInWork order = orderDao.getByPK(Integer.parseInt(order_id));
					order.setOrderStatus(orderStatusDao.getByPK(Integer.parseInt(order_status_id)));
					order.setDescription(order_description);
					order.setCar(carDao.getByPK(Integer.parseInt(car_id)));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
					try {
						order.setTimestampStart(new Timestamp(sdf.parse(timestamp_started).getTime()));
						if (timestamp_finished != null && timestamp_finished != "") {
							order.setTimestampFinish(new Timestamp(sdf.parse(timestamp_finished).getTime()));
						} else {
							order.setTimestampFinish(null);
						}
					} catch (ParseException e) {
						e.printStackTrace();
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
