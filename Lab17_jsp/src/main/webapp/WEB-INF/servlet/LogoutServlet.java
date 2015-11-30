package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
		
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
		rs.include(request, response);
	}
}
