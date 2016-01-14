package com.nixsolutions.service;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.User;

public interface CustomerService {
	/**
	 * get all customers from db
	 * 
	 * @throws SQLException
	 */
	List<Customer> getAllCustomers();

	/**
	 * get Customer By Phone Number
	 * 
	 * @throws SQLException
	 */
	Customer getCustomerByPhoneNumber(String phone);

	/**
	 * create New Customer
	 * 
	 * @throws SQLException
	 */
	void createNewCustomer(Customer customer);

	/**
	 * delete Customer by lastName and firstName
	 * 
	 * @throws SQLException
	 */
	void deleteCustomer(Customer customer);

	/**
	 * update customer
	 * 
	 * @throws SQLException
	 */
	void updateCustomer(Customer customer);

	/**
	 * getCustomerByUserID
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	Customer getCustomerByUserID(Integer userId);

	/**
	 * getCustomerByID
	 * 
	 * @param custId
	 * @return
	 */
	Customer getCustomerByID(Integer custId);

	/**
	 * getCustomerByID
	 * 
	 * @param custId
	 * @return
	 */
	Customer getCustomerByID(String custId);

	void createNewCustomer(User userByLogin, String lastName, String firstName, String phone);

	void updateCustomer(User userByID, String customerId, String lastName, String firstName, String phone);

	void deleteCustomer(String customer_id);

}
