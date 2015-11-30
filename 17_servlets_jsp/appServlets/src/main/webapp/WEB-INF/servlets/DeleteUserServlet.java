package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.User;

public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteUserServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
	builder.append("<br><br><br><div align=\"center\">");
	User user = DaoFactory.getUser().getByUserId(Integer.parseInt(request.getParameter("delete")));
	if (DaoFactory.getRole().getRoleById(user.getRoleId()).getRoleName().equals("admin")) {
	    builder.append("<p style=\"color:red\">You can not delete admin user</p>");
	} else {
	    if (DaoFactory.getUser().delete(user)) {
		builder.append("<p style=\"color:green\">User is deleted.</p>");
	    } else {
		builder.append("<p style=\"color:red\">Error has occured. User was not deleted.</p>");
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
