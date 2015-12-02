package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Role;
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
	User user = DaoFactory.getUser().getByUserId(Integer.parseInt(request.getParameter("delete")));
	if (DaoFactory.getRole().getRoleById(user.getRoleId()).getRoleName().equals("admin")) {
	    request.setAttribute("message", "You can not delete admin user");
	} else {
	    if (!DaoFactory.getUser().delete(user)) {
		request.setAttribute("message", "Error has occured. User was not deleted.");
	    }
	}
	List<User> userList = DaoFactory.getUser().getAll();
	List<Role> roleList = DaoFactory.getRole().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("users", userList);
	request.setAttribute("roles", roleList);
	request.getRequestDispatcher("/WEB-INF/jspPages/users/manageUsers.jsp").include(request, response);
    }
}
