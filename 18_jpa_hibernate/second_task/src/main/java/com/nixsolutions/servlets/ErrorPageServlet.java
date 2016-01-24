package com.nixsolutions.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/errorPage")
public class ErrorPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
		String role = String.valueOf(request.getSession().getAttribute("role"));		
		if ("admin".equals(role))
			request.getRequestDispatcher("/WEB-INF/jsp/errorAdmin.jsp").forward(request, response);
		else
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	}

}
