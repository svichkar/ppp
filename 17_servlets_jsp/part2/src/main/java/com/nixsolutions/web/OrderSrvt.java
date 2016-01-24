package com.nixsolutions.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.dao.impl.AllPartsInOrderDAOImpl;
import com.nixsolutions.dao.impl.AllWorkersInOrderDAOImpl;
import com.nixsolutions.dao.impl.CarDAOImpl;
import com.nixsolutions.dao.impl.OrderInWorkDAOImpl;
import com.nixsolutions.dao.impl.OrderStatusDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.entities.AllPartsInOrder;
import com.nixsolutions.entities.AllWorkersInOrder;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderStatus;

/**
 * Servlet implementation class OrderSrvt
 */
public class OrderSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceStationDAOFactoryImpl factory;
	private OrderInWorkDAOImpl orderInWorkImpl;
	private CarDAOImpl carImpl;
	private AllWorkersInOrderDAOImpl owImpl;
	private AllPartsInOrderDAOImpl poImpl;
	private OrderStatusDAOImpl osImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderSrvt() throws Exception {
		factory = new ServiceStationDAOFactoryImpl();
		orderInWorkImpl = (OrderInWorkDAOImpl) factory.getDao(OrderInWork.class);
		carImpl = (CarDAOImpl) factory.getDao(Car.class);
		owImpl = (AllWorkersInOrderDAOImpl) factory.getDao(AllWorkersInOrder.class);
		poImpl = (AllPartsInOrderDAOImpl) factory.getDao(AllPartsInOrder.class);
		osImpl = (OrderStatusDAOImpl) factory.getDao(OrderStatus.class);
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
		String order_status_id = request.getParameter("order_status_id") != null
				? request.getParameter("order_status_id") : "";
		String description = request.getParameter("description") != null ? request.getParameter("description") : "";
		String car_id = request.getParameter("car_id") != null ? request.getParameter("car_id") : "";
		String order_id = "";
		if (request.getAttribute("order_id") != null) {
			order_id = String.valueOf(request.getAttribute("order_id"));
		} else if (request.getParameter("order_id") != null) {
			order_id = request.getParameter("order_id");
		}
		String datetime_start = request.getParameter("datetime_start") != null ? request.getParameter("datetime_start")
				: "";
		String datetime_end = request.getParameter("datetime_end") != null ? request.getParameter("datetime_end") : "";
		String action = request.getAttribute("action") != null ? String.valueOf(request.getAttribute("action"))
				: request.getParameter("action");
		/// parse datetime
		Timestamp datetimeStart = isStringValidDate(datetime_start) ? Timestamp.valueOf(datetime_start) : null;
		Timestamp datetimeEnd = isStringValidDate(datetime_end) ? Timestamp.valueOf(datetime_end) : null;
		/// parse id
		int orderstatusId = NumberUtils.isDigits(order_status_id) ? Integer.parseInt(order_status_id) : 0;
		int orderId = NumberUtils.isDigits(order_id) ? Integer.parseInt(order_id) : 0;
		int carId = NumberUtils.isDigits(car_id) ? Integer.parseInt(car_id) : 0;
		/// get items for select control
		List<AllWorkersInOrder> ow = owImpl.getAll(orderId);
		List<AllPartsInOrder> po = poImpl.getAll(orderId);
		List<Car> cars = carImpl.getAll();
		List<OrderStatus> os = osImpl.getAll();

		if (orderId > 0) {
			OrderInWork oiw = orderInWorkImpl.findByPK(orderId);
			if (action.equalsIgnoreCase("Edit")) {
				request.setAttribute("ow", ow);
				request.setAttribute("po", po);
				request.setAttribute("oiw", oiw);
				request.setAttribute("cars", cars);
				request.setAttribute("os", os);
				request.setAttribute("title", "Order");
				request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Delete")) {
				/// need to delete related records
				orderInWorkImpl.delete(oiw);
				request.setAttribute("destination", "Orders");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				oiw.setCar_id(carId);
				oiw.setOrder_status_id(orderstatusId);
				oiw.setDescription(description);
				oiw.setDatetime_start(datetimeStart);
				oiw.setDatetime_end(datetimeEnd);
				oiw.setDescription(description);
				orderInWorkImpl.update(oiw);
				request.setAttribute("destination", "Orders");
				request.setAttribute("title", "Orders");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				request.setAttribute("po", po);
				request.setAttribute("cars", cars);
				request.setAttribute("os", os);
				request.setAttribute("title", "Order");
				request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				OrderInWork oiwNew = new OrderInWork(orderstatusId, description, carId, datetimeStart, datetimeEnd);
				orderInWorkImpl.create(oiwNew);
				request.setAttribute("title", "Orders");
				request.setAttribute("destination", "Orders");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else {
				request.setAttribute("title", "Orders");
				request.setAttribute("destination", "Orders");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		}
	}

	private boolean isStringValidDate(String date) {
		boolean valid = false;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			format.parse(date);
			valid = true;
		} catch (Exception ex) {
			valid = false;
		}
		return valid;
	}
	
	

}
