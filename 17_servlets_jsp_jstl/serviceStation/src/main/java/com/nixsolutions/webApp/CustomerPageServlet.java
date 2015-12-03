package com.nixsolutions.webApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.bean.CustomerFullInfo;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.User;

/**
 * Servlet implementation class CustomerPageServlet
 */
public class CustomerPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Customer> customerList = null;
		customerList = DaoFactory.getCustomerDaoImpl().getAllCustomers();
		request.setAttribute("customerList", getCustomerFullInfo(customerList));
		request.getRequestDispatcher("/WEB-INF/customerPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private List<CustomerFullInfo> getCustomerFullInfo(List<Customer> customerList) {
		List<CustomerFullInfo> list = new ArrayList<>();
		for (Customer customer : customerList) {
			CustomerFullInfo info = new CustomerFullInfo();
			info.setCustomer_id(customer.getCustomer_id().toString());
			info.setFullName(customer.getLast_name()+" "+customer.getFirst_name());
			info.setPhoneNumber(customer.getPhone());
			User user=DaoFactory.getUserDaoImpl().getUserByID(customer.getUser_id());
			info.setUserName(user.getUser_login());
			info.setUserPassword(user.getUser_password());
			list.add(info);
		}
		return list;
	}
}
