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

public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditUserServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<User> userList = DaoFactory.getUser().getAll();
	List<Role> roleList = DaoFactory.getRole().getAll();
	User user = DaoFactory.getUser().getByUserId(Integer.parseInt(request.getParameter("editUser")));
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("users", userList);
	request.setAttribute("roles", roleList);
	request.setAttribute("createOrEdit", "edit");
	request.setAttribute("userToEdit", user);
	request.getRequestDispatcher("/WEB-INF/jspPages/users/createEditUsers.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	User user = DaoFactory.getUser().getByUserId(Integer.parseInt(request.getParameter("userId")));
	user.setEmail(request.getParameter("email"));
	user.setPassword(request.getParameter("password"));
	user.setRole(DaoFactory.getRole().getRoleByName(request.getParameter("role")));
	Boolean isUpdated = DaoFactory.getUser().update(user);
	List<User> userList = DaoFactory.getUser().getAll();
	List<Role> roleList = DaoFactory.getRole().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("users", userList);
	request.setAttribute("roles", roleList);
	if (isUpdated) {
	    request.getRequestDispatcher("/WEB-INF/jspPages/users/manageUsers.jsp").include(request, response);
	} else {
	    request.setAttribute("createOrEdit", "edit");
	    request.setAttribute("userToEdit", user);
	    User u = DaoFactory.getUser().getByUserEmail(request.getParameter("email"));
	    if (u != null && u.getUserId() != user.getUserId()) {
		request.setAttribute("message", "User with same email is already exist.");
	    } else {
		request.setAttribute("message", "Error has occured. User was not created.");
	    }
	    request.getRequestDispatcher("/WEB-INF/jspPages/users/createEditUsers.jsp").include(request, response);
	}
    }
}
