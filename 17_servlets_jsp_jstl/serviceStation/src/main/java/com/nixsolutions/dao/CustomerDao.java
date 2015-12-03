/**
 * 
 */
package com.nixsolutions.dao;

import java.sql.SQLException;
import java.util.List;

import com.nixsolutions.entity.Customer;

/**
 * @author Михаил
 *
 */
public interface CustomerDao extends DBTables {
	/**
	 * get all customers from db
	 * 
	 * @throws SQLException
	 */
	public List<Customer> getAllCustomers();

	/**
	 * get phone by sqllab.customer lastName
	 * 
	 * @throws SQLException
	 */
	public List<String> getPhoneNumberByLastName(String lastName);

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
	public void createNewCustomer(String lastName, String firstName, String phoneNumber, Integer user_id);

	/**
	 * delete Customer by lastName and firstName
	 * 
	 * @throws SQLException
	 */
	public void deleteCustomer(String lastName, String firstName);

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
