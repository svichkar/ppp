package com.nixsolutions.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.dao.impl.StatusDAOImpl;
import com.nixsolutions.dao.impl.WorkerDAOImpl;
import com.nixsolutions.dao.impl.WorkerSpecificationDAOImpl;
import com.nixsolutions.entities.Status;
import com.nixsolutions.entities.Worker;
import com.nixsolutions.entities.WorkerSpecification;

/**
 * Servlet implementation class WorkerSrvt
 */
public class WorkerSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceStationDAOFactoryImpl factory;
	private WorkerDAOImpl workerImpl;
	private StatusDAOImpl statusImpl;
	private WorkerSpecificationDAOImpl workerspecImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public WorkerSrvt() throws Exception {
		factory = new ServiceStationDAOFactoryImpl();
		workerImpl = (WorkerDAOImpl) factory.getDao(Worker.class);
		workerspecImpl = (WorkerSpecificationDAOImpl) factory.getDao(WorkerSpecification.class);
		statusImpl = (StatusDAOImpl) factory.getDao(Status.class);
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
		String worker_id = request.getParameter("worker_id") != null ? request.getParameter("worker_id") : "";
		String f_name = request.getParameter("f_name") != null ? request.getParameter("f_name") : "";
		String l_name = request.getParameter("l_name") != null ? request.getParameter("l_name") : "";
		String status_id = request.getParameter("status_id") != null ? request.getParameter("status_id") : "";
		String spec_id = request.getParameter("spec_id") != null ? request.getParameter("spec_id") : "";
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		/// parse id
		int workerId = NumberUtils.isDigits(worker_id) ? Integer.parseInt(worker_id) : 0;
		int specId = NumberUtils.isDigits(spec_id) ? Integer.parseInt(spec_id) : 0;
		int statusId = NumberUtils.isDigits(status_id) ? Integer.parseInt(status_id) : 0;

		if (workerId > 0) {
			Worker worker = workerImpl.findByPK(workerId);
			if (action.equalsIgnoreCase("Edit")) {
				request.setAttribute("worker", worker);
				request.setAttribute("statuses", statusImpl.getAll());
				request.setAttribute("specifications", workerspecImpl.getAll());
				request.setAttribute("title", "Worker");
				request.getRequestDispatcher("/WEB-INF/jsp/worker.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Delete")) {
				workerImpl.delete(worker);
				request.setAttribute("title", "Workers");
				request.setAttribute("destination", "Workers");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				worker.setF_name(f_name);
				worker.setL_name(l_name);
				worker.setSpec_id(specId);
				worker.setStatus_id(statusId);
				workerImpl.update(worker);
				request.setAttribute("title", "Workers");
				request.setAttribute("destination", "Workers");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				request.setAttribute("title", "Add worker");
				request.getRequestDispatcher("/WEB-INF/jsp/worker.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				Worker worker = new Worker(f_name, l_name, specId, statusId);
				workerImpl.create(worker);
				request.setAttribute("destination", "Workers");
				request.setAttribute("title", "Workers");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else {
				request.setAttribute("destination", "Workers");
				request.setAttribute("title", "Workers");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		}
	}

}
