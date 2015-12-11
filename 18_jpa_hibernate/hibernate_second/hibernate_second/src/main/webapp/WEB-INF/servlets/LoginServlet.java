package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Role;;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	User user = DaoFactory.getUser().getByUserEmail(email);
	try (PrintWriter out = response.getWriter()) {
	    if (user != null && user.getPassword().equals(password)) {
		Role r = user.getRole();
		String role = r.getRoleName();
		request.setAttribute("loginfailed", "false");
		request.setAttribute("currentUser", role);
		request.getRequestDispatcher("/WEB-INF/jspPages/homePage.jsp").include(request, response);
	    } else {
		request.setAttribute("loginfailed", "true");
		request.getRequestDispatcher("/index.jsp").include(request, response);
	    }
	}
    }
}
