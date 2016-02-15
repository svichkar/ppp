package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.dao.OrderInWorkDAO;
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

}
