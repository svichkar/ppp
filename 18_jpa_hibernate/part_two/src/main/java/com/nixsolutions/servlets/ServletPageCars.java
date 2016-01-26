
package com.nixsolutions.servlets;

import com.nixsolutions.servicestation.dao.FactoryDAO;
import com.nixsolutions.servicestation.dao.impl.FactoryDAOImpl;
import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.entity.CarType;
import com.nixsolutions.servicestation.entity.Client;

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
        req.setAttribute("carsSet", factoryDAO.getCarDAO().getUserCars());
        req.setAttribute("clientsSet", factoryDAO.getClientDAO().findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/cars.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FactoryDAO factoryDAO = new FactoryDAOImpl();
        Car car = new Car();
        Client client = new Client();
        car.setSerialVIN(req.getParameter("VIN"));
        if ((req.getParameter("add") != null) || (req.getParameter("edit") != null)) {
            Set<CarType> carTypeSet = factoryDAO.getCarTypeDAO().findAll();
            CarType carType = new CarType();
            carType.setBrand(req.getParameter("brand"));
            carType.setModelName(req.getParameter("model_name"));
            if (!carTypeSet.contains(carType)) {
                factoryDAO.getCarTypeDAO().create(carType);
                carTypeSet = factoryDAO.getCarTypeDAO().findAll();
            }
            for (CarType ct : carTypeSet) {
                if (ct.getBrand().equals(req.getParameter("brand")) && ct.getModelName().equals(req.getParameter("model_name"))) {
                    carType.setCarTypeId(ct.getCarTypeId());
                    car.setCarType(carType);
                }
            }
            if (req.getParameter("add") != null) {
                client.setClientId(Long.valueOf(req.getParameter("clients")));
                car.setClient(client);
                factoryDAO.getCarDAO().create(car);
                resp.sendRedirect("cars?message=Row%20was%20created");
            }
            if (req.getParameter("edit") != null) {
                client.setClientId(Long.valueOf(req.getParameter("client_id")));
                car.setClient(client);
                car.setCarId(Long.valueOf(req.getParameter("car_id")));
                factoryDAO.getCarDAO().update(car);
                resp.sendRedirect("cars?message=Row%20was%20updated");
            }
        }
        if (req.getParameter("delete") != null) {
            for (CarOrder co: factoryDAO.getCarOrderDAO().findAll()) {
                if(co.getCar().getCarId().equals(req.getParameter("car_id"))){
                    factoryDAO.getCarOrderDAO().delete(co);
                }
            }
            car.setCarId(Long.valueOf(req.getParameter("car_id")));
            factoryDAO.getCarDAO().delete(car);
            resp.sendRedirect("cars?message=Row%20was%20deleted");
        }
    }
}

