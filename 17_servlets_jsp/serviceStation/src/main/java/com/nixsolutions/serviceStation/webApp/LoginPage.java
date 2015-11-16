package com.nixsolutions.serviceStation.webApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbc.JdbcBatchUpdateException;

import com.nixsolutions.serviceStation.CreateDB;
import com.nixsolutions.serviceStation.DropDB;
import com.nixsolutions.serviceStation.FillingDB;
import com.nixsolutions.serviceStation.dbObjects.Car;
import com.nixsolutions.serviceStation.dbObjects.Customer;
import com.nixsolutions.serviceStation.dbObjects.Order_in_work;
import com.nixsolutions.serviceStation.h2Objects.CarDaoImpl;
import com.nixsolutions.serviceStation.h2Objects.CustomerDaoImpl;
import com.nixsolutions.serviceStation.h2Objects.Order_in_workDaoImpl;
import com.nixsolutions.serviceStation.h2Objects.Order_statusDaoImpl;
import com.nixsolutions.serviceStation.h2Objects.ServiceFactory;
import com.nixsolutions.serviceStation.h2Objects.UserDaoImpl;

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
	private final static Logger logger = LogManager.getLogger();
	private ServiceFactory factory;

	@Override
	public void init() {
	/*	try {
			new CreateDB();
		} catch (ClassNotFoundException e) {
			
		} catch (SQLException e) {
			new DropDB();
		} finally {
			try {
				new CreateDB();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			new FillingDB();
		}*/
		factory = new ServiceFactory();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// BufferedReader reader = request.getReader();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		UserDaoImpl userDao = factory.getUserDaoImpl();
		try {
			if (userDao.validate(login, password)) {
				PrintWriter out = response.getWriter();
				Integer role_id = userDao.getUser().getUser_role_id();
				if (role_id.equals(1)) {
					out.write("<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
							+ "<header><div align=\"center\"><h1> MANGER </h1></div></header><body>"
							+ createPage(userDao) + "</body>" + "<footer>Created by Lelyakov M.A.</footer>"
							+ "</html>");
				} else if (role_id.equals(2)) {
					CustomerDaoImpl customerDaoImpl = factory.getCustomerDaoImpl();
					out.write("<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
							+ "<header><div align=\"center\"><h1>Hello "
							+ customerDaoImpl.getCustomerByUserID(userDao.getUser().getUser_id()).toString()
							+ "</h1></div></header><body>" + createPage(userDao) + "</body>"
							+ "<footer>Created by Lelyakov M.A.</footer>" + "</html>");

				}
			} else {
				response.sendRedirect("");
			}
		} catch (NullPointerException e) {
			response.sendRedirect("");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.write("<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
				+ "<header><div align=\"center\"><h1> CREATE NEW ORDER </h1></div></header><body>" + createPage() + "</body>"
				+ "<footer>Created by Lelyakov M.A.</footer>" + "</html>");
	}

	private String createPage(UserDaoImpl userDao) {
		StringBuilder page = new StringBuilder();
		if (userDao.getUser().getUser_role_id().equals(1)) {
			page.append("<table align=\"center\" width=40% border=1 cellpadding=8\">");
			page.append("<tr>");
			page.append("<td>Order ID</td>");
			page.append("<td>Description</td>");
			page.append("<td>Start time</td>");
			page.append("<td>Finish time</td>");
			page.append("<td>Status</td>");
			page.append("<td>Car</td>");
			page.append("</tr>");
			Order_in_workDaoImpl workDaoImpl = factory.getOrder_in_workDaoImpl();
			Order_statusDaoImpl statusDaoImpl = factory.getOrder_statusDaoImpl();
			CarDaoImpl carDaoImpl = factory.getCarDaoImpl();
			for (Order_in_work work : workDaoImpl.getAllOrders()) {
				page.append("<tr>");
				page.append("<td>" + work.getOrder_id() + "</td>");
				page.append("<td>" + work.getOrder_description() + "</td>");
				page.append("<td>" + work.getDatetime_start() + "</td>");
				page.append("<td>" + work.getDatetime_finish() + "</td>");
				page.append("<td>" + statusDaoImpl.getStatusByID(work.getOrder_status_id()).getStatus_name() + "</td>");
				page.append("<td>" + carDaoImpl.getCarByID(work.getCar_id()).toString() + "</td>");
				page.append("</tr>");

			}
			page.append("</table>");
			page.append("<form align=\"center\" method=\"GET\">");
			page.append("<button name=\"newOrder\" action=\"createNewOrder\">Create new order</button>");
			page.append("<button name=\"newCar\" action=\"addNewCar\">Add new car</button>");
			page.append("<button name=\"newCustomer\" action=\"addNewCustomer\">Add new customer</button>");
			page.append("</form>");

		} else if (userDao.getUser().getUser_role_id().equals(2)) {
			CustomerDaoImpl customerDaoImpl = factory.getCustomerDaoImpl();
			CarDaoImpl carDaoImpl = factory.getCarDaoImpl();
			Customer customer = customerDaoImpl.getCustomerByUserID(userDao.getUser().getUser_id());
			java.util.List<Car> cars = carDaoImpl.getCarsByCustomerName(customer.getLast_name(),
					customer.getFirst_name());
			Order_in_workDaoImpl workDaoImpl = factory.getOrder_in_workDaoImpl();
			java.util.List<Order_in_work> work = new ArrayList();
			for (Car car : cars) {
				Order_in_work e = workDaoImpl.getOrderInWorkByCar(car.getVin_number());
				if (e != null)
					work.add(e);
			}

			if (cars != null) {
				page.append("<div align=\"center\"><h3>Your cars:</h3></div>");
				page.append("<table align=\"center\" width=40% border=1 cellpadding=8\">");
				page.append("<th>Model</td>");
				page.append("<th>VIN number</td>");
				page.append("<tr>");
				page.append("</tr>");
				for (Car car : cars) {
					page.append("<tr>");
					page.append("<td>" + car.getModel() + "</td>");
					page.append("<td>" + car.getVin_number() + "</td>");
					page.append("</tr>");
				}
				page.append("</table>");
			} else {
				page.append("<div align=\"center\"><h3>Now you have no car</h3></div>");
			}
			if (work != null) {
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
				Order_statusDaoImpl statusDaoImpl = factory.getOrder_statusDaoImpl();
				for (Order_in_work order : work) {
					page.append("<tr>");
					page.append("<td>" + order.getOrder_id() + "</td>");
					page.append("<td>" + order.getOrder_description() + "</td>");
					page.append("<td>" + order.getDatetime_start() + "</td>");
					page.append("<td>" + order.getDatetime_finish() + "</td>");
					page.append("<td>" + statusDaoImpl.getStatusByID(order.getOrder_status_id()).getStatus_name()
							+ "</td>");
					page.append("<td>" + carDaoImpl.getCarByID(order.getCar_id()).getModel() + "</td>");
					page.append("</tr>");
				}
				page.append("</table>");
			} else
				page.append("<div align=\"center\"><h3>Now you have no active orders</h3></div>");

		}
		return page.toString();

	}
	
	private String createPage() {
		StringBuilder page = new StringBuilder();
		Order_in_workDaoImpl order_in_workDaoImpl = factory.getOrder_in_workDaoImpl();
		page.append("<form action=\"createNewOrder\" align=\"center\" method=\"POST\">");
		page.append("<b>Select car :</b>");
		CarDaoImpl carDao = factory.getCarDaoImpl();
		java.util.List<Car> cars = carDao.getAllCar();
		page.append("<select name=\"vin_number\">");
		page.append("<option disabled>Select car</option>");
		for (Car car : cars) {
			page.append("<option>" + car.getVin_number() + "</option>");
		}
		page.append("</select><Br/>");
		page.append("<b>Please enter car problem:</b>");
		page.append("<input type=\"text\" name=\"carDescription\" placeholder=\"Please enter car problem\" />");

		page.append("<Br/>");
		page.append("<button class=\"btn\" name=\"newOrder\" >Create new order</button>");
		page.append("</form>");
		return page.toString();
	}
	
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
}
