package com.nixsolutions.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dao.WorkerSpecializationDAO;
import com.nixsolutions.dao.hibernate.DaoFactoryHibernate;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.entity.Worker;

@WebServlet("/workerPost.do")
public class WorkerPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static WorkerDAO workerDao;
	private static WorkerSpecializationDAO workerSpecDao;
	private static StatusDAO statusDao;

	@Override
	public void init() {
		DaoFactoryHibernate daoFactory = new DaoFactoryHibernate();
		userDao = daoFactory.getUserDAO();
		workerDao = daoFactory.getWorkerDAO();
		workerSpecDao = daoFactory.getWorkerSpecializationDAO();
		statusDao = daoFactory.getStatusDAO();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				String worker_id = request.getParameter("id");
				String first_name = request.getParameter("first_name");
				String last_name = request.getParameter("last_name");
				String status_id = request.getParameter("status_id");
				String specialization_id = request.getParameter("specialization_id");
				String user_id = request.getParameter("user_id");
				if (worker_id == null || worker_id.equals("")) {
					Worker worker = new Worker(first_name, last_name, workerSpecDao.getByPK(Integer.parseInt(specialization_id)), 
							statusDao.getByPK(Integer.parseInt(status_id)), userDao.getByPK(Integer.parseInt(user_id)));
					workerDao.createFrom(worker);
				} else {
					Worker worker = workerDao.getByPK(Integer.parseInt(worker_id));
					worker.setFirstName(first_name);
					worker.setLastName(last_name);
					worker.setWorkerSpecialization(workerSpecDao.getByPK(Integer.parseInt(specialization_id)));
					worker.setStatus(statusDao.getByPK(Integer.parseInt(status_id)));
					worker.setUser(userDao.getByPK(Integer.parseInt(user_id)));
					workerDao.update(worker);
				}
				request.setAttribute("target", "Workers");
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
