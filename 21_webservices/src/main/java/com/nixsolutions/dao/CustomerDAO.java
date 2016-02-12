package com.nixsolutions.dao;

import com.nixsolutions.entities.Customer;
import com.nixsolutions.entities.User;

public interface CustomerDAO extends GenericDao<Customer> {

	Customer findByFullName(String fName, String lName);

	Customer findByUser(User user);

}
