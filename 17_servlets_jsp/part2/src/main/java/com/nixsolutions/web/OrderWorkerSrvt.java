package com.nixsolutions.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.dao.impl.AllWorkersInOrderDAOImpl;
import com.nixsolutions.dao.impl.OrderWorkerDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.WorkerDAOImpl;
import com.nixsolutions.entities.AllWorkersInOrder;
import com.nixsolutions.entities.OrderWorker;
import com.nixsolutions.entities.Worker;

/**
 * Servlet implementation class OrderWorkerSrvt
 */
public class OrderWorkerSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceStationDAOFactoryImpl factory;
	private WorkerDAOImpl workersImpl;
	private OrderWorkerDAOImpl owImpl;
	private AllWorkersInOrderDAOImpl allWoImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderWorkerSrvt() throws Exception {
		factory = new ServiceStationDAOFactoryImpl();
		owImpl = (OrderWorkerDAOImpl) factory.getDao(OrderWorker.class);
		workersImpl = (WorkerDAOImpl) factory.getDao(Worker.class);
		allWoImpl = (AllWorkersInOrderDAOImpl) factory.getDao(AllWorkersInOrder.class);
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
		String order_id = request.getParameter("order_id") != null ? request.getParameter("order_id") : "";
		String worker_id = request.getParameter("worker_id") != null ? request.getParameter("worker_id") : "";
		String completed_value = request.getParameter("completed") != null ? request.getParameter("completed") : "";
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		/// parse id
		int orderId = NumberUtils.isDigits(order_id) ? Integer.parseInt(order_id) : 0;
		int workerId = NumberUtils.isDigits(worker_id) ? Integer.parseInt(worker_id) : 0;
		boolean completed = completed_value.length()>0 ? Boolean.parseBoolean(completed_value)  : false;
		OrderWorker orderworker = owImpl.findbyOrderAndWorker(orderId, workerId);
		///
		if (orderworker.getWorker_id() > 0 & orderworker.getId() > 0) {
			AllWorkersInOrder allorderworker = allWoImpl.findByOrderAndWorker(orderId, workerId);
			if (action.equalsIgnoreCase("Edit")) {
				request.setAttribute("order_id", orderId);
				request.setAttribute("worker", allorderworker);
				request.setAttribute("workers", workersImpl.getAll());
				request.setAttribute("action", "Edit");
				request.setAttribute("title", "Edit worker in order");
				request.getRequestDispatcher("/WEB-INF/jsp/editwo.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Delete")) {
				owImpl.delete(orderworker);
				request.setAttribute("order_id", orderId);
				request.setAttribute("action", "Edit");
				request.setAttribute("title", "Order");
				request.getRequestDispatcher("/order").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				orderworker.setWorker_id(workerId);
				orderworker.setId(orderId);
				orderworker.setCompleted(completed);
				owImpl.update(orderworker);
				request.setAttribute("order_id", orderId);
				request.setAttribute("action", "Edit");
				request.setAttribute("title", "Order");
				request.getRequestDispatcher("/order").forward(request, response);
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				request.setAttribute("action", "Add");
				request.setAttribute("order_id", orderId);
				request.setAttribute("workers", workersImpl.getAll());
				request.setAttribute("title", "Add worker in order");
				request.getRequestDispatcher("/WEB-INF/jsp/editwo.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				OrderWorker orderworkerNew = new OrderWorker(workerId, orderId, completed);
				owImpl.create(orderworkerNew);
				request.setAttribute("order_id", orderId);
				request.setAttribute("action", "Edit");
				request.setAttribute("title", "Order");
				request.getRequestDispatcher("/order").forward(request, response);
			} else {
				request.setAttribute("destination", "Orders");
				request.setAttribute("title", "Orders");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		}
	}

}
