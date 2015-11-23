/**
 * 
 */
package com.nixsolutions.webApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.impl.CarDaoImpl;
import com.nixsolutions.dao.impl.OrderInWorkDaoImpl;
import com.nixsolutions.entity.Car;

@WebServlet("/createNewOrder")
public class CreateNewOrderServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2737409962787439071L;
	private final static Logger logger = LogManager.getLogger();

	@Override
	public void init() {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vin = request.getParameter("vin_number");
		String issueDescription = request.getParameter("carDescription");
		PrintWriter out = response.getWriter();
		OrderInWorkDaoImpl orderDao = DaoFactory.getOrderInWorkDaoImpl();
		CarDaoImpl carDao = DaoFactory.getCarDaoImpl();
		try {
			orderDao.createNewOrder(carDao.getCarByVINNumber(vin).getCar_id(), issueDescription);
			out.write("<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
					+ "<header><div align=\"center\"><h1> Your order #"
					+ orderDao.getOrderInWorkByCar(vin).getOrder_id()
					+ " was added successfully</h1></div></header><body>" + createPage() + "</body>"
					+ "<footer>Created by Lelyakov M.A.</footer>" + "</html>");
		} catch (SQLException e) {
			logger.catching(e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			out.write("<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
					+ "<header><div align=\"center\"><h1> CREATE NEW ORDER </h1></div></header><body>" + createPage()
					+ "</body>" + "<footer>Created by Lelyakov M.A.</footer>" + "</html>");
		} catch (SQLException e) {
			logger.catching(e);
		}
	}

	private String createPage() throws SQLException {
		StringBuilder page = new StringBuilder();
		OrderInWorkDaoImpl order_in_workDaoImpl = DaoFactory.getOrderInWorkDaoImpl();
		page.append("<form align=\"center\" method=\"post\">");
		page.append("<b>Select car :</b>");
		CarDaoImpl carDao = DaoFactory.getCarDaoImpl();
		List<Car> cars = carDao.getAllCar();
		page.append("<select name=\"vin_number\">");
		page.append("<option disabled>Select car</option>");
		for (Car car : cars) {
			page.append("<option>" + car.getVin_number() + "</option>");
		}
		page.append("</select><Br/>");
		page.append("<b>Please enter car problem:</b>");
		page.append("<input type=\"text\" name=\"carDescription\" placeholder=\"Please enter car problem\" />");

		page.append("</form><Br/>");
		page.append("<form align=\"center\" method=\"get\">");
		page.append("<button class=\"btn\" name=\"newOrder\" action=\"createNewOrder\">Create new order</button>");
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
}
