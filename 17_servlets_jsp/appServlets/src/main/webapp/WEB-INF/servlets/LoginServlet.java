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
		Role r = DaoFactory.getRole().getRoleById(user.getRoleId());
		String role = r.getRoleName();
		out.write(getPage(role, true));
	    } else {
		out.write(getPage(null, false));
	    }
	}
    }

    private String getPage(String role, Boolean loginSuccessfully) {
	StringBuilder builder = new StringBuilder();
	builder.append("<html>");
	builder.append("<head>");
	builder.append("<meta charset=\"utf-8\">");
	builder.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">");
	builder.append(loginSuccessfully ? "" : "<link rel=\"stylesheet\" href=\"style.css\">");
	builder.append("</head>");
	builder.append("<body>");
	if (loginSuccessfully) {
	    builder.append("<div align=\"right\"><form method = \"get\" action = \"logout_do\">"
		    + "<label>You are logged as " + role + "   </label>"
		    + "<input type=\"submit\" name=\"logout\" value=\"Logout\">"
		    + "</form></div>");
	    if (role.equals("admin")){
		builder.append("<div align=\"center\"><form>"
		    + "<button formmethod=\"get\" formaction=\"create_user\">Create user</button>"
		    + "<button formmethod=\"get\" formaction=\"view_all_users\">View all users</button>"
		    + "</form></div>");
	    }
	}
	
	if (!loginSuccessfully) {
	    builder.append("<section class=\"container\">" 
		    + "<div class=\"login\">" 
		    + "<h1>LOGIN</h1>"
		    + "<form method=\"post\" action=\"auth_check\">"
		    + "<p><input type=\"text\" name=\"email\" placeholder=\"Email\"></p>"
		    + "<p><input type=\"password\" name=\"password\" placeholder=\"Password\"></p>"
		    + "<p class=\"submit\">" 
		    + "<input type=\"submit\" name=\"login\" value=\"Login\"></p>"
		    + "<p style=\"color:red\">Email or password is not valid.</p>" 
		    + "</form>" 
		    + "</div>" 
		    + "</section>");
	}
	builder.append("</body>");
	builder.append("</html>");
	return builder.toString();
    }
}
