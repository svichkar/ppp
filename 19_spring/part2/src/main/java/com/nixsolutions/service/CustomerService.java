package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.entities.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();

	public Customer getCustomerById(long id);

	public Customer getCustomerByFullName(String f_name, String l_name);

	public void addCustomer(Customer customer);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(Customer customer);

}
