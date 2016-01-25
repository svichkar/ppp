package com.nixsolutions.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nixsolutions.bean.CarCustomerBean;
import com.nixsolutions.bean.OrderInWorkCarStatusBean;
import com.nixsolutions.bean.WorkerStatusSpecificationBean;
import com.nixsolutions.dao.impl.CustomerDAOImpl;
import com.nixsolutions.dao.impl.PartDAOImpl;
import com.nixsolutions.dao.impl.RoleDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.UserDAOImpl;
import com.nixsolutions.entities.CarCustomer;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.OrderInWorkCarStatus;
import com.nixsolutions.entities.Part;
import com.nixsolutions.entities.Role;
import com.nixsolutions.entities.User;
import com.nixsolutions.entities.WorkerStatusSpecification;

/**
 * Servlet implementation class Navigation
 */
public class Navigation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userImpl;
	private RoleDAOImpl roleImpl;
	private ServiceStationDAOFactoryImpl factory;
	private PartDAOImpl partImpl;
	private CustomerDAOImpl customerImpl;
	private CarCustomerBean carcustomerImpl;
	private WorkerStatusSpecificationBean workerstatusspecificationImpl;
	private OrderInWorkCarStatusBean orderInWorkCSImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public Navigation() throws Exception {
		factory = new ServiceStationDAOFactoryImpl();
		userImpl = (UserDAOImpl) factory.getDao(User.class);
		roleImpl = (RoleDAOImpl) factory.getDao(Role.class);
		customerImpl = (CustomerDAOImpl) factory.getDao(Customer.class);
		partImpl = (PartDAOImpl) factory.getDao(Part.class);
		carcustomerImpl = (CarCustomerBean) factory.getDao(CarCustomer.class);
		workerstatusspecificationImpl = (WorkerStatusSpecificationBean) factory.getDao(WorkerStatusSpecification.class);
		orderInWorkCSImpl = (OrderInWorkCarStatusBean) factory.getDao(OrderInWorkCarStatus.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rrr = (String) request.getAttribute("destination");
		rrr.length();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		String destinaion = (request.getParameter("destination") != null ? request.getParameter("destination")
				: (String) request.getAttribute("destination"));
		if (destinaion == null) {
			destinaion = "Orders";
		}
		User user = userImpl.findByName(login);
		Role role = roleImpl.findByPK(user.getRole().getRoleId());
		if (user.getUsername() != null) {
			if (role.getRolename().equals("admin")) {
				switch (destinaion) {
				case "Cars":
					request.setAttribute("carcustomers", carcustomerImpl.getAll());
					request.setAttribute("title", "Cars");
					request.getRequestDispatcher("/WEB-INF/jsp/cars.jsp").forward(request, response);
					break;
				case "Orders":
					request.setAttribute("oiwcs", orderInWorkCSImpl.getAll());
					request.setAttribute("title", "Orders");
					request.getRequestDispatcher("/WEB-INF/jsp/orders.jsp").forward(request, response);
					break;
				case "Workers":
					request.setAttribute("workers", workerstatusspecificationImpl.getAll());
					request.setAttribute("title", "Workers");
					request.getRequestDispatcher("/WEB-INF/jsp/workers.jsp").forward(request, response);
					break;
				case "Parts":
					request.setAttribute("parts", partImpl.getAll());
					request.setAttribute("title", "Parts");
					request.getRequestDispatcher("/WEB-INF/jsp/parts.jsp").forward(request, response);
					break;
				case "Customers":
					request.setAttribute("customers", customerImpl.getAll());
					request.setAttribute("title", "Customers");
					request.getRequestDispatcher("/WEB-INF/jsp/customers.jsp").forward(request, response);
					break;
				default:
					request.setAttribute("oiwcs", orderInWorkCSImpl.getAll());
					request.setAttribute("title", "Orders status");
					request.getRequestDispatcher("/WEB-INF/jsp/orders.jsp").forward(request, response);
					break;
				}

			} else {
				request.setAttribute("title", "Orders status");
				request.getRequestDispatcher("/WEB-INF/jsp/userpage.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("title", "Oooups! Error!");
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}

	}

}
