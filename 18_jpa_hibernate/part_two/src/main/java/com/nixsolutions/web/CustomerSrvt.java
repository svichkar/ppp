package com.nixsolutions.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.impl.CustomerDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.entities.Customer;

/**
 * Servlet implementation class Customer
 */
public class CustomerSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceStationDAOFactoryImpl factory;
	private CustomerDAOImpl customerImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerSrvt() throws Exception {
		factory = new ServiceStationDAOFactoryImpl();
		customerImpl = (CustomerDAOImpl) factory.getDao(Customer.class);
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
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		String f_name = request.getParameter("f_name") !=null ? request.getParameter("f_name") : "";
		String l_name = request.getParameter("l_name") !=null ? request.getParameter("l_name") : "";
		String phone = request.getParameter("phone") !=null ? request.getParameter("phone") : "";
		/// parse customer id
		int customerId = customer_id.length() > 0 ? Integer.parseInt(customer_id) : 0;
		if (customerId > 0) {
			Customer customer = customerImpl.findByPK(customerId);
			if (action.equalsIgnoreCase("Edit")) {
				request.setAttribute("customer", customer);
				request.setAttribute("title", "Edit customer");
				request.getRequestDispatcher("/WEB-INF/jsp/customer.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Delete")) {
				customerImpl.delete(customer);
				request.setAttribute("destination", "Customers");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {

				customer.setF_name(f_name);
				customer.setL_name(l_name);
				customer.setPhone(phone);
				customerImpl.update(customer);
				request.setAttribute("destination", "Customers");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				request.setAttribute("title", "Add customer");
				request.getRequestDispatcher("/WEB-INF/jsp/customer.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				Customer customer = new Customer(f_name, l_name, phone);
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
