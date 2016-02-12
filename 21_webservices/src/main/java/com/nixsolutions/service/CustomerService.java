package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();

	Customer getCustomerById(long id);

	Customer getCustomerByFullName(String fName, String lName);

	void addCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(Customer customer);
}
