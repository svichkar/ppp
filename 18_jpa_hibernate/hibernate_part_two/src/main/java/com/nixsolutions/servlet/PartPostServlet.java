package com.nixsolutions.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.PartDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;

@WebServlet("/partPost.do")
public class PartPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static PartDAO partDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		userDao = daoFactory.getUserDAO();
		partDao = daoFactory.getPartDAO();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				String part_id = request.getParameter("id");
				String part_name = request.getParameter("part_name");
				String vendor = request.getParameter("vendor");
				String amount = request.getParameter("amount");
				if (part_id == null || part_id.equals("")) {
					Part part = new Part(part_name, vendor, Long.parseLong(amount));
					partDao.createFrom(part);
				} else {
					Part part = partDao.getByPK(Integer.parseInt(part_id));
					part.setPartName(part_name);
					part.setVendor(vendor);
					part.setAmount(Long.parseLong(amount));
					partDao.update(part);
				}
				request.setAttribute("target", "Parts");
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
