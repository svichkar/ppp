package com.nixsolutions.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.bean.OrderInWorkCarStatusBean;
import com.nixsolutions.dao.impl.RoleDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.UserDAOImpl;
import com.nixsolutions.entities.OrderInWorkCarStatus;
import com.nixsolutions.entities.Role;
import com.nixsolutions.entities.User;

/**
 * Servlet implementation class landing
 */
public class Landing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = LogManager.getLogger(Landing.class);
	private ServiceStationDAOFactoryImpl factory;
	private UserDAOImpl userImpl;
	private RoleDAOImpl roleImpl;
	private OrderInWorkCarStatusBean oiwCS;
	

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
			oiwCS = (OrderInWorkCarStatusBean) factory.getDao(OrderInWorkCarStatus.class);
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/// get credentials
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		String session_id = session.getId();
		
		// check credentials with db data
		User founduser = userImpl.findByNameAndPassword(login, password);

		if (founduser.getUsername() != null) {
			session.setAttribute("login", founduser.getUsername());
			//update session id
			founduser.setSession(session_id);
			userImpl.update(founduser);
			// check role
			Role foundUserRole = roleImpl.findByPK(founduser.getRole().getRoleId());
			if (foundUserRole.getRolename().equals("admin")) {
				request.setAttribute("title", "Orders");
				request.setAttribute("desctination", "Orders");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else if (foundUserRole.getRolename().equals("user")) {
				request.setAttribute("oiwcs", oiwCS.getAll());
				request.setAttribute("title", "Orders status");
				request.getRequestDispatcher("/WEB-INF/jsp/userpage.jsp").forward(request, response);
			} else {
				request.removeAttribute("login");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	}

}
