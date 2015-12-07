package com.nixsolutions.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.OrderPartDAO;
import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.hibernate.entity.OrderPart;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;

@WebServlet(urlPatterns = { "/addPart.do", "/editPart.do", "/deletePart.do" })
public class PartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static OrderPartDAO orderPartDao;
	private static PartDAO partDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		userDao = daoFactory.getUserDAO();
		orderPartDao = daoFactory.getOrderPartDAO();
		partDao = daoFactory.getPartDAO();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
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
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				if (action.equals("Edit")) {
					Part part = partDao.getByPK(Integer.parseInt(part_id));
					request.setAttribute("part", part);
					request.setAttribute("action", "edit");
					request.getRequestDispatcher("/WEB-INF/jsp/part.jsp").forward(request, response);
				} else {
					Part part = partDao.getByPK(Integer.parseInt(part_id));
					List<OrderPart> orderPartList = orderPartDao.getOrderPartByPart(part);
					for (OrderPart orderPart : orderPartList) {
						orderPartDao.delete(orderPart);
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
