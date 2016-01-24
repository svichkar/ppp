package com.nixsolutions.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nixsolutions.dao.impl.OrderInWorkCarStatusDAOImpl;
import com.nixsolutions.dao.impl.RoleDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.UserDAOImpl;
import com.nixsolutions.entities.OrderInWorkCarStatus;
import com.nixsolutions.entities.Role;
import com.nixsolutions.entities.User;

/**
 * Servlet Filter implementation class AuthFilter
 */
public class AuthFilter implements Filter {
	private ServiceStationDAOFactoryImpl factory;
	private UserDAOImpl userImpl;
	private RoleDAOImpl roleImpl;
	private OrderInWorkCarStatusDAOImpl oiwCS;

	protected ServletContext srvtContext;

	public AuthFilter() throws Exception {
		factory = new ServiceStationDAOFactoryImpl();
		userImpl = (UserDAOImpl) factory.getDao(User.class);
		roleImpl = (RoleDAOImpl) factory.getDao(Role.class);
		oiwCS = (OrderInWorkCarStatusDAOImpl) factory.getDao(OrderInWorkCarStatus.class);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String session_id = session.getId();

		String login = (String) session.getAttribute("login");
		User founduser = userImpl.findByName(login);
		// check role
		if (founduser.getUsername() != null) {
			// check role
			if (founduser.getUsername().equalsIgnoreCase(login)
					& founduser.getSession_id().equalsIgnoreCase(session_id)) {
				Role foundUserRole = roleImpl.findByPK(founduser.getRole_id());
				if (foundUserRole.getRolename().equalsIgnoreCase("admin")) {
					chain.doFilter(request, response);
				} else {
					request.setAttribute("oiwcs", oiwCS.getAll());
					request.getRequestDispatcher("/WEB-INF/jsp/userpage.jsp").forward(request, response);
				}
			}
			else
			{
				request.setAttribute("warning", "Your session has expired");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} else

		{
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		srvtContext = fConfig.getServletContext();
	}

}
