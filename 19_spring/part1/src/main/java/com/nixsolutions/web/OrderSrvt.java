package com.nixsolutions.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.bean.AllPartsInOrderBean;
import com.nixsolutions.bean.AllWorkersInOrderBean;
import com.nixsolutions.dao.impl.CarDAOImpl;
import com.nixsolutions.dao.impl.OrderInWorkDAOImpl;
import com.nixsolutions.dao.impl.OrderStatusDAOImpl;
import com.nixsolutions.dao.impl.OrderWorkerDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.entities.AllPartsInOrder;
import com.nixsolutions.entities.AllWorkersInOrder;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.OrderStatus;
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.error.CustomWebException;

/**
 * Servlet implementation class OrderSrvt
 */
public class OrderSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderInWorkDAOImpl orderInWorkImpl;
	private CarDAOImpl carImpl;
	private AllWorkersInOrderBean allWorkersinOrderImpl;
	private AllPartsInOrderBean allPartsInOrderImpl;
	private OrderStatusDAOImpl orderStatusImpl;
	private OrderWorkerDAOImpl orderWorkerImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderSrvt() throws Exception {
		orderInWorkImpl = ServiceStationDAOFactoryImpl.getOrderInWorkDao();
		carImpl = ServiceStationDAOFactoryImpl.getCarDao();
		allWorkersinOrderImpl = ServiceStationDAOFactoryImpl.getAllWorkersInOrderBean();
		allPartsInOrderImpl = ServiceStationDAOFactoryImpl.getAllPartsInOrderBean();
		orderStatusImpl = ServiceStationDAOFactoryImpl.getOrderStatusDao();
		orderWorkerImpl = ServiceStationDAOFactoryImpl.getOrderWorkerDoa();
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
		List<AllWorkersInOrder> allWorkersInOrder = new ArrayList<>();
		List<AllPartsInOrder> allPartsInOrder = new ArrayList<>();
		if (orderId > 0) {
			allWorkersInOrder.addAll(allWorkersinOrderImpl.getAll(orderId));
			allPartsInOrder.addAll(allPartsInOrderImpl.getAll(orderId));
		}
		List<Car> cars = carImpl.getAll();
		List<OrderStatus> allOrderStatus = orderStatusImpl.getAll();

		if (orderId > 0) {
			OrderInWork selectedOrderInWork = orderInWorkImpl.findByPK(orderId);
			if (action.equalsIgnoreCase("Edit")) {
				request.setAttribute("allOrderWorkers", allWorkersInOrder);
				request.setAttribute("allOrderParts", allPartsInOrder);
				request.setAttribute("orderInWork", selectedOrderInWork);
				request.setAttribute("cars", cars);
				request.setAttribute("allOrderStatuses", allOrderStatus);
				request.setAttribute("title", "Order");
				request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Delete")) {
				List<OrderWorker> allAssignedWorkes = orderWorkerImpl.getAllForOrder(orderId);
				if (allAssignedWorkes.size()>0)
				{
					throw new CustomWebException("You cannot delete order that has at least one assigned worker!");
				}
				orderInWorkImpl.delete(selectedOrderInWork);
				request.setAttribute("destination", "Orders");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				selectedOrderInWork.setCar(carImpl.findByPK(carId));
				selectedOrderInWork.setOrder_status(orderStatusImpl.findByPK(orderstatusId));
				selectedOrderInWork.setDescription(description);
				selectedOrderInWork.setDatetime_start(datetimeStart);
				selectedOrderInWork.setDatetime_end(datetimeEnd);
				selectedOrderInWork.setDescription(description);
				orderInWorkImpl.update(selectedOrderInWork);
				request.setAttribute("destination", "Orders");
				request.setAttribute("title", "Orders");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				request.setAttribute("allOrderParts", allPartsInOrder);
				request.setAttribute("cars", cars);
				request.setAttribute("allOrderStatuses", allOrderStatus);
				request.setAttribute("title", "Order");
				request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				OrderInWork orderinWorkAdded = new OrderInWork(orderStatusImpl.findByPK(orderstatusId), description,
						carImpl.findByPK(carId), datetimeStart, datetimeEnd);
				orderInWorkImpl.create(orderinWorkAdded);
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
