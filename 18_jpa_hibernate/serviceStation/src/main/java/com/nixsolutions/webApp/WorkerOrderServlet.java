package com.nixsolutions.webApp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import com.nixsolutions.bean.OrderFullInfo;
import com.nixsolutions.bean.PartFullInfo;
import com.nixsolutions.bean.WorkerFullInfo;
*/
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.entity.WorkerStatus;

/**
 * Servlet implementation class WorkerOrderServlet
 */
public class WorkerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String order_id = request.getParameter("order_id");
		String worker_id = request.getParameter("worker_id");
		String action = request.getParameter("action");
		switch (action) {
		case "edit": {
			boolean isFinished = request.getParameter("isFinished").equals("yes");
			DaoFactory.getOrderWorkerDaoImpl().changeStatus(Integer.decode(order_id), Integer.decode(worker_id),
					isFinished);
			Worker worker = DaoFactory.getWorkerDaoImpl().getWorkerByID(Integer.decode(worker_id));
			WorkerStatus worker_status = DaoFactory.getWorkerStatusDaoImpl().getWorkerStatus(2);
			worker.setWorker_status(worker_status);
			DaoFactory.getWorkerDaoImpl().updateWorker(worker);
			
		}
			break;
		case "add": {
			DaoFactory.getOrderWorkerDaoImpl().assignWorkerToOrder(Integer.decode(order_id), Integer.decode(worker_id));
			Worker worker = DaoFactory.getWorkerDaoImpl().getWorkerByID(Integer.decode(worker_id));
			WorkerStatus worker_status = DaoFactory.getWorkerStatusDaoImpl().getWorkerStatus(1);
			worker.setWorker_status(worker_status);
			DaoFactory.getWorkerDaoImpl().updateWorker(worker);
		}
			break;
		}
		OrderInWork order = DaoFactory.getOrderInWorkDaoImpl().getOrderByID(Integer.decode(order_id));
		List<Part> allParts = DaoFactory.getPartDaoImpl().getAllParts();
		List<OrderStatus> statuses1 = DaoFactory.getOrderStatusDaoImpl().getAllStatus();
		List<OrderWorker> orderWorkers = DaoFactory.getOrderWorkerDaoImpl().getWorkersByOrderID(Integer.decode(order_id));
		request.setAttribute("state", "edit");
		request.setAttribute("statuses", statuses1);
		request.setAttribute("order", order);
		request.setAttribute("parts", DaoFactory.getPartOrderDaoImpl().getPartsByOrderId(Integer.decode(order_id)));
		request.setAttribute("workers",
				DaoFactory.getOrderWorkerDaoImpl().getWorkersByOrderID(Integer.decode(order_id)));
		request.setAttribute("allWorkers", DaoFactory.getWorkerDaoImpl().getAllWorkers());
		request.setAttribute("orderWorkers", orderWorkers);
		request.setAttribute("allParts", allParts);
		request.getRequestDispatcher("/WEB-INF/editOrder.jsp").forward(request, response);
	}

	/*
	 * private List<PartFullInfo> getFullPartsForOrder(Integer order_id) {
	 * List<PartFullInfo> list = new ArrayList<>(); List<PartOrder> parts =
	 * DaoFactory.getPartOrderDaoImpl().getPartsByOrderId(order_id); for
	 * (PartOrder partOrder : parts) { PartFullInfo info = new PartFullInfo();
	 * Part part =
	 * null;//DaoFactory.getPartDaoImpl().getPart(partOrder.getPart_id());
	 * info.setPart_id(part.getPart_id().toString());
	 * info.setPart_name(part.getPart_name()); info.setVendor(part.getVendor());
	 * info.setTotalQuantity(part.getAmount().toString());
	 * info.setQuntityInOrder(partOrder.getAmount().toString()); list.add(info);
	 * }
	 * 
	 * return list; }
	 * 
	 * private List<Worker> getAssignedWorkers(Integer order_id) {
	 * List<OrderWorker> orderWorkers =
	 * DaoFactory.getOrderWorkerDaoImpl().getWorkersByOrderID(order_id);
	 * List<Worker> workerList = new ArrayList<>(); for (OrderWorker orWor :
	 * orderWorkers) { //
	 * workerList.add(DaoFactory.getWorkerDaoImpl().getWorkerByID(orWor.
	 * getWorker_id())); } return workerList; }
	 * 
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
	 * else { info.setActiveOrder( "Order id: " + work.getOrder_id() +
	 * "; start time: " + work.getDatetime_start() + "; "); } list.add(info); }
	 * return list; }
	 */
}
