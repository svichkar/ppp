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
 * Servlet implementation class Perform
 */
public class Perform extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userImpl;
	private RoleDAOImpl roleImpl;
	private CustomerDAOImpl customerImpl;
	private ServiceStationDAOFactoryImpl factory;
	private final static Logger LOG = LogManager.getLogger(Perform.class);

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public Perform() throws Exception {
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
		String actionName = request.getParameter("action");
		String user_Id = request.getParameter("usertoprocess");

		PrintWriter rw;
		switch (actionName) {
		case "Edit":
			// print form for modifying
			int userId = Integer.parseInt(user_Id);
			rw = response.getWriter();
			rw.append(makeEditForm(userId));
			rw.flush();
			rw.close();
			break;
		case "New":
			rw = response.getWriter();
			rw.append(makeNewForm());
			rw.flush();
			rw.close();
			break;
		case "Delete":
			doPost(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter("action");
		String user_Id = request.getParameter("usertoprocess");

		HttpSession session = request.getSession();
		String req = request.getRequestURI();
		if (!session.isNew()) {
			switch (actionName) {
			case "New":
				// print for adding new
				String f_name = request.getParameter("f_name");
				String l_name = request.getParameter("l_name");

				Customer customerNew = new Customer();
				customerNew.setf_name(f_name);
				customerNew.setl_name(l_name);
				customerImpl.create(customerNew);

				customerNew = customerImpl.findByFullName(f_name, l_name);

				User userNew = new User();
				userNew.setUsername(request.getParameter("username"));
				userNew.setPassword(request.getParameter("password"));
				String rolenameNew = request.getParameter("role");
				int roleIdNew = roleImpl.findRoleByName(rolenameNew).getId();
				userNew.setRole_id(roleIdNew);
				userNew.setCustomer_id(customerNew.getId());
				userImpl.create(userNew);

				response.sendRedirect("landing");
				break;

			case "Delete":
				int userId = Integer.parseInt(user_Id);
				User userToDelete = userImpl.findByPK(userId);
				if (!roleImpl.findByPK(userToDelete.getRole_id()).getRolename().equals("admin")) {
					userImpl.delete(userToDelete);
				}
				response.sendRedirect("landing");
				break;

			case "Update":
				int userIdToUpdate = Integer.parseInt(user_Id);
				User user = userImpl.findByPK(userIdToUpdate);
				user.setUsername(request.getParameter("username"));
				user.setPassword(request.getParameter("password"));
				String rolename = request.getParameter("role");
				int roleId = roleImpl.findRoleByName(rolename).getId();
				user.setRole_id(roleId);
				userImpl.update(user);

				Customer customer = customerImpl.findByPK(user.getCustomer_id());
				customer.setf_name(request.getParameter("f_name"));
				customer.setl_name(request.getParameter("l_name"));
				customerImpl.update(customer);

				response.sendRedirect("landing");
			}

		} else {
			response.sendRedirect("index.jsp");
		}
	}

	private StringBuilder makeEditForm(int userId) {
		StringBuilder sb = new StringBuilder();
		User userForEditing = userImpl.findByPK(userId);
		sb.append("<html><title>Editing user</title><body><table align=\"center\"><tr>");
		sb.append("<form name=\"edituser\" method=\"POST\" action=\"perform\">");
		sb.append("<td>Login:</td><td><input type=\"text\" name=\"username\" value=\"")
				.append(userImpl.findByPK(userId).getUsername()).append("\"></td></tr>");
		sb.append("<tr><td>Password:</td><td><input type=\"text\" name=\"password\" value=\"")
				.append(userImpl.findByPK(userId).getPassword()).append("\"></td></tr>");
		sb.append("<tr><td>First name:</td><td><input type=\"text\" name=\"f_name\" value=\"")
				.append(customerImpl.findByPK(userForEditing.getCustomer_id()).getF_name()).append("\"></td></tr>");
		sb.append("<tr><td>Last name:</td><td><input type=\"text\" name=\"l_name\" value=\"")
				.append(customerImpl.findByPK(userForEditing.getCustomer_id()).getL_name()).append("\"></td></tr>");
		sb.append("<tr><td>Role:</td><td><select name=\"role\">");
		for (Role oneRole : roleImpl.getAll()) {
			if (oneRole.getId() == roleImpl.findByPK(userForEditing.getRole_id()).getId()) {
				sb.append("<option selected>").append(oneRole.getRolename()).append("</option>");
			} else {
				sb.append("<option>").append(oneRole.getRolename()).append("</option>");
			}
		}
		
		sb.append("</select></td></tr>");
		sb.append("<tr><td><input type=\"hidden\" name=\"usertoprocess\" value=\"").append(userId).append("\">");
		sb.append("<input type=\"hidden\" name=\"action\" value=\"Update\">");
		sb.append("<input type=\"submit\" value=\"Save\"></td></tr>");
		sb.append("</form></table></body></html>");
		return sb;
	}

	private StringBuilder makeNewForm() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><title>Creating user</title><body><table align=\"center\"><tr>");
		sb.append("<form name=\"edituser\" method=\"POST\" action=\"perform\">");
		sb.append("<td>Login:</td><td><input type=\"text\" name=\"username\" value=\"\"></td></tr>");
		sb.append("<tr><td>Password:</td><td><input type=\"text\" name=\"password\" value=\"\"></td></tr>");
		sb.append("<tr><td>First name:</td><td><input type=\"text\" name=\"f_name\" value=\"\"></td></tr>");
		sb.append("<tr><td>Last name:</td><td><input type=\"text\" name=\"l_name\" value=\"\"></td></tr>");
		sb.append("<tr><td>Role:</td><td>");
		sb.append("<select name=\"role\">");
		for (Role oneRole : roleImpl.getAll()) {
			sb.append("<option>").append(oneRole.getRolename()).append("</option>");
		}
		sb.append("</select></td></tr>");
		sb.append("<tr><td><input type=\"hidden\" name=\"usertoprocess\" value=\"\">");
		sb.append("<input type=\"hidden\" name=\"action\" value=\"New\">");
		sb.append("<input type=\"submit\" value=\"Save\"></td></tr>");
		sb.append("</form></table></body></html>");
		return sb;
	}
}
