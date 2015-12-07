package com.nixsolutions.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.dao.impl.H2DaoFactory;
import com.nixsolutions.entity.User;

@WebServlet("/addNewUser.do")
public class AddNewUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private RoleDao roleDao;
	
	@Override
	public void init() {
		DaoFactory daoFactory;
		try {
			daoFactory = new H2DaoFactory();

			roleDao = daoFactory.getRoleDao();
			userDao = daoFactory.getUserDao();
		} catch (ClassNotFoundException  e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/AddNewUser.jsp");
		rs.include(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		User user = new User();
		user.setUserName(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		user.setRole(roleDao.getByRoleName(request.getParameter("role")));
		user.setEmail(request.getParameter("email"));		
		if (userDao.getByUserName(user.getUserName()) == null){
			userDao.create(user);
			out.println("<font color=green>User created sucsecfull.</font>");
			response.sendRedirect("login.do");
			//RequestDispatcher rs = request.getRequestDispatcher("login.do");
			//rs.include(request, response);
		}else{
			out.println("<font color=red>This user already exist.</font>");
			response.sendRedirect("login.do");
			//RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/AdminHome.jsp");
			//rs.include(request, response);
		}
		out.close();
	}
}
