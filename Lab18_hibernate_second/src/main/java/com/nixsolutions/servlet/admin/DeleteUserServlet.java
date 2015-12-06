package com.nixsolutions.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/delete.do")
public class DeleteUserServlet extends HttpServlet {

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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		User user = userDao.getByUserName(request.getParameter("user"));
		String roleName = roleDao.getByRoleId(user.getRole().getRoleId()).getRoleName();
		if (roleName.toLowerCase().equals("admin")){
			out.println("<font color=red>You can't delete admin user.</font>");
		}else{
			userDao.delete(user);
			out.println("<font color=green>User deleted sucsecfull.</font>");
			response.sendRedirect("login.do");
		}
		out.close();
	}
}
