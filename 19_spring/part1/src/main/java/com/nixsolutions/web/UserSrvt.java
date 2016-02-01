package com.nixsolutions.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceException;

import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.dao.impl.CustomerDAOImpl;
import com.nixsolutions.dao.impl.RoleDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.UserDAOImpl;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.Role;
import com.nixsolutions.entities.User;

/**
 * Servlet implementation class UserSrvt
 */

public class UserSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAOImpl customerImpl;
	private UserDAOImpl userImpl;
	private RoleDAOImpl roleImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSrvt() throws Exception {
		customerImpl = ServiceStationDAOFactoryImpl.getCustomerDao();
		userImpl = ServiceStationDAOFactoryImpl.getUserDao();
		roleImpl = ServiceStationDAOFactoryImpl.getRoleDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_id = request.getParameter("user_id") != null ? request.getParameter("user_id") : "";
		String password = request.getParameter("password") != null ? request.getParameter("password") : "";
		String role_id = request.getParameter("role_id") != null ? request.getParameter("role_id") : "";
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		String username = request.getParameter("username") != null ? request.getParameter("username") : "";
		/// parse user id
		int userId = NumberUtils.isDigits(user_id) ? Integer.parseInt(user_id) : 0;
		// parse role id
		int roleId = NumberUtils.isDigits(role_id) ? Integer.parseInt(role_id) : 0;
		if (userId > 0) {
			User user = userImpl.findByPK(userId);
			if (action.equalsIgnoreCase("Edit")) {
				request.setAttribute("user", user);
				request.setAttribute("roles", roleImpl.getAll());
				request.setAttribute("title", "Edit user");
				request.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Delete")) {
				List<Customer> lCustomers = customerImpl.getAll();
				for (Customer customer : lCustomers) {
					if (customer.getUser() != null && customer.getUser().getUserId() == user.getUserId()) {
						throw new WebServiceException(
								"You cannot remove user that is assigned to at least one customer!!");
					}
				}
				if (!user.getRole().getRolename().equalsIgnoreCase("admin")) {
					userImpl.delete(user);
				}
				request.setAttribute("destination", "Users");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				user.setUsername(username);
				user.setPassword(password);
				user.setRole(roleImpl.findByPK(roleId));
				userImpl.update(user);
				request.setAttribute("destination", "Users");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				request.setAttribute("roles", roleImpl.getAll());
				request.setAttribute("title", "Add user");
				request.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				User userNew = new User(username, password, roleImpl.findByPK(roleId));
				userImpl.create(userNew);
				request.setAttribute("destination", "Users");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else {
				request.setAttribute("destination", "Users");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		}
	}

}
