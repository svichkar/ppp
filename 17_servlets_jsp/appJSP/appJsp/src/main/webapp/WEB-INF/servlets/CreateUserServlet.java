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

public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateUserServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<User> userList = DaoFactory.getUser().getAll();
	List<Role> roleList = DaoFactory.getRole().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("users", userList);
	request.setAttribute("roles", roleList);
	request.setAttribute("createOrEdit", "create");
	request.getRequestDispatcher("/WEB-INF/jspPages/users/createEditUsers.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	User u = new User(0, request.getParameter("email"), request.getParameter("password"),
		DaoFactory.getRole().getRoleByName(request.getParameter("role")).getRoleId());
	Boolean isUserCreated = DaoFactory.getUser().create(u);
	List<User> userList = DaoFactory.getUser().getAll();
	List<Role> roleList = DaoFactory.getRole().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("users", userList);
	request.setAttribute("roles", roleList);
	if (isUserCreated) {
	    request.getRequestDispatcher("/WEB-INF/jspPages/users/manageUsers.jsp").include(request, response);
	} else {
	    if (DaoFactory.getUser().getByUserEmail(request.getParameter("email")) != null) {
		request.setAttribute("message", "User with same email is already exist.");
		request.getRequestDispatcher("/WEB-INF/jspPages/createEditUsers.jsp").include(request, response);
	    } else {
		request.setAttribute("message", "Error has occured. User was not created.");
		request.getRequestDispatcher("/WEB-INF/jspPages/users/createEditUsers.jsp").include(request, response);
	    }
	}
    }
}
