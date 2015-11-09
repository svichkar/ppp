package h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import entities.PersistenceException;

public class CustomerDAOImpl extends AbstractH2DAO<Customer> {

	private class ChCustomer extends Customer {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}
	
	public CustomerDAOImpl(Connection conn) {
		super(conn);
	}

	@Override
	public String getSelectByID() {
		return "SELECT * FROM sqllab.customer WHERE customer_id = ?;";
	}

	@Override
	public String getSelectAll() {
		return "SELECT * FROM sqllab.customer;";
	}

	@Override
	public String getUpdate() {
		return "UPDATE sqllab.customer SET %1$s WHERE customer_id = %2$s;";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM sqllab.customer WHERE customer_id = ?;";
	}
	
	@Override
	public String getCreate() {
		return "INSERT INTO sqllab.customer (first_name, last_name, phone) VALUES (%s);";
	}

	@Override
	public List<Customer> parseResults(ResultSet rs) throws PersistenceException {
		List<Customer> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChCustomer customer = new ChCustomer();
				customer.setId(rs.getInt("customer_id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setPhone(rs.getString("phone"));
				resultList.add(customer);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

	@Override
	public Customer create() throws PersistenceException {
		Customer customer = new Customer();
		return createFrom(customer);
	}
}
