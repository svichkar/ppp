package com.nixsolutions.dao;

import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.User;

public interface CustomerDAO extends GenericDao<Customer> {

	public Customer findByFullName(String f_name, String l_name);

	public Customer findByUser(User user);

}
