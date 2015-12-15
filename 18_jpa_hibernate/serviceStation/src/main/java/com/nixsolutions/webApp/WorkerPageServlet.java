package com.nixsolutions.webApp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.nixsolutions.bean.WorkerFullInfo;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Worker;

/**
 * Servlet implementation class WorkerPageServlet
 */
public class WorkerPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Worker> workerList = null;
		workerList = DaoFactory.getWorkerDaoImpl().getAllWorkers();
		request.setAttribute("workerList", workerList);
		request.getRequestDispatcher("/WEB-INF/workerPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/*
	 * private List<WorkerFullInfo> getWorkerFullInfo(List<Worker> workerList) {
	 * List<WorkerFullInfo> list = new ArrayList<>(); for (Worker worker :
	 * workerList) { WorkerFullInfo info = new WorkerFullInfo();
	 * info.setWorker_id(worker.getWorker_id().toString());
	 * info.setFullName(worker.getLast_name() + " " + worker.getFirst_name());
	 * WorkerSpecialization specialization =
	 * null;//DaoFactory.getWorkerSpecializationDaoImpl()
	 * .getSpecialization(worker.getSpecialization_id());
	 * info.setSpecialization(specialization.getSpecialization_name());
	 * WorkerStatus status =
	 * null;//DaoFactory.getWorkerStatusDaoImpl().getWorkerStatus(worker.
	 * getWorker_status_id()); info.setStatus(status.getWorker_status_name());
	 * User user =
	 * null;//DaoFactory.getUserDaoImpl().getUserByID(worker.getUser_id());
	 * info.setLogin(user.getUser_login());
	 * info.setPassword(user.getUser_password()); OrderInWork work =
	 * DaoFactory.getOrderWorkerDaoImpl().getActiveOrderByWorkerID(worker.
	 * getWorker_id()); if (work == null) { info.setActiveOrder("free time"); }
	 * else{ info.setActiveOrder("Order id: "+work.getOrder_id()+
	 * "; start time: "+work.getDatetime_start()+"; "); } list.add(info); }
	 * return list; }
	 */}
