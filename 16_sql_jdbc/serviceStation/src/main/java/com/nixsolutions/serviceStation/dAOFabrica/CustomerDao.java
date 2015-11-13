/**
 * 
 */
package com.nixsolutions.serviceStation.dAOFabrica;

import java.util.List;

import com.nixsolutions.serviceStation.dbCommon.DBTables;
import com.nixsolutions.serviceStation.dbObjects.Customer;

/**
 * @author Михаил
 *
 */
public interface CustomerDao extends DBTables{
	/**
	 * get all customers from db
	 */
	public List<Customer> getAllCustomers();

	/**
	 * get phone by sqllab.customer lastName
	 */
	public List<String> getPhoneNumberByLastName(String lastName);

	/**
	 * get Customer By Phone Number
	 */
	public Customer getCustomerByPhoneNumber(String phone);

	/** create New Customer */
	public void createNewCustomer(String lastName, String firstName, String phoneNumber);

	/** delete Customer by lastName and firstName */
	public void deleteCustomer(String lastName, String firstName);
/**
 * update customer*/
	public void updateCustomer(Customer customer);
	}
