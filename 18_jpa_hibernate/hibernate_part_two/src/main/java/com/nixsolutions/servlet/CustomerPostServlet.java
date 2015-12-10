package com.nixsolutions.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.hibernate.DaoFactoryHibernate;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;

@WebServlet("/customerPost.do")
public class CustomerPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static CustomerDAO customerDao;

	@Override
	public void init() {
		DaoFactoryHibernate daoFactory = new DaoFactoryHibernate();
		userDao = daoFactory.getUserDAO();
		customerDao = daoFactory.getCustomerDAO();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				String customer_id = request.getParameter("id");
				String first_name = request.getParameter("first_name");
				String last_name = request.getParameter("last_name");
				String phone = request.getParameter("phone");
				String user_id = request.getParameter("user_id");
				if (customer_id == null || customer_id.equals("")) {
					Customer customer = new Customer(first_name, last_name, phone, userDao.getByPK(Integer.parseInt(user_id)));
					customerDao.createFrom(customer);
				} else {
					Customer customer = customerDao.getByPK(Integer.parseInt(customer_id));
					customer.setFirstName(first_name);
					customer.setLastName(last_name);
					customer.setPhone(phone);
					customer.setUser(userDao.getByPK(Integer.parseInt(user_id)));
					customerDao.update(customer);
				}
				request.setAttribute("target", "Customers");
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
