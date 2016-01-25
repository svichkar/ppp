package com.nixsolutions.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;

import com.nixsolutions.dao.impl.CarDAOImpl;
import com.nixsolutions.dao.impl.CustomerDAOImpl;
import com.nixsolutions.dao.impl.ServiceStationDAOFactoryImpl;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.Customer;

/**
 * Servlet implementation class Car
 */
public class CarSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceStationDAOFactoryImpl factory;
	private CarDAOImpl carImpl;
	private CustomerDAOImpl customerImpl;

	/**
	 * @throws Exception
	 * @see HttpServlet#HttpServlet()
	 */
	public CarSrvt() throws Exception {
		factory = new ServiceStationDAOFactoryImpl();
		carImpl = (CarDAOImpl) factory.getDao(Car.class);
		customerImpl = (CustomerDAOImpl) factory.getDao(Customer.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String car_id = request.getParameter("car_id") != null ? request.getParameter("car_id") : "";
		String action = request.getParameter("action") != null ? request.getParameter("action") : "";
		String model = request.getParameter("model") != null ? request.getParameter("model") : "";
		String vin = request.getParameter("vin") != null ? request.getParameter("vin") : "";
		String description = request.getParameter("description") != null ? request.getParameter("description") : "";
		String customer_id = request.getParameter("customer_id") != null ? request.getParameter("customer_id") : "";
		List<Customer> customers = customerImpl.getAll();
		/// parse customer id
		int customerId = NumberUtils.isDigits(customer_id) ? Integer.parseInt(customer_id) : 0;
		Customer customer = null;
		if (customerId > 0) {
			customer = customerImpl.findByPK(customerId);
		}
		/// parse car id
		int carId = NumberUtils.isDigits(car_id) ? Integer.parseInt(car_id) : 0;
		if (carId > 0) {
			Car car = carImpl.findByPK(carId);
			if (action.equalsIgnoreCase("Edit")) {
				request.setAttribute("car", car);
				request.setAttribute("customers", customers);
				request.setAttribute("title", "Edit car");
				request.getRequestDispatcher("/WEB-INF/jsp/car.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Delete")) {
				carImpl.delete(car);
				request.setAttribute("destination", "Cars");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				car.setModel(model);
				car.setVin(vin);
				car.setDescription(description);
				car.setCustomer(customer);
				carImpl.update(car);
				request.setAttribute("destination", "Cars");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		} else {
			if (action.equalsIgnoreCase("Add")) {
				request.setAttribute("customers", customers);
				request.setAttribute("title", "Add car");
				request.getRequestDispatcher("/WEB-INF/jsp/car.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("Save")) {
				Car car = new Car(model, vin, description, customer);
				carImpl.create(car);
				request.setAttribute("destination", "Cars");
				request.getRequestDispatcher("/navigation").forward(request, response);
			} else {
				request.setAttribute("destination", "Cars");
				request.getRequestDispatcher("/navigation").forward(request, response);
			}
		}
	}

}
