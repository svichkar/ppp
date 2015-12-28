/**
 * 
 */
package com.nixsolutions.dao;

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
	 * @
	 */
	public List<Customer> getAllCustomers() ;

	/**
	 * get phone by sqllab.customer lastName
	 * 
	 * @
	 */
	public List<String> getPhoneNumberByLastName(String lastName) ;

	/**
	 * get Customer By Phone Number
	 * 
	 * @
	 */
	public Customer getCustomerByPhoneNumber(String phone) ;

	/**
	 * create New Customer
	 * 
	 * @
	 */
	public void createNewCustomer(String lastName, String firstName, String phoneNumber, Integer userId)
			;

	/**
	 * delete Customer by lastName and firstName
	 * 
	 * @
	 */
	public void deleteCustomer(String lastName, String firstName) ;

	/**
	 * update customer
	 * 
	 * @
	 */
	public void updateCustomer(Customer customer) ;

	/**
	 * getCustomerByUserID
	 * 
	 * @param userId
	 * @return
	 * @
	 */
	public Customer getCustomerByUserID(Integer userId) ;
}
