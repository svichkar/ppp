package com.nixsolutions.servicestation.app;

import com.nixsolutions.servicestation.dao.*;
import com.nixsolutions.servicestation.dao.impl.ImplCarTypeDAO;
import com.nixsolutions.servicestation.dao.impl.ImplFactoryDAO;
import com.nixsolutions.servicestation.entity.*;

import java.util.Date;
import java.util.List;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class JDBCDemo {
    public static void main(String[] args) {
        FactoryDAO factoryDAO = new ImplFactoryDAO();
        CarTypeDAO carTypeDAO = factoryDAO.getCarTypeDAO();
        CarType carType = new CarType("Honda","Accord");
        carTypeDAO.create(carType);
        List<CarType> carTypes = carTypeDAO.findAll();
        carType.setCarTypeId(carTypes.get(0).getCarTypeId());
        carType.setModelName("Civic");
        carTypeDAO.update(carType);

        ClientDAO clientDAO = factoryDAO.getClientDAO();
        Client client = new Client("Homer","Simpson");
        clientDAO.create(client);
        List<Client> clients = clientDAO.findAll();
        client.setClientId(clients.get(0).getClientId());

        EmployeeCategoryDAO employeeCategoryDAO = factoryDAO.getEmployeeCategoryDAO();
        EmployeeCategory employeeCategory = new EmployeeCategory("tuning");
        employeeCategoryDAO.create(employeeCategory);
        List<EmployeeCategory> employeeCategories = employeeCategoryDAO.findAll();
        employeeCategory.setEmployeeCategoryId(employeeCategories.get(0).getEmployeeCategoryId());

        CarOrderStatusDAO carOrderStatusDAO = factoryDAO.getCarOrderStatusDAO();
        CarOrderStatus carOrderStatus = new CarOrderStatus("Complete");
        carOrderStatusDAO.create(carOrderStatus);
        List<CarOrderStatus> carOrderStatuses = carOrderStatusDAO.findAll();
        carOrderStatus.setCarOrderStatusId(carOrderStatuses.get(0).getCarOrderStatusId());

        CarDAO carDAO = factoryDAO.getCarDAO();
        Car car = new Car(100500,"zzzxxx", carTypes.get(0).getCarTypeId(), clients.get(0).getClientId());
        carDAO.create(car);
        car.setSerialId("ppppaaaaa");
        carDAO.update(car);


        EmployeeDAO employeeDAO = factoryDAO.getEmployeeDAO();
        Employee employee = new Employee("John","Johnson", employeeCategory.getEmployeeCategoryId());
        employeeDAO.create(employee);
        List<Employee> employees = employeeDAO.findAll();
        employee.setEmployeeId(employees.get(0).getEmployeeId());
        employee.setFirstName("Jack");
        employeeDAO.update(employee);

        CarOrderDAO carOrderDAO = factoryDAO.getCarOrderDAO();
        CarOrder carOrder = new CarOrder(car.getCarId(), carOrderStatus.getCarOrderStatusId(), new Date(1452002949000L));
        carOrderDAO.create(carOrder);
        List<CarOrder> carOrders = carOrderDAO.findAll();
        carOrder.setCarOrderId(carOrders.get(0).getCarOrderId());
        carOrder.setStartDate(new Date(1451002949000L));
        carOrder.setEndDate(new Date(1452002949000L));
        carOrderDAO.update(carOrder);

        EmployeeCarOrderDAO employeeCarOrderDAO = factoryDAO.getEmployeeCarOrderDAO();
        EmployeeCarOrder employeeCarOrder = new EmployeeCarOrder(500, employee.getEmployeeId(),carOrder.getCarOrderId());
        employeeCarOrderDAO.create(employeeCarOrder);

    }
}
