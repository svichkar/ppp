package com.nixsolutions.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;

import com.nixsolutions.bean.CarBean;
import com.nixsolutions.bean.OrderBean;
import com.nixsolutions.bean.WorkerBean;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.hibernate.entity.OrderInWork;
import com.nixsolutions.hibernate.entity.Part;
import com.nixsolutions.hibernate.entity.Role;
import com.nixsolutions.hibernate.entity.User;
import com.nixsolutions.hibernate.entity.Worker;
import com.nixsolutions.hibernate.util.HibernateUtil;

@WebServlet("/nav.do")
public class NavigationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static SessionFactory sf;
	private Session session;

	@Override
	public void init() {
		sf = HibernateUtil.getSessionFactory();
		session = sf.getCurrentSession();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		Transaction tr = session.beginTransaction();
		if (login != null) {
			User user = (User) session.createCriteria(User.class).add(Restrictions.eq("userLogin", login)).list().get(0);
			Role role = (Role) session.byId(Role.class).load(user.getRole().getRoleId());
			//session.
			if (role.getRoleName().equals("Administrator")) {
				//List<OrderInWork> orderList = session.createCriteria(OrderInWork.class).list();
				List<OrderBean> obList = session.createSQLQuery(
						"SELECT oiw.order_id AS orderId, os.order_status_name AS orderStatus, " + 
						"oiw.description, c.vin AS carVin, c.model AS carModel, c.description AS carDescription, " +
						"oiw.timestamp_start AS timestampStart, oiw.timestamp_finish AS timestampFinish " +
						"FROM sqllab.order_in_work oiw " +
						"INNER JOIN sqllab.order_status os ON oiw.order_status_id = os.order_status_id " +
						"INNER JOIN sqllab.car c ON oiw.car_id = c.car_id ")
						.addScalar("orderId", LongType.INSTANCE)
						.addScalar("orderStatus", StringType.INSTANCE)
						.addScalar("description", StringType.INSTANCE)
						.addScalar("carVin", StringType.INSTANCE)
						.addScalar("carModel", StringType.INSTANCE)
						.addScalar("carDescription", StringType.INSTANCE)
						.addScalar("timestampStart", TimestampType.INSTANCE)
						.addScalar("timestampFinish", TimestampType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(OrderBean.class)).list();
				//request.setAttribute("orders", getOrdersAsBean(orderList));
				request.setAttribute("orders", obList);
				request.getRequestDispatcher("/WEB-INF/jsp/ordersPage.jsp").forward(request, response);
			} else if (role.getRoleName().equals("User")) {
				Customer customer = (Customer) session.createCriteria(Customer.class)
						.add(Restrictions.eq("user", user)).list().get(0);
/*				List<OrderInWork> orderList = session.createCriteria(OrderInWork.class)
						.add(Restrictions.eq("customer_id", customer.getCustomerId())).list();
				request.setAttribute("orders", getOrdersAsBean(orderList));*/
				List<OrderBean> obList = session.createSQLQuery(
						"SELECT oiw.order_id AS orderId, os.order_status_name AS orderStatus, " + 
						"oiw.description, c.vin AS carVin, c.model AS carModel, c.description AS carDescription, " +
						"oiw.timestamp_start AS timestampStart, oiw.timestamp_finish AS timestampFinish " +
						"FROM sqllab.order_in_work oiw " +
						"INNER JOIN sqllab.order_status os ON oiw.order_status_id = os.order_status_id " +
						"INNER JOIN sqllab.car c ON oiw.car_id = c.car_id " +
						"WHERE c.customer_id = " + customer.getCustomerId())
						.addScalar("orderId", LongType.INSTANCE)
						.addScalar("orderStatus", StringType.INSTANCE)
						.addScalar("description", StringType.INSTANCE)
						.addScalar("carVin", StringType.INSTANCE)
						.addScalar("carModel", StringType.INSTANCE)
						.addScalar("carDescription", StringType.INSTANCE)
						.addScalar("timestampStart", TimestampType.INSTANCE)
						.addScalar("timestampFinish", TimestampType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(OrderBean.class)).list();
				request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp").forward(request, response);
			} else {
				//
				response.sendRedirect("");
			}
		} else {
			//
			response.sendRedirect("");
		}
		//tr.rollback();
	}

	/*@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = (String) request.getSession().getAttribute("login");
		String target = (String) request.getParameter("target");
		target = target != null ? target : (String) request.getAttribute("target"); 
		if (login != null) {
			User user = (User) session.createCriteria(User.class).add(Restrictions.eq("user_name", login)).list().get(0);
			Role role = (Role) session.byId(Role.class).load(user.getRole().getRoleId());
			if (role.getRoleName().equals("Administrator")) {
				if (target == null || target.equals("Orders")) {
					List<OrderInWork> orderList = session.createCriteria(OrderInWork.class).list();
					request.setAttribute("orders", getOrdersAsBean(orderList));
					request.getRequestDispatcher("/WEB-INF/jsp/ordersPage.jsp").forward(request, response);
				} else if (target.equals("Cars")) {
					List<Car> carList = session.createCriteria(Car.class).list();
					request.setAttribute("cars", getCarsAsBean(carList));
					request.getRequestDispatcher("WEB-INF/jsp/carsPage.jsp").forward(request, response);
				} else if (target.equals("Customers")) {
					List<Customer> customerList = session.createCriteria(Customer.class).list();
					request.setAttribute("customers", customerList);
					request.getRequestDispatcher("WEB-INF/jsp/customersPage.jsp").forward(request, response);
				} else if (target.equals("Parts")) {
					List<Part> partList = session.createCriteria(Part.class).list();
					request.setAttribute("parts", partList);
					request.getRequestDispatcher("WEB-INF/jsp/partsPage.jsp").forward(request, response);
				} else if (target.equals("Workers")) {
					List<Worker> workerList = session.createCriteria(Worker.class).list();
					request.setAttribute("workers", getWorkersAsBean(workerList));
					request.getRequestDispatcher("WEB-INF/jsp/workersPage.jsp").forward(request, response);
				}
			} else {
				//
				response.sendRedirect("");
			}
		} else {
			//
			response.sendRedirect("");
		}
	}*/
}
