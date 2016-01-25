package com.nixsolutions.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.dao.impl.PartDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.entities.Part;

/**
 * Servlet implementation class PartSrvt
 */
public class PartSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceStationDAOFactoryImpl factory;
	private PartDAOImpl partImpl;

	/**
	 * @throws Exception 
	 * @see HttpServlet#HttpServlet()
	 */
	public PartSrvt() throws Exception {
		factory = new ServiceStationDAOFactoryImpl();
		partImpl = (PartDAOImpl) factory.getDao(Part.class);
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
		String part_id = request.getParameter("part_id") != null ? request.getParameter("part_id") : "";
		String vendor = request.getParameter("vendor") != null ? request.getParameter("vendor") : "";
		String part_name = request.getParameter("part_name") != null ? request.getParameter("part_name") : "";
		String amount = request.getParameter("amount") != null ? request.getParameter("amount") : "";
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		int amount_parts = NumberUtils.isDigits(amount) ? Integer.parseInt(amount) : 0;
		/// parse car id
		int partId = NumberUtils.isDigits(part_id) ? Integer.parseInt(part_id) : 0;
		if (partId > 0) {
			Part part = partImpl.findByPK(partId);
			if (action.equalsIgnoreCase("Edit")) {
				request.setAttribute("title", "Edit part");
				request.setAttribute("part", part);
				request.setAttribute("title", "Part");
				request.getRequestDispatcher("/WEB-INF/jsp/part.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Delete")) {
				partImpl.delete(part);
				request.setAttribute("destination", "Parts");
				request.setAttribute("title", "Parts");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				part.setAmount(amount_parts);
				part.setPart_name(part_name);
				part.setVendor(vendor);
				partImpl.update(part);
				request.setAttribute("destination", "Parts");
				request.setAttribute("title", "Parts");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				request.setAttribute("title", "Add part");
				request.getRequestDispatcher("/WEB-INF/jsp/part.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				Part part = new Part(part_name, vendor, amount_parts);
				partImpl.create(part);
				request.setAttribute("destination", "Parts");
				request.setAttribute("title", "Parts");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else {
				request.setAttribute("destination", "Parts");
				request.setAttribute("title", "Parts");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		}
	}

}
