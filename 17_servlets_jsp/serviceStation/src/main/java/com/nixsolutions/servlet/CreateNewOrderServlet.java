/**
 * 
 */
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
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.OrderInWorkDao;
import com.nixsolutions.entity.Car;

@WebServlet("/createNewOrder")
public class CreateNewOrderServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2737409962787439071L;
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

	@Override
	public void init() {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vin = request.getParameter("vinNumber");
		String issueDescription = request.getParameter("carDescription");
		PrintWriter out = response.getWriter();
		OrderInWorkDao orderDao = DaoFactory.getOrderInWorkDao();
		CarDao carDao = DaoFactory.getCarDao();
		orderDao.createNewOrder(carDao.getCarByVINNumber(vin).getCarId(), issueDescription);
		out.write("<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
				+ "<header><div align=\"center\"><h1> Your order #" + orderDao.getOrderInWorkByCar(vin).getOrderId()
				+ " was added successfully</h1></div></header><body>" + createPage() + "</body>"
				+ "<footer>Created by Lelyakov M.A.</footer>" + "</html>");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.write("<html><head><title>Admin Page</title></head><style>" + BODY_STYLE + "</style>"
				+ "<header><div align=\"center\"><h1> CREATE NEW ORDER </h1></div></header><body>" + createPage()
				+ "</body>" + "<footer>Created by Lelyakov M.A.</footer>" + "</html>");
	}

	private String createPage() {
		StringBuilder page = new StringBuilder();
		page.append("<form align=\"center\" method=\"post\">");
		page.append("<b>Select car :</b>");
		CarDao carDao = DaoFactory.getCarDao();
		List<Car> cars = carDao.getAllCar();
		page.append("<select name=\"vinNumber\">");
		page.append("<option disabled>Select car</option>");
		for (Car car : cars) {
			page.append("<option value = \""+car.getVinNumber()+"\">" + car.getVinNumber() + "</option>");
		}
		page.append("</select><Br/>");
		page.append("<b>Please enter car problem:</b>");
		page.append("<input type=\"text\" name=\"carDescription\" placeholder=\"Please enter car problem\" />");

		/*page.append("</form><Br/>");
		page.append("<form align=\"center\" method=\"post\">");*/
		page.append("<br/>");
		page.append("<button class=\"btn\" name=\"newOrder\" action=\"createNewOrder\">Create new order</button>");
		page.append("</form>");
		return page.toString();
	}
}
