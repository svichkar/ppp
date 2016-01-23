
package com.nixsolutions.servlets;

import com.nixsolutions.servicestation.dao.FactoryDAO;
import com.nixsolutions.servicestation.dao.impl.FactoryDAOImpl;
import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.entity.CarType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;


/**
 * Created by rybkinrolla on 15.01.2016.
 */

@WebServlet("/cars")
public class ServletPageCars extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        req.setAttribute("carsBeanList", factoryDAO.getCarDAO().getUserCars());
        req.setAttribute("clientsList", factoryDAO.getClientDAO().findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/cars.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        Car car = new Car();
        if ((req.getParameter("add") != null) || (req.getParameter("edit") != null)) {
            Set<CarType> carTypeList = factoryDAO.getCarTypeDAO().findAll();
            CarType carType = new CarType();
            carType.setBrand(req.getParameter("brand"));
            carType.setModelName(req.getParameter("model_name"));
            if (!carTypeList.contains(carType)) {
                factoryDAO.getCarTypeDAO().create(carType);
                carTypeList = factoryDAO.getCarTypeDAO().findAll();
            }
            for (CarType ct : carTypeList) {
                if (ct.getBrand().equals(req.getParameter("brand")) && ct.getModelName().equals(req.getParameter("model_name"))) {
                    car.getCarType().setCarTypeId(ct.getCarTypeId());
                }
            }
            car.setSerialVIN(req.getParameter("VIN"));
            if (req.getParameter("add") != null) {
                car.getClient().setClientId(Long.valueOf(req.getParameter("clients")));
                factoryDAO.getCarDAO().create(car);
                resp.sendRedirect("cars?message=Row was created");
            }
            if (req.getParameter("edit") != null) {
                car.getClient().setClientId(Long.valueOf(req.getParameter("client_id")));
                car.setCarId(Long.valueOf(req.getParameter("car_id")));
                factoryDAO.getCarDAO().update(car);
                resp.sendRedirect("cars?message=Row was updated");
            }
        }
        if(req.getParameter("delete") != null){
            car.setCarId(Long.valueOf(req.getParameter("car_id")));
            factoryDAO.getCarDAO().delete(car);
            resp.sendRedirect("cars?message=Row was deleted");
        }
    }
}

