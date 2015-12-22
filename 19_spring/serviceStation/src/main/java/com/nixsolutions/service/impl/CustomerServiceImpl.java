/**
 * 
 */
package com.nixsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CustomerDao;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.service.CustomerService;

/**
 * @author mixeyes
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

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
	public Customer getCustomerByUserID(Integer user_id) {
		return customerDao.getCustomerByUserID(user_id);
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

}
