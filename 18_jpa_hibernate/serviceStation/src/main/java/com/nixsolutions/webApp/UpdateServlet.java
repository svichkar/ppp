package com.nixsolutions.webApp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import com.nixsolutions.bean.CarFullInfo;
import com.nixsolutions.bean.CustomerFullInfo;
import com.nixsolutions.bean.OrderFullInfo;
import com.nixsolutions.bean.PartFullInfo;
import com.nixsolutions.bean.WorkerFullInfo;*/
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
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
		String target = request.getParameter("target");
		switch (target) {
		case "updateOrder": {
			String order_id = request.getParameter("order_id");
			String order_status_id = request.getParameter("order_status_id");
			String description = request.getParameter("order_description");
			if (order_status_id != null) {
				DaoFactory.getOrderInWorkDaoImpl().changeOrderStatusByOrderID(Integer.decode(order_id),
						Integer.decode(order_status_id));
			}
			DaoFactory.getOrderInWorkDaoImpl().updateOrderDescriptionByID(Integer.decode(order_id), description);
			List<OrderInWork> orderList = null;
			orderList = DaoFactory.getOrderInWorkDaoImpl().getAllOrders();
			request.setAttribute("orders", orderList);
			request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);
			break;
		}
		case "updateCar": {
			String car_id = request.getParameter("car_id");
			String carModel = request.getParameter("carModel");
			String regNumber = request.getParameter("regNumber");
			String vinNumber = request.getParameter("vinNumber");
			String carOwner = request.getParameter("carOwner");
			String car_description = request.getParameter("car_description");
			String customer_id = request.getParameter("customer_id");
			Car car = DaoFactory.getCarDaoImpl().getCarByID(Integer.decode(car_id));
			car.setCar_description(car_description);
			car.setCar_model(carModel);
			car.setReg_number(regNumber);
			car.setVin_number(vinNumber);
			car.setCustomer(DaoFactory.getCustomerDaoImpl().getCustomerByID(Integer.decode(customer_id)));

			DaoFactory.getCarDaoImpl().updateCarByID(car);
			response.sendRedirect("carPage");

			break;
		}
		case "updateCustomer": {
			String customer_id = request.getParameter("customer_id");
			String last_name = request.getParameter("last_name");
			String first_name = request.getParameter("first_name");
			String phone = request.getParameter("phone");
			String user_login = request.getParameter("user_login");
			String user_password = request.getParameter("user_password");
			String user_id = request.getParameter("user_id");

			User user = DaoFactory.getUserDaoImpl().getUserByID(Integer.decode(user_id));
			user.setUser_login(user_login);
			user.setUser_password(user_password);
			DaoFactory.getUserDaoImpl().updateUser(user);

			Customer customer = DaoFactory.getCustomerDaoImpl().getCustomerByID(Integer.decode(customer_id));
			customer.setFirst_name(first_name);
			customer.setLast_name(last_name);
			customer.setPhone(phone);
			DaoFactory.getCustomerDaoImpl().updateCustomer(customer);

			response.sendRedirect("customerPage");

			break;
		}
		case "updateWorker": {
			String worker_id = request.getParameter("worker_id");
			String last_name = request.getParameter("last_name");
			String first_name = request.getParameter("first_name");
			String specialization_id = request.getParameter("specialization_id");
			String user_login = request.getParameter("user_login");
			String user_password = request.getParameter("user_password");
			String user_id = request.getParameter("user_id");

			User user = DaoFactory.getUserDaoImpl().getUserByID(Integer.decode(user_id));
			user.setUser_login(user_login);
			user.setUser_password(user_password);
			DaoFactory.getUserDaoImpl().updateUser(user);

			Worker worker = DaoFactory.getWorkerDaoImpl().getWorkerByID(Integer.decode(worker_id));
			worker.setFirst_name(first_name);
			worker.setLast_name(last_name);
			// worker.setSpecialization_id(Integer.decode(specialization_id));
			DaoFactory.getWorkerDaoImpl().updateWorker(worker);

			response.sendRedirect("workerPage");

			break;
		}
		case "updatePart": {
			String part_name = request.getParameter("part_name");
			String vendor = request.getParameter("vendor");
			String amount = request.getParameter("amount");
			String part_id = request.getParameter("part_id");
			Part part = DaoFactory.getPartDaoImpl().getPart(Integer.decode(part_id));
			part.setAmount(Integer.decode(amount));
			part.setPart_name(part_name);
			part.setVendor(vendor);
			DaoFactory.getPartDaoImpl().updateExistingPart(part);

			response.sendRedirect("partPage");

			break;
		}
		case "Edit order":
			editExistingOrder(request, response);
			break;
		case "Edit Car":
			editExistingCar(request, response);
			break;
		case "Edit Customer":
			editExistingCustomer(request, response);
			break;
		case "Edit Worker":
			editExistingWorker(request, response);
			break;
		case "Edit Part":
			editExistingPart(request, response);
			break;

		default:
			break;
		}

	}

	public void editExistingPart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String part_id = request.getParameter("part_id");
		Part part = DaoFactory.getPartDaoImpl().getPart(Integer.decode(part_id));
		request.setAttribute("part", part);
		request.getRequestDispatcher("/WEB-INF/editPart.jsp").forward(request, response);
	}

	public void editExistingWorker(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String worker_id = request.getParameter("worker_id");
		Worker worker = DaoFactory.getWorkerDaoImpl().getWorkerByID(Integer.decode(worker_id));
		User user = null;// DaoFactory.getUserDaoImpl().getUserByID(worker.getUser_id());
		request.setAttribute("worker", worker);
		request.setAttribute("user", user);
		request.setAttribute("specs", DaoFactory.getWorkerSpecializationDaoImpl().getAllSpecialization());
		request.getRequestDispatcher("/WEB-INF/editWorker.jsp").forward(request, response);
	}

	public void editExistingCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customer_id = request.getParameter("customer_id");
		Customer customer = DaoFactory.getCustomerDaoImpl().getCustomerByID(Integer.decode(customer_id));
		User user = null;// DaoFactory.getUserDaoImpl().getUserByID(customer.getUser_id());
		request.setAttribute("customer", customer);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/editCustomer.jsp").forward(request, response);
	}

	public void editExistingOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String order_id = request.getParameter("order_id");
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

	public void editExistingCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String car_id = request.getParameter("car_id");
		request.setAttribute("car", DaoFactory.getCarDaoImpl().getCarByID(Integer.decode(car_id)));
		request.setAttribute("customers", DaoFactory.getCustomerDaoImpl().getAllCustomers());
		request.getRequestDispatcher("/WEB-INF/editCar.jsp").forward(request, response);
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
	 * fullInfo.setOredrInfo(order.getOrder_description());
	 * fullInfo.setOrderId(order.getOrder_id().toString());
	 * orders.add(fullInfo); } return orders; }
	 * 
	 * private List<Worker> getAssignedWorkers(Integer order_id) {
	 * List<OrderWorker> orderWorkers =
	 * DaoFactory.getOrderWorkerDaoImpl().getWorkersByOrderID(order_id);
	 * List<Worker> workerList = new ArrayList<>(); for (OrderWorker orWor :
	 * orderWorkers) { //
	 * workerList.add(DaoFactory.getWorkerDaoImpl().getWorkerByID(orWor.
	 * getWorker_id())); } return workerList; }
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
	 * 
	 * private List<WorkerFullInfo> getWorkerFullInfo(List<Worker> workerList) {
	 * List<WorkerFullInfo> list = new ArrayList<>(); for (Worker worker :
	 * workerList) { WorkerFullInfo info = new WorkerFullInfo();
	 * info.setWorker_id(worker.getWorker_id().toString());
	 * info.setFullName(worker.getLast_name() + " " + worker.getFirst_name());
	 * WorkerSpecialization specialization = null;//
	 * DaoFactory.getWorkerSpecializationDaoImpl() //
	 * .getSpecialization(worker.getSpecialization_id());
	 * info.setSpecialization(specialization.getSpecialization_name());
	 * WorkerStatus status = null;//
	 * DaoFactory.getWorkerStatusDaoImpl().getWorkerStatus(worker.
	 * getWorker_status_id()); info.setStatus(status.getWorker_status_name());
	 * User user = null;//
	 * DaoFactory.getUserDaoImpl().getUserByID(worker.getUser_id());
	 * info.setLogin(user.getUser_login());
	 * info.setPassword(user.getUser_password()); OrderInWork work =
	 * DaoFactory.getOrderWorkerDaoImpl().getActiveOrderByWorkerID(worker.
	 * getWorker_id()); if (work == null) { info.setActiveOrder("free time"); }
	 * else { info.setActiveOrder( "Order id: " + work.getOrder_id() +
	 * "; start time: " + work.getDatetime_start() + "; "); } list.add(info); }
	 * return list; }
	 * 
	 * private CarFullInfo getCarFullInfo(Car car) { CarFullInfo info = new
	 * CarFullInfo(); info.setCar_ID(car.getCar_id().toString());
	 * info.setCarModel(car.getCar_model());
	 * info.setRegNumber(car.getReg_number());
	 * info.setVinNumber(car.getVin_number()); Customer carOwner = null;//
	 * DaoFactory.getCustomerDaoImpl().getCustomerByID(car.getCustomer_id());
	 * info.setCarOwner(carOwner.getLast_name() + " " +
	 * carOwner.getFirst_name()); info.setOwnerPhone(carOwner.getPhone());
	 * return info; }
	 * 
	 * private List<CustomerFullInfo> getCustomerFullInfo(List<Customer>
	 * customerList) { List<CustomerFullInfo> list = new ArrayList<>(); for
	 * (Customer customer : customerList) { CustomerFullInfo info = new
	 * CustomerFullInfo();
	 * info.setCustomer_id(customer.getCustomer_id().toString());
	 * info.setFullName(customer.getLast_name() + " " +
	 * customer.getFirst_name()); info.setPhoneNumber(customer.getPhone()); User
	 * user = null;//
	 * DaoFactory.getUserDaoImpl().getUserByID(customer.getUser_id());
	 * info.setUserName(user.getUser_login());
	 * info.setUserPassword(user.getUser_password()); list.add(info); } return
	 * list; }
	 */
}
