package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;

public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateUserServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	StringBuilder builder = new StringBuilder();
	builder.append("<html>");
	builder.append("<head>");
	builder.append("<meta charset=\"utf-8\">");
	builder.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">");
	builder.append("</head>");
	builder.append("<body>");
	builder.append("<div align=\"right\"><form method = \"get\" action = \"logout_do\">"
		+ "<label>You are logged as admin   </label>"
		+ "<input type=\"submit\" name=\"logout\" value=\"Logout\">" + "</form></div>");
	builder.append("<div align=\"center\"><form>"
		+ "<button formmethod=\"get\" formaction=\"create_user\">Create user</button>"
		+ "<button formmethod=\"get\" formaction=\"view_all_users\">View all users</button>" + "</form>"
		+ "</div>");
	builder.append("<br><br><br>" + "<div align=\"center\">" + "<form action=\"create_user\" method=\"post\">"
		+ "<label>Email:</label><br>" + "<input type=\"text\" name = \"email\" required><br>"
		+ "<label>Password:</label><br>" + "<input type=\"text\" name = \"password\" required><br>"
		+ "<label>Role:</label><br>" + "<select name=\"role\">");
	List<Role> roleList = DaoFactory.getRole().getAll();
	for (Role r : roleList) {
	    builder.append("<option value=\"" + r.getRoleName() + "\">" + r.getRoleName() + "</option>");
	}
	builder.append("</select><br><br>"
		+ "<input type=\"submit\" name=\"createNewUser\" value = \"Create new user\">" + "</form>" + "</div>");
	builder.append("</body>");
	builder.append("</html>");
	try (PrintWriter out = response.getWriter()) {
	    out.write(builder.toString());
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	DaoFactory.getUser().getByUserEmail(request.getParameter("email"));
	User u = new User(0, request.getParameter("email"),
		request.getParameter("password"),
		DaoFactory.getRole().getRoleByName(request.getParameter("role")).getRoleId());
	Boolean isUserCreated = DaoFactory.getUser().create(u);
	StringBuilder builder = new StringBuilder();
	builder.append("<html>");
	builder.append("<head>");
	builder.append("<meta charset=\"utf-8\">");
	builder.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">");
	builder.append("</head>");
	builder.append("<body>");
	builder.append("<div align=\"right\"><form method = \"get\" action = \"logout_do\">"
		+ "<label>You are logged as admin   </label>"
		+ "<input type=\"submit\" name=\"logout\" value=\"Logout\">" + "</form></div>");
	builder.append("<div align=\"center\"><form>"
		+ "<button formmethod=\"get\" formaction=\"create_user\">Create user</button>"
		+ "<button formmethod=\"get\" formaction=\"view_all_users\">View all users</button>" + "</form>"
		+ "</div>");
	builder.append("<br><br><br><div align=\"center\">");
	if (isUserCreated) {
	    builder.append("<p style=\"color:green\">New user was created successfully.</p>");
	} else {
	    if (DaoFactory.getUser().getByUserEmail(request.getParameter("email")) != null) {
		builder.append("<p style=\"color:red\">User with same email is already exist.</p>");
	    } else {
		builder.append("<p style=\"color:red\">Error has occured. User was not created.</p>");
	    }
	}
	builder.append("</div>");
	builder.append("</body>");
	builder.append("</html>");
	try (PrintWriter out = response.getWriter()) {
	    out.write(builder.toString());
	}
    }
}
