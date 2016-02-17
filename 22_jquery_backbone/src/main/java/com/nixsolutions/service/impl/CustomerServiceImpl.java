package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.dao.OrderInWorkDAO;
import com.nixsolutions.dto.CarCustomer;
import com.nixsolutions.dto.CustomerDescription;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.OrderInWork;
import com.nixsolutions.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDaoImpl;

	@Autowired
	private CarDAO carDaoImpl;

	@Autowired
	private OrderInWorkDAO orderInWorkImpl;

	public List<Customer> getAllCustomers() {
		return customerDaoImpl.getAll();
	}

	public Customer getCustomerById(long id) {
		return customerDaoImpl.findByPK(id);
	}

	public Customer getCustomerByFullName(String f_name, String l_name) {
		return customerDaoImpl.findByFullName(f_name, l_name);
	}

	public void addCustomer(Customer customer) {
		customerDaoImpl.create(customer);
	}

	public void updateCustomer(Customer customer) {
		customerDaoImpl.update(customer);
	}

	public void deleteCustomer(Customer customer) {
		customerDaoImpl.delete(customer);
	}

	public List<CustomerDescription> getAllCustomerDescriptions() {
		List<CustomerDescription> customerDescriptions = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		customers.addAll(customerDaoImpl.getAll());
		for (Customer customer : customers) {
			CustomerDescription customerDescription = new CustomerDescription();
			customerDescription.setCustomerId(customer.getCustomerId());
			customerDescription.setFname(customer.getFname());
			customerDescription.setLname(customer.getLname());
			customerDescription.setCustomerId(customer.getCustomerId());
			customerDescriptions.add(customerDescription);
		}
		return customerDescriptions;
	}

	public CustomerDescription getCustomerDescription(long customerId) {
		Customer customer = customerDaoImpl.findByPK(customerId);
		CustomerDescription customerDescription = new CustomerDescription();
		customerDescription.setCustomerId(customer.getCustomerId());
		customerDescription.setFname(customer.getFname());
		customerDescription.setLname(customer.getLname());
		customerDescription.setCustomerId(customer.getCustomerId());
		return customerDescription;
	}

}
