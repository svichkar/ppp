/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CustomerDao;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.User;
import com.nixsolutions.service.CustomerService;
import com.nixsolutions.service.UserService;

/**
 * @author mixeyes
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.CustomerService#getAllCustomers()
	 */
	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.CustomerService#getCustomerByPhoneNumber(java.
	 * lang.String)
	 */
	@Override
	public Customer getCustomerByPhoneNumber(String phone) {
		return customerDao.getCustomerByPhoneNumber(phone);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.CustomerService#createNewCustomer(com.
	 * nixsolutions.entity.Customer)
	 */
	@Override
	public void createNewCustomer(Customer customer) {
		customerDao.createNewCustomer(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.CustomerService#deleteCustomer(com.nixsolutions.
	 * entity.Customer)
	 */
	@Override
	public void deleteCustomer(Customer customer) {
		customerDao.deleteCustomer(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.CustomerService#updateCustomer(com.nixsolutions.
	 * entity.Customer)
	 */
	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nixsolutions.service.CustomerService#getCustomerByUserID(java.lang.
	 * Integer)
	 */
	@Override
	public Customer getCustomerByUserID(Integer userId) {
		return customerDao.getCustomerByUserID(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.CustomerService#getCustomerByID(java.lang.
	 * Integer)
	 */
	@Override
	public Customer getCustomerByID(Integer custId) {
		return customerDao.getCustomerByID(custId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nixsolutions.service.CustomerService#getCustomerByID(java.lang.
	 * Integer)
	 */
	@Override
	public Customer getCustomerByID(String custId) {
		return getCustomerByID(Integer.decode(custId));
	}

	@Override
	public void createNewCustomer(User userByLogin, String lastName, String firstName, String phone) {
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPhone(phone);
		customer.setUser(userByLogin);
		createNewCustomer(customer);
	}

	@Override
	public void updateCustomer(User userByID, String customerId, String lastName, String firstName, String phone) {
		Customer customer = getCustomerByID(customerId);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPhone(phone);
		customer.setUser(userByID);
		updateCustomer(customer);
	}

	@Override
	public void deleteCustomer(String customer_id) {
		Customer customer = getCustomerByID(customer_id);
		deleteCustomer(customer);
		User user = userService.getUserByID(customer.getUser().getUserId().intValue());
		userService.deleteUser(user);

	}

}
