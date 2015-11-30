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

public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditUserServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	User user = DaoFactory.getUser().getByUserId(Integer.parseInt(request.getParameter("editUser")));
	StringBuilder builder = new StringBuilder();
	builder.append("<html>");
	builder.append("<head>");
	builder.append("<meta charset=\"utf-8\">");
	builder.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">");
	builder.append("</head>");
	builder.append("<body>");
	builder.append("<div align=\"right\"><form method = \"get\" action = \"logout_do\">"
		+ "<label>You are logged as admin   </label>"
		+ "<input type=\"submit\" name=\"logout\" value=\"Logout\">" 
		+ "</form></div>");
	builder.append("<div align=\"center\"><form>"
		+ "<button formmethod=\"get\" formaction=\"create_user\">Create user</button>"
		+ "<button formmethod=\"get\" formaction=\"view_all_users\">View all users</button>" 
		+ "</form>"
		+ "</div>");
	builder.append("<br><br><br>" 
		+ "<div align=\"center\">" 
		+ "<form action=\"edit_user\" method=\"post\">"
		+ "<label>Email:</label><br>" 
		+ "<input type=\"hidden\" name = \"userId\" value=\"" + user.getUserId() + "\">"
		+ "<input type=\"text\" name = \"email\" value=\"" + user.getEmail() + "\" required><br>"
		+ "<label>Password:</label><br>" 
		+ "<input type=\"text\" name = \"password\" value=\"" + user.getPassword() + "\" required><br>"
		+ "<label>Role:</label><br>" 
		+ "<select name=\"role\">");
	List<Role> roleList = DaoFactory.getRole().getAll();
	for (Role r : roleList) {
	    builder.append("<option value=\"" + r.getRoleName() + "\" " + (r.getRoleId() == user.getRoleId() ? "selected" : "") + ">" + r.getRoleName() + "</option>");
	}
	builder.append("</select><br><br>"
		+ "<input type=\"submit\" name=\"saveChanges\" value = \"Save\">" + "</form>" + "</div>");
	builder.append("</body>");
	builder.append("</html>");
	try (PrintWriter out = response.getWriter()) {
	    out.write(builder.toString());
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	User user = DaoFactory.getUser().getByUserId(Integer.parseInt(request.getParameter("userId")));
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
	user.setEmail(request.getParameter("email"));
	user.setPassword(request.getParameter("password"));
	user.setRoleId(DaoFactory.getRole().getRoleByName(request.getParameter("role")).getRoleId());
	if (DaoFactory.getUser().update(user)) {
	    builder.append("<p style=\"color:green\">User was updated successfully.</p>");
	} else {
	    User u = DaoFactory.getUser().getByUserEmail(request.getParameter("email"));
	    if (u != null && u.getUserId() != user.getUserId()) {
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
