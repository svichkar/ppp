/**
 * 
 */
package com.nixsolutions.webApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.impl.UserDaoImpl;
import com.nixsolutions.entity.User;

/**
 * @author mixeyes
 *
 */
@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3337700405693703447L;
	private final static Logger logger = LogManager.getLogger();

	@Override
	public void init() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// BufferedReader reader = request.getReader();
		String user_id = request.getParameter("user_id");
		UserDaoImpl userDao = DaoFactory.getUserDaoImpl();
		try {
			User user = userDao.getUserByID(Integer.decode(user_id));
			PrintWriter out = response.getWriter();
			out.write("<html><head><title>Edit Worker</title></head><style>" + BODY_STYLE + "</style>"
					+ "<header><div align=\"center\"><h1> Edit " + user.getUser_login() + "</h1></div></header><body>"
					+ editUser(userDao) + "</body>" + "<footer>Created by Lelyakov M.A.</footer>" + "</html>");
		} catch (NullPointerException | NumberFormatException | SQLException e) {
			response.sendRedirect("");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// BufferedReader reader = request.getReader();
		String user_id = request.getParameter("user_id");
		String user_login = request.getParameter("user_login");
		String user_password = request.getParameter("user_password");
		String user_role_id = request.getParameter("user_role_id");
		UserDaoImpl userDao = DaoFactory.getUserDaoImpl();
		try {
			User user = userDao.getUserByID(Integer.decode(user_id));
			user.setUser_login(user_login);
			user.setUser_password(user_password);
			user.setUser_role_id(Integer.decode(user_role_id));
			userDao.updateUser(user);
			PrintWriter out = response.getWriter();
			out.write("<html><head><title>Edit Worker</title></head><style>" + BODY_STYLE + "</style>"
					+ "<header><div align=\"center\"><h1> " + user.getUser_login()
					+ " edited successfully</h1></div></header><body>" + "<form action=\"login\"  align=\"center\" method=\"POST\">"
					+ "<input name=\"login\" type=\"hidden\" value=\"" + user.getUser_login() + "\">"
					+ "<input name=\"password\" type=\"hidden\" value=\"" + user.getUser_password() + "\">" + "</form>"
					+ "</body>" + "<footer>Created by Lelyakov M.A.</footer>" + "</html>");
			response.sendRedirect("login");
		} catch (NullPointerException | NumberFormatException | SQLException e) {
			response.sendRedirect("");
		}
	}

	private String editUser(UserDaoImpl userDao) {
		StringBuilder builder = new StringBuilder();
		builder.append("<div align=\"center\">");
		builder.append("<form action=\"editUser\"  align=\"center\" method=\"POST\">");
		builder.append("<p>user_id</p>");
		builder.append(
				"<input name=\"user_id\" readonly=\"readonly\"  value=\"" + userDao.getUser().getUser_id() + "\">");
		builder.append("<p>user_login</p>");
		builder.append("<input name=\"user_login\" value=\"" + userDao.getUser().getUser_login() + "\">");
		builder.append("<p>user_password</p>");
		builder.append("<input name=\"user_password\" value=\"" + userDao.getUser().getUser_password() + "\">");
		builder.append("<p>user_role_id</p>");
		builder.append("<input name=\"user_role_id\" value=\"" + userDao.getUser().getUser_role_id() + "\">");
		builder.append("<p></p>");
		builder.append("<input type=\"submit\" value=\"Save\"/>");
		builder.append("</form>");
		builder.append("</div>");

		return builder.toString();
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
