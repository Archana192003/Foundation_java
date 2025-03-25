package dao;

import entity.Customermodel;
public interface CustomerDao {
	Customermodel getCustomerById(int customerId);
	Customermodel getCustomerByUsername(String username);
	boolean registerCustomer(Customermodel customer);
	boolean updateCustomer(Customermodel customer);
	boolean deleteCustomer(int customerId);
	
	

}
