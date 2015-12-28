package com.nixsolutions.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.CarDao;
import com.nixsolutions.dao.CustomerDao;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.OrderInWorkDao;
import com.nixsolutions.dao.OrderStatusDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.dao.UserRoleDao;
import com.nixsolutions.dao.WorkerDao;
import com.nixsolutions.dao.WorkerSpecializationDao;
import com.nixsolutions.dao.WorkerStatusDao;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;

/**
 * @author Михаил
 *
 */
@WebServlet("/login")
public class LoginPage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3337700405693703447L;

	private final String BODY_STYLE = "body {	width: 100%;	height: 100%;	font-family: 'Open Sans', sans-serif;	background: #092756;	"
			+ "background: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104, 128, 138, .4)		10%, rgba(138, 114, 76, 0) 40%),"
			+ "-moz-linear-gradient(top, rgba(57, 173, 219, .25) 0%,		rgba(42, 60, 87, .4) 100%),		"
			+ "-moz-linear-gradient(-45deg, #670d10 0%, #092756 100%);	background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104, 128, 138, .4)		"
			+ "10%, rgba(138, 114, 76, 0) 40%), -webkit-linear-gradient(top, rgba(57, 173, 219, .25) 0%,		rgba(42, 60, 87, .4) 100%),"
			+ "-webkit-linear-gradient(-45deg, #670d10 0%, #092756 100%);	background: -o-radial-gradient(0% 100%, ellipse cover, rgba(104, 128, 138, .4)		10%, rgba(138, 114, 76, 0) 40%),"
			+ "-o-linear-gradient(top, rgba(57, 173, 219, .25) 0%, rgba(42, 60, 87, .4) 100%),		-o-linear-gradient(-45deg, #670d10 0%, #092756 100%);	"
			+ "background: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104, 128, 138, .4)		10%, rgba(138, 114, 76, 0) 40%),		-ms-linear-gradient(top, rgba(57, 173, 219, .25) 0%,		"
			+ "rgba(42, 60, 87, .4) 100%), -ms-linear-gradient(-45deg, #670d10 0%, #092756 100%);	background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104, 128, 138, .4)		10%, rgba(138, 114, 76, 0) 40%),		"
			+ "linear-gradient(to bottom, rgba(57, 173, 219, .25) 0%,rgba(42, 60, 87, .4) 100%),		linear-gradient(135deg, #670d10 0%, #092756 100%);	filter: "
			+ "progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D', endColorstr='#092756', GradientType=1);}"

			+ "p, h1, h3, td {	color: #fff;	text-shadow: 0 0 10px rgba(0, 0, 0, 0.3);	letter-spacing: 1px;	text-align: center;}"

			+ ".btn {	display: inline-block;	*display: inline;	*zoom: 1;	padding: 4px 10px 4px;	margin-bottom: 0;	font-size: 13px;"
			+ "line-height: 18px;	color: #333333;	text-align: center;	text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);	vertical-align: middle;"
			+ "	background-color: #f5f5f5;	background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);	background-image: -ms-linear-gradient(top, #ffffff, #e6e6e6);"
			+ "	background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff),		to(#e6e6e6));	background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);"
			+ "	background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);	background-image: linear-gradient(top, #ffffff, #e6e6e6);	background-repeat: repeat-x;"
			+ "	filter: progid:dximagetransform.microsoft.gradient(startColorstr=#ffffff,		endColorstr=#e6e6e6, GradientType=0);"
			+ "	border-color: #e6e6e6 #e6e6e6 #e6e6e6;	border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);	border: 1px solid #e6e6e6;	-webkit-border-radius: 4px;"
			+ "	-moz-border-radius: 4px;	border-radius: 4px;	-webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px		rgba(0, 0, 0, 0.05);	-moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px"
			+ "		rgba(0, 0, 0, 0.05);	box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px		rgba(0, 0, 0, 0.05);	cursor: pointer;	*margin-left: .3em;"
			+ "}";

	@Override
	public void init() {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao userDao = DaoFactory.getUserDao();
		PrintWriter out = response.getWriter();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if (request.getSession().getAttribute("login") != null
				&& userDao.getUser() != null
				&& request.getSession().getAttribute("login").equals(login)) {
			out.write(generateHtml(userDao.getUser()));
		} else {
			if (userDao.validate(login, password)) {
				request.getSession().setAttribute("login", login);
				request.getSession().setAttribute("user_id", userDao.getUser().getUserId());
				request.getSession().setAttribute("role", userDao.getUser().getUserRoleId());
				out.write(generateHtml(userDao.getUser()));
			} else {
				request.getSession().removeAttribute("login");
				request.getSession().removeAttribute("user_id");
				request.getSession().removeAttribute("role");
				response.sendRedirect("");
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer userId = Integer.decode(request.getSession().getAttribute("user_id").toString());
		request.getParameter("password");
		PrintWriter out = response.getWriter();
		UserDao userDao = DaoFactory.getUserDao();
		userDao.getUserByID(userId);
		out.write(generateHtml(userDao.getUser()));
	}

	private String generateHtml(User user) {
		String html = "";
		if (user.getUserRoleId().equals(1)) {
			html = "<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
					+ "<header><div align=\"center\"><h1> MANGER </h1></div></header><body>" + createPage(user)
					+ "</body>" + "<footer>Created by Lelyakov M.A.</footer>" + "</html>";
		} else if (user.getUserRoleId().equals(2)) {
			CustomerDao customerDaoImpl = DaoFactory.getCustomerDao();
			html = "<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
					+ "<header><div align=\"center\"><h1>Hello "
					+ customerDaoImpl.getCustomerByUserID(user.getUserId()).toString() + "</h1></div></header><body>"
					+ createPage(user) + "</body>" + "<footer>Created by Lelyakov M.A.</footer>" + "</html>";

		}
		return html;
	}

	private String createPage(User user) {
		StringBuilder page = new StringBuilder();
		if (user.getUserRoleId().equals(1)) {
			page.append("<div align=\"center\"><h1> ORDERS </h1></div>");
			page.append("<table align=\"center\" width=40% border=1 cellpadding=8\">");
			page.append("<tr>");
			page.append("<td>Order ID</td>");
			page.append("<td>Description</td>");
			page.append("<td>Start time</td>");
			page.append("<td>Finish time</td>");
			page.append("<td>Status</td>");
			page.append("<td>Car</td>");
			page.append("</tr>");
			OrderInWorkDao workDaoImpl = DaoFactory.getOrderInWorkDao();
			OrderStatusDao statusDaoImpl = DaoFactory.getOrderStatusDao();
			CarDao carDaoImpl = DaoFactory.getCarDao();
			for (OrderInWork work : workDaoImpl.getAllOrders()) {
				page.append("<tr>");
				page.append("<td>" + work.getOrderId() + "</td>");
				page.append("<td>" + work.getOrderDescription() + "</td>");
				page.append("<td>" + work.getDatetimeStart() + "</td>");
				page.append("<td>" + work.getDatetimeFinish() + "</td>");
				page.append("<td>" + statusDaoImpl.getStatusByID(work.getOrderStatusId()).getStatusName() + "</td>");
				page.append("<td>" + carDaoImpl.getCarByID(work.getCarId()).toString() + "</td>");
				page.append("</tr>");

			}
			page.append("</table>");
			page.append("<div align=\"center\"><h1> CUSTOMERS </h1>");
			page.append("</div>");
			page.append("<table align=\"center\" width=40% border=1 cellpadding=8\">");
			page.append("<tr>");
			page.append("<td>User ID</td>");
			page.append("<td>User Login</td>");
			page.append("<td>User Role</td>");
			page.append("<td>Customer</td>");
			page.append("<td>Action</td>");
			page.append("</tr>");
			List<User> users = DaoFactory.getUserDao().getAllCustomers();
			UserRoleDao userRoleDao = DaoFactory.getUserRoleDao();
			CustomerDao customerDao = DaoFactory.getCustomerDao();
			for (User cust : users) {
				page.append("<tr>");
				page.append("<form action=\"editUser\"  align=\"center\" method=\"GET\">");
				page.append("<td name=\"user_id\">" + cust.getUserId() + "</td>");
				page.append("<input name=\"user_id\" type=\"hidden\" value=\"" + cust.getUserId() + "\">");
				page.append("<td>" + cust.getUserLogin() + "</td>");
				page.append("<td>" + userRoleDao.getUserRole(cust.getUserRoleId()).getUserRoleName() + "</td>");
				page.append("<td>" + customerDao.getCustomerByUserID(cust.getUserId()).toString() + "</td>");
				page.append("<td>");
				page.append("<input type=\"submit\" value=\"Edit\"/>");
				page.append("</td>");
				page.append("</form>");
				page.append("</tr>");

			}
			page.append("</table>");
			page.append("<div align=\"center\"><h1> WORKERS </h1>");
			page.append("</div>");
			page.append("<table align=\"center\" width=40% border=1 cellpadding=8\">");
			page.append("<tr>");
			page.append("<td>User ID</td>");
			page.append("<td>User Login</td>");
			page.append("<td>User Role</td>");
			page.append("<td>Worker</td>");
			page.append("<td>Specialization</td>");
			page.append("<td>Status</td>");
			page.append("<td>Action</td>");
			page.append("</tr>");
			users = DaoFactory.getUserDao().getAllWorkers();
			userRoleDao = DaoFactory.getUserRoleDao();
			WorkerDao workerDao = DaoFactory.getWorkerDao();
			WorkerSpecializationDao worker_specializationDao = DaoFactory.getWorkerSpecializationDao();
			WorkerStatusDao worker_statusDao = DaoFactory.getWorkerStatusDao();
			for (User work : users) {
				page.append("<tr>");
				page.append("<form action=\"editUser\" name=\"editUser\" align=\"center\" method=\"GET\">");
				page.append("<td name=\"user_id\">" + work.getUserId() + "</td>");
				page.append("<input name=\"user_id\" type=\"hidden\" value=\"" + work.getUserId() + "\">");
				page.append("<td>" + work.getUserLogin() + "</td>");
				page.append("<td>" + userRoleDao.getUserRole(work.getUserRoleId()).getUserRoleName() + "</td>");
				Worker worker = workerDao.getWorker(work.getUserId());
				page.append(
						"<td>" + worker.getLastName().toString() + " " + worker.getFirstName().toString() + "</td>");
				page.append("<td>" + worker_specializationDao.getSpecialization(worker.getSpecializationId())
						.getSpecialization_name() + "</td>");
				page.append("<td>" + worker_statusDao.getWorkerStatus(worker.getWorkerStatusId()).getWorkerStatusName()
						+ "</td>");
				page.append("<td>");
				page.append("<input type=\"submit\" value=\"Edit\"/>");
				page.append("</td>");
				page.append("</form>");
				page.append("</tr>");

			}
			page.append("</table>");
			page.append("<form align=\"center\" action=\"createNewOrder\" method=\"GET\">");
			page.append("<button name=\"newOrder\">Create new order</button>");
			page.append("</form>");
			page.append("<form align=\"center\" action=\"addNewCar\" method=\"GET\">");
			page.append("<button name=\"newCar\">Add new car</button>");
			page.append("</form>");
			page.append("<form align=\"center\" action=\"addNewCustomer\" method=\"GET\">");
			page.append("<button name=\"newCustomer\" >Add new customer</button>");
			page.append("</form>");
		} else if (user.getUserRoleId().equals(2))

		{
			CustomerDao customerDaoImpl = DaoFactory.getCustomerDao();
			CarDao carDaoImpl = DaoFactory.getCarDao();
			Customer customer = customerDaoImpl.getCustomerByUserID(user.getUserId());
			List<Car> cars = carDaoImpl.getCarsByCustomerName(customer.getLastName(), customer.getFirstName());
			OrderInWorkDao workDaoImpl = DaoFactory.getOrderInWorkDao();
			List<OrderInWork> work = new ArrayList<OrderInWork>();
			for (Car car : cars) {
				OrderInWork e = workDaoImpl.getOrderInWorkByCar(car.getVinNumber());
				if (e != null)
					work.add(e);
			}

			if (cars.size() > 0) {
				page.append("<div align=\"center\"><h3>Your cars:</h3></div>");
				page.append("<table align=\"center\" width=40% border=1 cellpadding=8\">");
				page.append("<th>Model</td>");
				page.append("<th>VIN number</td>");
				page.append("<tr>");
				page.append("</tr>");
				for (Car car : cars) {
					page.append("<tr>");
					page.append("<td>" + car.getCarModel() + "</td>");
					page.append("<td>" + car.getVinNumber() + "</td>");
					page.append("</tr>");
				}
				page.append("</table>");
			} else {
				page.append("<div align=\"center\"><h3>Now you have no car</h3></div>");
			}
			if (work.size() > 0) {
				page.append("<div align=\"center\"><h3>Your order:</h3></div>");
				page.append("<table align=\"center\" width=40% border=1 cellpadding=8\">");
				page.append("<tr>");
				page.append("<th>Order ID</td>");
				page.append("<th>Description</td>");
				page.append("<th>Start time</td>");
				page.append("<th>Finish time</td>");
				page.append("<th>Status</td>");
				page.append("<th>Car</td>");
				page.append("</tr>");
				OrderStatusDao statusDaoImpl = DaoFactory.getOrderStatusDao();
				for (OrderInWork order : work) {
					page.append("<tr>");
					page.append("<td>" + order.getOrderId() + "</td>");
					page.append("<td>" + order.getOrderDescription() + "</td>");
					page.append("<td>" + order.getDatetimeStart() + "</td>");
					page.append("<td>" + order.getDatetimeFinish() + "</td>");
					page.append(
							"<td>" + statusDaoImpl.getStatusByID(order.getOrderStatusId()).getStatusName() + "</td>");
					page.append("<td>" + carDaoImpl.getCarByID(order.getCarId()).getCarModel() + "</td>");
					page.append("</tr>");
				}
				page.append("</table>");
			} else
				page.append("<div align=\"center\"><h3>Now you have no active orders</h3></div>");

		}
		return page.toString();

	}
}
