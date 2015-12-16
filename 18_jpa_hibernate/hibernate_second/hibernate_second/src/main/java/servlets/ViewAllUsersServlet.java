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

public class ViewAllUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewAllUsersServlet() {
	super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	List<User> userList = DaoFactory.getUser().getAll();
	List<Role> roleList = DaoFactory.getRole().getAll();
	request.setAttribute("currentUser", request.getParameter("currentUser"));
	request.setAttribute("users", userList);
	request.setAttribute("roles", roleList);
	request.getRequestDispatcher("/WEB-INF/jspPages/users/manageUsers.jsp").include(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }
}
