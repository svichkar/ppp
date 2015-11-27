package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.CategoryDao;
import dao.UsersDao;
import dao.impl.DBDaoFactory;
import entity.Category;
import entity.Users;

@WebServlet(name = "addCategory", urlPatterns = "/addCategory")
public class AddNewCategory extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		DBDaoFactory factory = new DBDaoFactory();
		String category = request.getParameter("Category");
		if(category!=null&&category!=""){
			CategoryDao cf = factory.getCategoryDao();
			cf.addCategory(new Category(category));
			UsersDao users = factory.getUsersDao();
			List<Users> usersList = users.getAllUsers();
			List<Category> bookCategories = cf.getAllCategories();
			request.setAttribute("books", bookCategories);
			request.setAttribute("users",usersList);
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/usersList.jsp").forward(request, response);
		}
		
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
