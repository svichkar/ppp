package com.nixsolutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.hibernate.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDAO customerDao;

	public List<Customer> getAllCustomers() {
		return customerDao.getAll();
	}
	
	public Customer getCustomerById(int id) {
		return customerDao.getByPK(id);
	}
	
	public void addCustomer(Customer customer) {
		customerDao.createFrom(customer);
	}
	
	public void updateCustomer(Customer customer) {
		customerDao.update(customer);
	}
	
	public void deleteCustomer(Customer customer) {
		customerDao.delete(customer);
	}
}
