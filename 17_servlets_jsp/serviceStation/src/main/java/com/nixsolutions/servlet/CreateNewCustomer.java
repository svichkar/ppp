package com.nixsolutions.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.nixsolutions.entity.OrderInWork;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;

/**
 * Servlet implementation class CreateNewCustomer
 */
@WebServlet("/addNewCustomer")
public class CreateNewCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
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

			+ "h1, b, h3, td {	color: #fff;	text-shadow: 0 0 10px rgba(0, 0, 0, 0.3);	letter-spacing: 1px;	text-align: center;}"

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateNewCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.write("<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
				+ "<header><div align=\"center\"><h1> CREATE NEW ORDER </h1></div></header><body>" + createPage()
				+ "</body>" + "<footer>Created by Lelyakov M.A.</footer>" + "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String cellNumber = request.getParameter("cellNumber");
		String userLogin = request.getParameter("userLogin");
		String userPassword = request.getParameter("userPassword");
		
		UserDao userDao = DaoFactory.getUserDao();
		PrintWriter out = response.getWriter();
		userDao.createNewUser(userLogin, userPassword, 2);
		userDao.validate(userLogin, userPassword);
		User user = userDao.getUser();
		CustomerDao customerDao = DaoFactory.getCustomerDao();
		customerDao.createNewCustomer(lastName, firstName, cellNumber, user.getUserId());

		out.write(generateHtml(userDao.getUser()));

	}

	private String createPage() {
		StringBuilder page = new StringBuilder();
		page.append("<form align=\"center\" method=\"post\">");
		page.append("<b>Enter Last Name:</b>");
		page.append("<input type=\"text\" name=\"lastName\" placeholder=\"Please enter Last Name\" />");
		page.append("<br/>");
		page.append("<br/>");
		page.append("<b>Enter First Name:</b>");
		page.append("<input type=\"text\" name=\"firstName\" placeholder=\"Please enter First Name\" />");
		page.append("<br/>");
		page.append("<br/>");
		page.append("<b>Enter cell number:</b>");
		page.append("<input type=\"text\" name=\"cellNumber\" placeholder=\"Please enter cell number\" />");
		page.append("<br/>");
		page.append("<br/>");
		page.append("<b>Enter User Login:</b>");
		page.append("<input type=\"text\" name=\"userLogin\" placeholder=\"Please enter User Name\" />");
		page.append("<br/>");
		page.append("<br/>");
		page.append("<b>Enter User Password:</b>");
		page.append("<input type=\"password\" name=\"userPassword\" placeholder=\"Please enter User Password\" />");

		page.append("<br/>");
		page.append("<br/>");
		page.append("<br/>");

		page.append("<button class=\"btn\">Create new order</button>");
		page.append("</form>");
		return page.toString();
	}

	private String generateHtml(User user) {
		String html = "";
		html = "<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
				+ "<header><div align=\"center\"><h1> MANGER </h1></div></header><body>" + createPage(user) + "</body>"
				+ "<footer>Created by Lelyakov M.A.</footer>" + "</html>";
		return html;
	}

	private String createPage(User user) {
		StringBuilder page = new StringBuilder();
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
			page.append("<td>" + worker.getLastName().toString() + " " + worker.getFirstName().toString() + "</td>");
			page.append("<td>"
					+ worker_specializationDao.getSpecialization(worker.getSpecializationId()).getSpecialization_name()
					+ "</td>");
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
		return page.toString();

	}

}
