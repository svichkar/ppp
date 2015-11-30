package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.User;

public class ViewAllUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewAllUsersServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<User> userList = DaoFactory.getUser().getAll();
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
	builder.append("<div align=\"center\">" + "<table border=\"1\">" + "<caption>Users:</caption><tbody>"
		+ "<tr><th>ID</th><th>Email</th><th>Password</th><th>Role</th><th>Action</th></tr>");
	for (User u : userList) {
	    builder.append("<tr><td>" + u.getUserId() + "</td><td>" + u.getEmail() + "</td><td>" + u.getPassword()
		    + "</td><td>" + DaoFactory.getRole().getRoleById(u.getRoleId()).getRoleName() 
		    + "</td><td><form>"
		    + "<button formmethod=\"get\" formaction=\"edit_user\" name=\"editUser\" value=\"" + u.getUserId() 
		    + "\" style=\"width: 100%\">Edit</button>"
		    + "<br>"
		    + "<button formmethod=\"post\" formaction=\"delete_user\" name=\"delete\" value=\"" + u.getUserId() 
		    + "\" style=\"width: 100%\">Delete</button>"
		    + "</form></td></tr>");
	}
	builder.append("</tbody></table>" + "</div>");
	try (PrintWriter out = response.getWriter()) {
	    out.write(builder.toString());
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }
}
