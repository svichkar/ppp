package com.nixsolutions.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.impl.CustomerDAOImpl;
import com.nixsolutions.dao.impl.RoleDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.UserDAOImpl;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.Role;
import com.nixsolutions.entities.User;

/**
 * Servlet implementation class landing
 */
public class Landing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceStationDAOFactoryImpl factory;
	private UserDAOImpl userImpl;
	private RoleDAOImpl roleImpl;
	private CustomerDAOImpl customerImpl;
	private boolean authorized = false;
	private final static Logger LOG = LogManager.getLogger(Landing.class);

	/**
	 * Default constructor.
	 * 
	 * @throws Exception
	 */
	public Landing() throws Exception {
		try {
			factory = new ServiceStationDAOFactoryImpl();
			userImpl = (UserDAOImpl) factory.getDao(User.class);
			roleImpl = (RoleDAOImpl) factory.getDao(Role.class);
			customerImpl = (CustomerDAOImpl) factory.getDao(Customer.class);
		} catch (Exception ex) {
			LOG.error(ex, ex);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("login");
		String password = request.getParameter("passwd");
		HttpSession session = request.getSession();

		User foundUser = new User();
		if (username != null & password != null) {
			foundUser = userImpl.findByNameAndPassword(username, password);
		} else {
			username = session.getAttribute("login").toString();
			password = session.getAttribute("password").toString();
			foundUser = userImpl.findByNameAndPassword(username, password);
		}

		if (foundUser.getId() > 0) {
			session.setAttribute("login", username);
			session.setAttribute("password", password);
			authorized = true;
		}

		if (authorized) {
			StringBuilder sb = new StringBuilder();
			if (roleImpl.findByPK(foundUser.getRole_id()).getRolename().equals("admin")) {
				sb = createAdminUsersTable();
			} else {
				sb = createReadOnlyUsersTable();
			}
			PrintWriter pw = response.getWriter();
			pw.append(sb.toString());
			pw.flush();
			pw.close();
		} else {
			response.sendRedirect("index.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private StringBuilder createReadOnlyUsersTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><title>List of users</title><body><table border=\"1\" align=\"center\">");
		sb.append("<tr><th>Login</th><th>First Name</th><th>Last Name</th><th>Role</th></tr>");
		for (User oneUser : userImpl.getAll()) {
			sb.append("<tr><td>").append(oneUser.getUsername()).append("</td>");
			sb.append("<td>").append(customerImpl.findByPK(oneUser.getCustomer_id()).getF_name()).append("</td>");
			sb.append("<td>").append(customerImpl.findByPK(oneUser.getCustomer_id()).getL_name()).append("</td>");
			sb.append("<td>").append(roleImpl.findByPK(oneUser.getRole_id()).getRolename()).append("</td></tr>");
		}
		sb.append("</table></body></html>");
		return sb;
	}

	private StringBuilder createAdminUsersTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><title>List of users</title><body><table border=\"1\" align=\"center\">");
		sb.append("<tr><th>Login</th><th>First Name</th><th>Last Name</th><th>Role</th><th>Action</th>");
		sb.append("<th><form name=\"addnew\"  method=\"GET\" action=\"perform\">");
		sb.append("<input type=\"hidden\" name=\"action\" value=\"New\">");
		sb.append("<input type=\"submit\" value=\"Add user\"></form></th></tr>");
		for (User oneUser : userImpl.getAll()) {
			sb.append("<tr><td>").append(oneUser.getUsername()).append("</td>");
			sb.append("<td>").append(customerImpl.findByPK(oneUser.getCustomer_id()).getF_name()).append("</td>");
			sb.append("<td>").append(customerImpl.findByPK(oneUser.getCustomer_id()).getL_name()).append("</td>");
			sb.append("<td>").append(roleImpl.findByPK(oneUser.getRole_id()).getRolename()).append("</td>");
			sb.append("<form name=\"processing\" method=\"GET\" action=\"perform\">");
			sb.append("<td>").append("<select name=\"action\">")
					.append("<option value=\"Edit\">Edit</option>");
			sb.append("<option value=\"Delete\">Delete</option></select></td>");
			sb.append("<td>");
			sb.append("<input type=\"hidden\" name=\"usertoprocess\" value=\"").append(oneUser.getId()).append("\">");
			sb.append("<input type=\"submit\" value=\"Process\"></form></td></tr>");
		}
		sb.append("</table></body></html>");
		return sb;
	}

}
