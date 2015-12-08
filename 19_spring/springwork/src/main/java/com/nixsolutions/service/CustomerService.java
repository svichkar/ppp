package com.nixsolutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.hibernate.entity.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDAO customerDao;

	public List<Customer> getAllCustomers() {
		return customerDao.getAll();
	}
}
