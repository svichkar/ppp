/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.app;

import java.util.Date;
import nix.jdbcworkshop.entities.Car;
import nix.jdbcworkshop.entities.CarOrder;
import nix.jdbcworkshop.entities.CarOrderStatus;
import nix.jdbcworkshop.entities.CarType;
import nix.jdbcworkshop.entities.Client;
import nix.jdbcworkshop.entities.Employee;
import nix.jdbcworkshop.entities.EmployeeCarOrder;
import nix.jdbcworkshop.entities.EmployeeCategory;
import nix.jdbcworkshop.utils.DaoFactoryH2;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
public class DaoDemo {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Client client1 = new Client(null, "Jonny", "Bravo");
        DaoFactoryH2.getClientDaoH2().create(client1);
        Client client2 = new Client(null, "Jimmy", "Terner");
        DaoFactoryH2.getClientDaoH2().create(client2);
        DaoFactoryH2.getCarTypeDaoH2().create(new CarType(null, "Tesla", "S"));
        DaoFactoryH2.getCarTypeDaoH2().create(new CarType(null, "Cooper", "Mini"));
        DaoFactoryH2.getCarTypeDaoH2().create(new CarType(null, "Tesla", "X"));
        DaoFactoryH2.getCarTypeDaoH2().create(new CarType(null, "Honda", "CR-V"));

        Car car = new Car(null, "SN-052352362623", new Long(3), client2.getClientId());
        DaoFactoryH2.getCarDaoH2().create(car);
        car.setClientId(client1.getClientId());
        DaoFactoryH2.getCarDaoH2().update(car);
        System.out.println("Car " + car.getSerialId() + " is owned by "
                + DaoFactoryH2.getClientDaoH2().findClientById(car.getClientId()).getLastName());

        EmployeeCategory general = new EmployeeCategory(null, "General");
        DaoFactoryH2.getEmployeeCategoryDaoH2().create(general);
        DaoFactoryH2.getEmployeeDaoH2()
                .create(new Employee(null, "John", "Doe", general.getEmployeeCategoryId()));
        DaoFactoryH2.getEmployeeDaoH2()
                .create(new Employee(null, "Joan", "Doe", general.getEmployeeCategoryId()));
        DaoFactoryH2.getEmployeeDaoH2()
                .create(new Employee(null, "Jimmy", "Doe", general.getEmployeeCategoryId()));
        Employee importantEmployee
                = new Employee(null, "Logan", "Doe", general.getEmployeeCategoryId());
        DaoFactoryH2.getEmployeeDaoH2()
                .create(importantEmployee);

        System.out.println("\nList of employees:");
        for (Employee worker : DaoFactoryH2.getEmployeeDaoH2().getEmployeeList()) {
            System.out.println(worker.getFirstName() + " " + worker.getLastName());
        }

        CarOrderStatus pending = new CarOrderStatus(null, "Pending");
        DaoFactoryH2.getCarOrderStatusDaoH2().create(pending);
        CarOrderStatus inProgress = new CarOrderStatus(null, "In Progress");
        DaoFactoryH2.getCarOrderStatusDaoH2().create(inProgress);

        CarOrder newOrder = new CarOrder(null, car.getCarId(), pending.getCarOrderStatusId(),
                new Date(), null);
        DaoFactoryH2.getCarOrderDaoH2().create(newOrder);
        DaoFactoryH2.getCarOrderDaoH2().getCarOrderList();
        Long employeeId = importantEmployee.getEmployeeId();
        Employee employee = DaoFactoryH2.getEmployeeDaoH2().findEmployeeById(employeeId);
        DaoFactoryH2.getEmployeeCarOrderDaoH2().create(
                new EmployeeCarOrder(employee.getEmployeeId(), newOrder.getCarOrderId()));

        System.out.println("\nList of assigned orders:");
        for (EmployeeCarOrder assignment : DaoFactoryH2.getEmployeeCarOrderDaoH2()
                .getEmployeeCarOrderList()) {
            Employee busyWorker
                    = DaoFactoryH2.getEmployeeDaoH2().findEmployeeById(assignment.getEmployeeId());
            System.out.println(busyWorker.getFirstName() + " " + busyWorker.getLastName()
                    + " -> assignment id:" + assignment.getCarOrderId());
        }

    }

}
