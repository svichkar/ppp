package com.nixsolutions.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.dao.OrderWorkerDAO;
import com.nixsolutions.dao.StatusDAO;
import com.nixsolutions.dao.UserDAO;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.dao.WorkerSpecializationDAO;
import com.nixsolutions.dao.impl.DAOFactoryImpl;
import com.nixsolutions.hibernate.entity.OrderWorker;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.entity.Worker;

@WebServlet(urlPatterns = { "/addWorker.do", "/editWorker.do", "/deleteWorker.do" })
public class WorkerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static OrderWorkerDAO orderWorkerDao;
	private static WorkerDAO workerDao;
	private static StatusDAO statusDao;
	private static WorkerSpecializationDAO workerSpecDao;

	@Override
	public void init() {
		DAOFactoryImpl daoFactory = new DAOFactoryImpl();
		userDao = daoFactory.getUserDAO();
		orderWorkerDao = daoFactory.getOrderWorkerDAO();
		workerDao = daoFactory.getWorkerDAO();
		statusDao = daoFactory.getStatusDAO();
		workerSpecDao = daoFactory.getWorkerSpecializationDAO();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				request.setAttribute("action", "add");
				request.setAttribute("statuses", statusDao.getAll());
				request.setAttribute("specs", workerSpecDao.getAll());
				request.setAttribute("users", userDao.getWorkers());
				request.getRequestDispatcher("/WEB-INF/jsp/worker.jsp").forward(request, response);
			} else {
				//
				response.sendRedirect("");
			}
		} else {
			//
			response.sendRedirect("");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		String action = (String) request.getParameter("action");
		String worker_id = (String) request.getParameter("worker_id");
		if (login != null) {
			User user = userDao.getUserByLogin(login);
			Role role = user.getRole();
			if (role.getRoleName().equals("Administrator")) {
				if (action.equals("Edit")) {
					Worker worker = workerDao.getByPK(Integer.parseInt(worker_id));
					request.setAttribute("worker", worker);
					request.setAttribute("action", "edit");
					request.setAttribute("statuses", statusDao.getAll());
					request.setAttribute("specs", workerSpecDao.getAll());
					request.setAttribute("users", userDao.getWorkers());
					request.getRequestDispatcher("/WEB-INF/jsp/worker.jsp").forward(request, response);
				} else {
					Worker worker = workerDao.getByPK(Integer.parseInt(worker_id));
					List<OrderWorker> orderWorkerList = orderWorkerDao.getOrderWorkerByWorker(worker);
					for (OrderWorker orderWorker : orderWorkerList) {
						orderWorkerDao.delete(orderWorker);
					}
					workerDao.delete(worker);
					User workerUser = worker.getUser();
					userDao.delete(workerUser);
					request.setAttribute("target", "Workers");
					request.getRequestDispatcher("/nav.do").forward(request, response);
				}
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
