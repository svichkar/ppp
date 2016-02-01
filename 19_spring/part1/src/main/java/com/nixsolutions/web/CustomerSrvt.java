package com.nixsolutions.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceException;

import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.dao.impl.CustomerDAOImpl;
import com.nixsolutions.dao.impl.OrderInWorkDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.UserDAOImpl;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.User;

/**
 * Servlet implementation class Customer
 */
public class CustomerSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAOImpl customerImpl;
	private UserDAOImpl userImpl;
	private OrderInWorkDAOImpl orderInWorkImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerSrvt() throws Exception {
		customerImpl = ServiceStationDAOFactoryImpl.getCustomerDao();
		userImpl = ServiceStationDAOFactoryImpl.getUserDao();
		orderInWorkImpl =  ServiceStationDAOFactoryImpl.getOrderInWorkDao();
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
		String customer_id = request.getParameter("customer_id") != null ? request.getParameter("customer_id") : "";
		String user_id = request.getParameter("user_id") != null ? request.getParameter("user_id") : "";
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		String f_name = request.getParameter("f_name") != null ? request.getParameter("f_name") : "";
		String l_name = request.getParameter("l_name") != null ? request.getParameter("l_name") : "";
		String phone = request.getParameter("phone") != null ? request.getParameter("phone") : "";
		/// parse customer id
		int customerId = NumberUtils.isDigits(customer_id) ? Integer.parseInt(customer_id) : 0;
		int userId = NumberUtils.isDigits(user_id) ? Integer.parseInt(user_id) : 0;

		if (customerId > 0) {
			Customer customer = customerImpl.findByPK(customerId);
			if (action.equalsIgnoreCase("Edit")) {
				request.setAttribute("customer", customer);
				request.setAttribute("users", userImpl.getAll());
				request.setAttribute("title", "Edit customer");
				request.getRequestDispatcher("/WEB-INF/jsp/customer.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Delete")) {
				List<OrderInWork> lOIW = orderInWorkImpl.getAll();
				for (OrderInWork orderInWork : lOIW) {
					if(orderInWork.getCar().getCustomer().getCustomerId() == customer.getCustomerId())
					{
						throw new WebServiceException("You cannot remove customer when his/her car in order!!");
					}
				}
				customerImpl.delete(customer);
				request.setAttribute("destination", "Customers");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				customer.setF_name(f_name);
				customer.setL_name(l_name);
				customer.setPhone(phone);
				customer.setUser(userImpl.findByPK(userId));
				customerImpl.update(customer);
				request.setAttribute("destination", "Customers");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				request.setAttribute("title", "Add customer");
				request.setAttribute("users", userImpl.getAll());
				request.getRequestDispatcher("/WEB-INF/jsp/customer.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				Customer customer = new Customer(f_name, l_name, phone, userImpl.findByPK(userId));
				customerImpl.create(customer);
				request.setAttribute("destination", "Customers");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else {
				request.setAttribute("destination", "Customers");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		}
	}

}
