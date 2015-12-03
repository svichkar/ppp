package com.nixsolutions.webApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.bean.OrderFullInfo;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.impl.CarDaoImpl;
import com.nixsolutions.dao.impl.CustomerDaoImpl;
import com.nixsolutions.dao.impl.OrderStatusDaoImpl;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.OrderInWork;

/**
 * Servlet implementation class AdminPageServlet
 */
public class AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<OrderInWork> orderList = null;
		orderList = DaoFactory.getOrderInWorkDaoImpl().getAllOrders();
		request.setAttribute("orders", getOrdersInfo(orderList));
		request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private List<OrderFullInfo> getOrdersInfo(List<OrderInWork> orderList) {
		List<OrderFullInfo> orders = new ArrayList<OrderFullInfo>();
		for (OrderInWork order : orderList) {
			CarDaoImpl carDaoImpl = DaoFactory.getCarDaoImpl();
			CustomerDaoImpl customerDaoImpl = DaoFactory.getCustomerDaoImpl();
			OrderFullInfo fullInfo = new OrderFullInfo();
			Car car = carDaoImpl.getCarByID(order.getCar_id());
			fullInfo.setCarRegNumber(car.getReg_number());
			fullInfo.setCarModel(car.getCar_model());
			fullInfo.setCarDescription(car.getCar_description());
			Customer customer = customerDaoImpl.getCustomerByID(car.getCustomer_id());
			fullInfo.setCustomerFullName(customer.getLast_name() + " " + customer.getFirst_name());
			if (order.getDatetime_finish() != null)
				fullInfo.setOrderFinishTime(order.getDatetime_finish().toString());
			else
				fullInfo.setOrderFinishTime(null);
			fullInfo.setOrderStartTime(order.getDatetime_start().toString());
			OrderStatusDaoImpl orderStatus = DaoFactory.getOrderStatusDaoImpl();
			fullInfo.setOrderStatus(orderStatus.getStatusByID(order.getOrder_status_id()).getOrder_status_name());
			fullInfo.setOredrInfo(order.getOrder_description());
			fullInfo.setOrderId(order.getOrder_id().toString());
			orders.add(fullInfo);
		}
		return orders;
	}
}
