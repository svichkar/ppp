package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDaoImpl;

	@Override
	public List<Customer> getAllCustomers() {
		return customerDaoImpl.getAll();
	}

	@Override
	public Customer getCustomerById(long id) {
		return customerDaoImpl.findByPK(id);
	}

	@Override
	public Customer getCustomerByFullName(String f_name, String l_name) {
		return customerDaoImpl.findByFullName(f_name, l_name);
	}

	@Override
	public void addCustomer(Customer customer) {
		customerDaoImpl.create(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDaoImpl.update(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerDaoImpl.delete(customer);
	}

}
