package com.nixsolutions.servlet.admin;

import java.io.IOException;

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
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

@WebServlet("/edirUser.do")
public class EditUserServlet extends HttpServlet {

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
	
	/*@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/AddNewUser.jsp");
		rs.include(request, response);
	}*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//PrintWriter out = response.getWriter();
		User user = userDao.getByUserName(request.getParameter("user"));		
		Role role = roleDao.getByRoleId(user.getRole().getRoleId());
		request.setAttribute("user", user);
		request.setAttribute("role", role);
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/jsp/EditUser.jsp");
		try {
			rs.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
		User user = userDao.getByUserName(request.getParameter("loginPrevious"));
		user.setUserName(request.getParameter("login"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setRole(roleDao.getByRoleName(request.getParameter("role")));		
		userDao.update(user);
		response.sendRedirect("login.do");
	}
}
