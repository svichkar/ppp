package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.hibernate.entity.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();

	Customer getCustomerById(int id);

	void addCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(Customer customer);
}
