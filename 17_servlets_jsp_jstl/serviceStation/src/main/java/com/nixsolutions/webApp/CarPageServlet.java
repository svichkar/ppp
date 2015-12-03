package com.nixsolutions.webApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.bean.CarFullInfo;
import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Car;
import com.nixsolutions.entity.Customer;

/**
 * Servlet implementation class CarPageServlet
 */
public class CarPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Car> carsList = null;
		carsList = DaoFactory.getCarDaoImpl().getAllCar();
		request.setAttribute("carList", getCarFullInfo(carsList));
		request.getRequestDispatcher("/WEB-INF/carPage.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private List<CarFullInfo> getCarFullInfo(List<Car> carsList) {
		List<CarFullInfo> list = new ArrayList<>();
		for (Car car : carsList) {
			CarFullInfo info = new CarFullInfo();
			info.setCar_ID(car.getCar_id().toString());
			info.setCarModel(car.getCar_model());
			info.setRegNumber(car.getReg_number());
			info.setVinNumber(car.getVin_number());
			Customer carOwner= DaoFactory.getCustomerDaoImpl().getCustomerByID(car.getCustomer_id());
			info.setCarOwner(carOwner.getLast_name()+" "+carOwner.getFirst_name());
			info.setOwnerPhone(carOwner.getPhone());
			list.add(info);
		}
		return list;
	}
}
