package com.nixsolutions.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.bean.AllPartsInOrderBean;
import com.nixsolutions.dao.impl.OrderInWorkDAOImpl;
import com.nixsolutions.dao.impl.PartDAOImpl;
import com.nixsolutions.dao.impl.PartOrderDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.entities.AllPartsInOrder;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.entities.Part;
import com.nixsolutions.entities.PartOrder;

/**
 * Servlet implementation class OrderPartSrvt
 */
public class OrderPartSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceStationDAOFactoryImpl factory;
	private PartDAOImpl partsImpl;
	private OrderInWorkDAOImpl oiwImpl;
	private PartOrderDAOImpl poImpl;
	private AllPartsInOrderBean allPoImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderPartSrvt() throws Exception {
		factory = new ServiceStationDAOFactoryImpl();
		partsImpl = (PartDAOImpl) factory.getDao(Part.class);
		poImpl = (PartOrderDAOImpl) factory.getDao(PartOrder.class);
		allPoImpl = (AllPartsInOrderBean) factory.getDao(AllPartsInOrder.class);
		oiwImpl = (OrderInWorkDAOImpl) factory.getDao(OrderInWork.class);
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
		String part_id = request.getParameter("part_id") != null ? request.getParameter("part_id") : "";
		String amountParts = request.getParameter("amount") != null ? request.getParameter("amount") : "";
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		/// parse id
		long orderId = NumberUtils.isDigits(order_id) ? Integer.parseInt(order_id) : 0;
		long partId = NumberUtils.isDigits(part_id) ? Integer.parseInt(part_id) : 0;
		long amount = NumberUtils.isDigits(amountParts) ? Integer.parseInt(amountParts) : 0;
		PartOrder orderpart = poImpl.findbyPartAndOrder(orderId, partId);
		///
		if (orderpart != null) {
			AllPartsInOrder allorderpart = allPoImpl.findByPartAndOrder(orderId, partId);
			if (action.equalsIgnoreCase("Edit")) {
				request.setAttribute("title", "Edit order");
				request.setAttribute("order_id", orderId);
				request.setAttribute("part", allorderpart);
				request.setAttribute("parts", poImpl.getAll());
				request.setAttribute("action", "Edit");
				request.getRequestDispatcher("/WEB-INF/jsp/editpo.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Delete")) {
				poImpl.delete(orderpart);
				request.setAttribute("order_id", orderId);
				request.setAttribute("action", "Edit");
				request.getRequestDispatcher("/order").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				orderpart.setPart(partsImpl.findByPK(partId));
				orderpart.setOrder(oiwImpl.findByPK(orderId));
				orderpart.setAmount(amount);
				poImpl.update(orderpart);
				request.setAttribute("order_id", orderId);
				request.setAttribute("action", "Edit");
				request.getRequestDispatcher("/order").forward(request, response);
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				request.setAttribute("title", "Add order");
				request.setAttribute("action", "Add");
				request.setAttribute("order_id", orderId);
				request.setAttribute("parts", partsImpl.getAll());
				request.getRequestDispatcher("/WEB-INF/jsp/editpo.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				PartOrder orderpartNew = new PartOrder(oiwImpl.findByPK(orderId), partsImpl.findByPK(partId), amount);
				poImpl.create(orderpartNew);
				request.setAttribute("title", "Orders");
				request.setAttribute("order_id", orderId);
				request.setAttribute("action", "Edit");
				request.getRequestDispatcher("/order").forward(request, response);
			} else {
				request.setAttribute("title", "Orders");
				request.setAttribute("destination", "Orders");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		}
	}

}
