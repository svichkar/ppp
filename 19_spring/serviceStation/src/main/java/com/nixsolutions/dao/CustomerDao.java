/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.Customer;

public interface CustomerDao {
	/**
	 * get all customers from db
	 * 
	 * @throws SQLException
	 */
	public List<Customer> getAllCustomers();

	/**
	 * get Customer By Phone Number
	 * 
	 * @throws SQLException
	 */
	public Customer getCustomerByPhoneNumber(String phone);

	/**
	 * create New Customer
	 * 
	 * @throws SQLException
	 */
	public void createNewCustomer(Customer customer);

	/**
	 * delete Customer by lastName and firstName
	 * 
	 * @throws SQLException
	 */
	public void deleteCustomer(Customer customer);

	/**
	 * update customer
	 * 
	 * @throws SQLException
	 */
	public void updateCustomer(Customer customer);

	/**
	 * getCustomerByUserID
	 * 
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public Customer getCustomerByUserID(Integer user_id);

	/**
	 * getCustomerByID
	 * 
	 * @param custId
	 * @return
	 */
	Customer getCustomerByID(Integer custId);
}
