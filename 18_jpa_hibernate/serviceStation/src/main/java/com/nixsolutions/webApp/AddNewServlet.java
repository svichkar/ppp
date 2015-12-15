package com.nixsolutions.webApp;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import com.nixsolutions.bean.CarFullInfo;
import com.nixsolutions.bean.PartFullInfo;
*/
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.OrderStatus;
import com.nixsolutions.entity.OrderWorker;
import com.nixsolutions.entity.Part;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.UserRole;
import com.nixsolutions.entity.Worker;
import com.nixsolutions.entity.WorkerSpecialization;

/**
 * Servlet implementation class AddNewServlet
 */
public class AddNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = request.getParameter("target");
		if (target == null)
			target = request.getSession().getAttribute("target").toString();
		String homePage = request.getParameter("homePage");
		switch (target) {
		case "order":
			List<Car> carsList = null;
			carsList = DaoFactory.getCarDaoImpl().getAllCar();
			request.setAttribute("carList", carsList);

			request.setAttribute("state", "new");
			request.setAttribute("homePage", homePage);
			request.getRequestDispatcher("/WEB-INF/newOrder.jsp").forward(request, response);
			break;
		case "car":
			List<Customer> customers = null;
			customers = DaoFactory.getCustomerDaoImpl().getAllCustomers();
			request.setAttribute("customers", customers);

			request.setAttribute("state", "new");
			request.setAttribute("homePage", homePage);
			request.getRequestDispatcher("/WEB-INF/newCar.jsp").forward(request, response);
			break;
		case "customer":
			request.getRequestDispatcher("/WEB-INF/newCustomer.jsp").forward(request, response);
			break;
		case "worker":
			request.setAttribute("specs", DaoFactory.getWorkerSpecializationDaoImpl().getAllSpecialization());
			request.getRequestDispatcher("/WEB-INF/newWorker.jsp").forward(request, response);
			break;
		case "part":
			request.getRequestDispatcher("/WEB-INF/newPart.jsp").forward(request, response);
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = request.getParameter("target");
		switch (target) {
		case "Create order":
			createNewOrder(request, response);
			break;
		case "car":
			doGet(request, response);
			break;
		case "Create Car":
			createNewCar(request, response);
			break;
		case "Create Customer":
			createNewCustomer(request, response);
			break;
		case "Create Worker":
			createNewWorker(request, response);
			break;
		case "Create Part":
			createNewPart(request, response);
			break;
		default:
			break;
		}

	}

	public void createNewPart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String part_name = request.getParameter("part_name");
		String vendor = request.getParameter("vendor");
		String amount = request.getParameter("amount");
		DaoFactory.getPartDaoImpl().addNewPart(part_name, vendor, Integer.decode(amount));
		response.sendRedirect("partPage");
	}

	public void createNewWorker(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String last_name = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		String specialization_id = request.getParameter("specialization_id");
		String homePage = request.getParameter("homePage");
		String userLogin = request.getParameter("userLogin");
		String userPassword = request.getParameter("userPassword");
		UserRole role = null;
		WorkerSpecialization specialization = DaoFactory.getWorkerSpecializationDaoImpl()
				.getSpecialization(Integer.parseInt(specialization_id));

		if (specialization.getSpecialization_name().equalsIgnoreCase("storekeeper")) {
			role = DaoFactory.getUserRoleDaoImpl().getUserRole("storekeeper");
		} else if (specialization.getSpecialization_name().equalsIgnoreCase("manager")) {
			role = DaoFactory.getUserRoleDaoImpl().getUserRole("manager");
		} else
			role = DaoFactory.getUserRoleDaoImpl().getUserRole("worker");
		DaoFactory.getUserDaoImpl().createNewUser(userLogin, userPassword, role.getUser_role_id());
		User user = DaoFactory.getUserDaoImpl().getUserByLogin(userLogin);
		Worker worker = new Worker();
		worker.setFirst_name(first_name);
		worker.setLast_name(last_name);
		worker.setSpecialization(specialization);
		worker.setUser(user);
		worker.setWorker_status(DaoFactory.getWorkerStatusDaoImpl().getWorkerStatus("free"));
		DaoFactory.getWorkerDaoImpl().createWorker(worker);
		response.sendRedirect("workerPage");
	}

	public void createNewCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String last_name = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		String phone = request.getParameter("phone");
		String homePage = request.getParameter("homePage");
		String userLogin = request.getParameter("userLogin");
		String userPassword = request.getParameter("userPassword");
		DaoFactory.getUserDaoImpl().createNewUser(userLogin, userPassword, 2);
		User user = DaoFactory.getUserDaoImpl().getUserByLogin(userLogin);
		Customer customer = new Customer();
		customer.setFirst_name(first_name);
		customer.setLast_name(last_name);
		customer.setPhone(phone);
		customer.setUser(user);
		DaoFactory.getCustomerDaoImpl().createNewCustomer(customer);
		response.sendRedirect("customerPage");
	}

	public void createNewCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String customerID = request.getParameter("customer");
		String regNumber = request.getParameter("regNumber");
		String vinNumber = request.getParameter("vinNumber");
		String homePage = request.getParameter("homePage");
		String carModel = request.getParameter("carModel");
		Car car = new Car();
		car.setCar_model(carModel);
		car.setReg_number(regNumber);
		car.setVin_number(vinNumber);
		car.setCustomer(DaoFactory.getCustomerDaoImpl().getCustomerByID(Integer.decode(customerID)));
		DaoFactory.getCarDaoImpl().createNewCar(car);
		if (homePage.equalsIgnoreCase("newOrder")) {
			request.getSession().setAttribute("target", "order");
			response.sendRedirect("addNew");
		} else if (homePage.equalsIgnoreCase("carPage")) {
			response.sendRedirect("carPage");
		}
	}

	public void createNewOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String regNumber = request.getParameter("regNumber");
		String description = request.getParameter("description");
		OrderInWork orderInWork = new OrderInWork();
		orderInWork.setCar(DaoFactory.getCarDaoImpl().getCarByRegNumber(regNumber));
		orderInWork.setOrder_description(description);
		orderInWork.setDatetime_start(Calendar.getInstance().getTime());
		DaoFactory.getOrderInWorkDaoImpl().createNewOrder(orderInWork);
		OrderInWork order = DaoFactory.getOrderInWorkDaoImpl().getOrderInWorkByCar(regNumber);
		List<Part> allParts = DaoFactory.getPartDaoImpl().getAllParts();
		List<OrderStatus> statuses1 = DaoFactory.getOrderStatusDaoImpl().getAllStatus();
		List<OrderWorker> orderWorkers = DaoFactory.getOrderWorkerDaoImpl().getWorkersByOrderID(order.getOrder_id());
		request.setAttribute("state", "edit");
		request.setAttribute("statuses", statuses1);
		request.setAttribute("order", order);
		request.setAttribute("parts", DaoFactory.getPartOrderDaoImpl().getPartsByOrderId(order.getOrder_id()));
		request.setAttribute("workers", DaoFactory.getOrderWorkerDaoImpl().getWorkersByOrderID(order.getOrder_id()));
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
	 * private List<CarFullInfo> getCarFullInfo(List<Car> carsList) {
	 * List<CarFullInfo> list = new ArrayList<>(); for (Car car : carsList) {
	 * CarFullInfo info = new CarFullInfo();
	 * info.setCar_ID(car.getCar_id().toString());
	 * info.setCarModel(car.getCar_model());
	 * info.setRegNumber(car.getReg_number());
	 * info.setVinNumber(car.getVin_number()); Customer carOwner = null;//
	 * DaoFactory.getCustomerDaoImpl().getCustomerByID(car.getCustomer_id());
	 * info.setCarOwner(carOwner.getLast_name() + " " +
	 * carOwner.getFirst_name()); info.setOwnerPhone(carOwner.getPhone());
	 * list.add(info); } return list; }
	 */
}
