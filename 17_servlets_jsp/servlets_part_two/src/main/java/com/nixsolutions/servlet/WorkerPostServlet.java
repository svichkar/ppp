package com.nixsolutions.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.RoleDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.entity.Worker;

@WebServlet("/workerPost.do")
public class WorkerPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static RoleDAO roleDao;
	private static UserDAO userDao;
	private static WorkerDAO workerDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		roleDao = daoFactory.getRoleDAO();
		userDao = daoFactory.getUserDAO();
		workerDao = daoFactory.getWorkerDAO();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = roleDao.getByPK(user.getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				String worker_id = request.getParameter("id");
				String first_name = request.getParameter("first_name");
				String last_name = request.getParameter("last_name");
				String status_id = request.getParameter("status_id");
				String specialization_id = request.getParameter("specialization_id");
				String user_id = request.getParameter("user_id");
				if (worker_id == null || worker_id.equals("")) {
					Worker worker = new Worker(first_name, last_name, Integer.parseInt(specialization_id), 
							Integer.parseInt(status_id), Integer.parseInt(user_id));
					workerDao.createFrom(worker);
				} else {
					Worker worker = workerDao.getByPK(Integer.parseInt(worker_id));
					worker.setFirstName(first_name);
					worker.setLastName(last_name);
					worker.setSpecializationId(Integer.parseInt(specialization_id));
					worker.setStatusId(Integer.parseInt(status_id));
					worker.setUserId(Integer.parseInt(user_id));
					workerDao.update(worker);
				}
				request.setAttribute("target", "Cars");
				request.getRequestDispatcher("/nav.do").forward(request, response);
			} else {
				//
				response.sendRedirect("");
			}
		} else {
			//
			response.sendRedirect("");
		}
	}
}
