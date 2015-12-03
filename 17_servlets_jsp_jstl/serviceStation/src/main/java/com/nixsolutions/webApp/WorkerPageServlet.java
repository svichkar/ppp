package com.nixsolutions.webApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.bean.WorkerFullInfo;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.entity.WorkerSpecialization;
import com.nixsolutions.entity.WorkerStatus;

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
		request.setAttribute("workerList", getWorkerFullInfo(workerList));
		request.getRequestDispatcher("/WEB-INF/workerPage.jsp").forward(request, response);
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

	private List<WorkerFullInfo> getWorkerFullInfo(List<Worker> workerList) {
		List<WorkerFullInfo> list = new ArrayList<>();
		for (Worker worker : workerList) {
			WorkerFullInfo info = new WorkerFullInfo();
			info.setWorker_id(worker.getWorker_id().toString());
			info.setFullName(worker.getLast_name() + " " + worker.getFirst_name());
			WorkerSpecialization specialization = DaoFactory.getWorkerSpecializationDaoImpl()
					.getSpecialization(worker.getSpecialization_id());
			info.setSpecialization(specialization.getSpecialization_name());
			WorkerStatus status = DaoFactory.getWorkerStatusDaoImpl().getWorkerStatus(worker.getWorker_status_id());
			info.setStatus(status.getWorker_status_name());
			User user = DaoFactory.getUserDaoImpl().getUserByID(worker.getUser_id());
			info.setLogin(user.getUser_login());
			info.setPassword(user.getUser_password());
			OrderInWork work = DaoFactory.getOrderWorkerDaoImpl().getActiveOrderByWorkerID(worker.getWorker_id());
			if (work == null) {
				info.setActiveOrder("free time");
			} else{
				info.setActiveOrder("Order id: "+work.getOrder_id()+"; start time: "+work.getDatetime_start()+"; ");
			}
				list.add(info);
		}
		return list;
	}
}
