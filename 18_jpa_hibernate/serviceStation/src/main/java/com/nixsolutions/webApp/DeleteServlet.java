package com.nixsolutions.webApp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.nixsolutions.bean.OrderFullInfo;
//import com.nixsolutions.bean.PartFullInfo;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.PartOrder;
import com.nixsolutions.entity.Worker;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("adminPage");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fieldName = request.getParameter("fieldName");
		String order_id = request.getParameter("order_id");
		switch (fieldName) {
		case "order":
			if (!DaoFactory.getOrderInWorkDaoImpl().deleteOrderByID(Integer.decode(order_id)))
				request.setAttribute("message", "Can't delete order " + order_id + ". it used in another tables ");
			List<OrderInWork> orderList = DaoFactory.getOrderInWorkDaoImpl().getAllOrders();
			request.setAttribute("orders", orderList);
			request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);
			break;
		case "orderpart": {
			String part_id = request.getParameter("part_id");
			PartOrder partOrder = DaoFactory.getPartOrderDaoImpl().getPartByOrderId(Integer.decode(order_id),
					Integer.decode(part_id));
			Part part = DaoFactory.getPartDaoImpl().getPart(Integer.decode(part_id));
			part.setAmount(part.getAmount() + partOrder.getAmount());
			DaoFactory.getPartDaoImpl().updateExistingPart(part);
			DaoFactory.getPartOrderDaoImpl().deletePartFromOrder(Integer.decode(order_id), Integer.decode(part_id));
			OrderInWork order = DaoFactory.getOrderInWorkDaoImpl().getOrderByID(Integer.decode(order_id));
			List<Part> allParts = DaoFactory.getPartDaoImpl().getAllParts();
			List<OrderStatus> statuses1 = DaoFactory.getOrderStatusDaoImpl().getAllStatus();
			List<OrderWorker> orderWorkers = DaoFactory.getOrderWorkerDaoImpl()
					.getWorkersByOrderID(order.getOrder_id());
			request.setAttribute("state", "edit");
			request.setAttribute("statuses", statuses1);
			request.setAttribute("order", order);
			request.setAttribute("parts", DaoFactory.getPartOrderDaoImpl().getPartsByOrderId(order.getOrder_id()));
			request.setAttribute("workers",
					DaoFactory.getOrderWorkerDaoImpl().getWorkersByOrderID(order.getOrder_id()));
			request.setAttribute("allWorkers", DaoFactory.getWorkerDaoImpl().getAllWorkers());
			request.setAttribute("orderWorkers", orderWorkers);
			request.setAttribute("allParts", allParts);
			request.getRequestDispatcher("/WEB-INF/editOrder.jsp").forward(request, response);
			break;
		}
		case "car":
			String car_id = request.getParameter("car_id");
			if (!DaoFactory.getCarDaoImpl().deleteCarByVINNumber(
					DaoFactory.getCarDaoImpl().getCarByID(Integer.decode(car_id)).getVin_number()))
				request.setAttribute("message", "Can't delete car " + car_id + ". it used in another tables");
			response.sendRedirect("carPage");

			break;
		case "customer":
			String customer_id = request.getParameter("customer_id");
			Customer customer = DaoFactory.getCustomerDaoImpl().getCustomerByID(Integer.decode(customer_id));
			// DaoFactory.getUserDaoImpl().deleteUserByID(customer.getUser_id());
			DaoFactory.getCustomerDaoImpl().deleteCustomer(customer);
			response.sendRedirect("customerPage");
			break;
		case "worker":
			String worker_id = request.getParameter("worker_id");
			Worker worker = DaoFactory.getWorkerDaoImpl().getWorkerByID(Integer.decode(worker_id));
			// DaoFactory.getUserDaoImpl().deleteUserByID(worker.getUser_id());
			DaoFactory.getWorkerDaoImpl().deleteWorker(worker.getLast_name(), worker.getFirst_name());
			response.sendRedirect("workerPage");
			break;
		case "part": {
			String part_id = request.getParameter("part_id");
			DaoFactory.getPartDaoImpl().deletePartByID(Integer.decode(part_id));
			response.sendRedirect("partPage");
			break;
		}
		default:
			break;
		}
	}
	/*
	 * private List<OrderFullInfo> getOrdersInfo(List<OrderInWork> orderList) {
	 * List<OrderFullInfo> orders = new ArrayList<OrderFullInfo>(); for
	 * (OrderInWork order : orderList) { CarDaoImpl carDaoImpl =
	 * DaoFactory.getCarDaoImpl(); CustomerDaoImpl customerDaoImpl =
	 * DaoFactory.getCustomerDaoImpl(); OrderFullInfo fullInfo = new
	 * OrderFullInfo(); Car car = null;//
	 * carDaoImpl.getCarByID(order.getCar_id());
	 * fullInfo.setCarRegNumber(car.getReg_number());
	 * fullInfo.setCarModel(car.getCar_model());
	 * fullInfo.setCarDescription(car.getCar_description()); Customer customer =
	 * null;// customerDaoImpl.getCustomerByID(car.getCustomer_id());
	 * fullInfo.setCustomerFullName(customer.getLast_name() + " " +
	 * customer.getFirst_name()); if (order.getDatetime_finish() != null)
	 * fullInfo.setOrderFinishTime(order.getDatetime_finish().toString()); else
	 * fullInfo.setOrderFinishTime(null);
	 * fullInfo.setOrderStartTime(order.getDatetime_start().toString());
	 * OrderStatusDaoImpl orderStatus = DaoFactory.getOrderStatusDaoImpl(); //
	 * fullInfo.setOrderStatus(orderStatus.getStatusByID(order.
	 * getOrder_status_id()).getOrder_status_name());
	 * fullInfo.setOredrInfo(order.getOrder_description()); //
	 * fullInfo.setOrderId(order.getCar_id().toString()); orders.add(fullInfo);
	 * } return orders; }
	 * 
	 * private List<PartFullInfo> getFullPartsForOrder(Integer order_id) {
	 * List<PartFullInfo> list = new ArrayList<>(); List<PartOrder> parts =
	 * DaoFactory.getPartOrderDaoImpl().getPartsByOrderId(order_id); for
	 * (PartOrder partOrder : parts) { PartFullInfo info = new PartFullInfo();
	 * Part part = null;//
	 * DaoFactory.getPartDaoImpl().getPart(partOrder.getPart_id());
	 * info.setPart_id(part.getPart_id().toString());
	 * info.setPart_name(part.getPart_name()); info.setVendor(part.getVendor());
	 * info.setTotalQuantity(part.getAmount().toString());
	 * info.setQuntityInOrder(partOrder.getAmount().toString()); list.add(info);
	 * }
	 * 
	 * return list; }
	 */
}
