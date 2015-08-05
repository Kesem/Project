package com.CouponSys.DAO;

import java.sql.SQLException;
import java.util.*;

import com.CouponSys.beans.*;

public interface CustomerDAO
{

	//Using the customer constructor variables to create the customer.
	public void createCustomer(Customer customer) throws SQLException;
	
	//Removing a customer based on the customer object. - option 1
	public void removeCustomer(Customer customer) throws SQLException;
	
	//Removing a customer based on the customer object. - option 2
	public void removeCustomer(long id) throws SQLException;
	
	//Updating a customer based on the customer constructor.
	public void updateCustomer(Customer customer) throws SQLException;
	
	//Get the customer ID based on the customer ID.
	public Customer getCustomer(long id) throws SQLException;
	
	//Get all customers. (each customer is Unique)
	public Set<Customer> getAllCustomers() throws SQLException;
	
	//Get all customer's coupons.
	public List<Coupon> getAllPurchasedCouponsByCustID(long id) throws SQLException;
	
	// Returns true if customer bought any coupon else return false
	public boolean didCustomerPurchaseAnyCoupon(long id) throws SQLException; 
	
	//Login - return a Boolean.
	public Customer login(String custEmail, String password) throws SQLException;

	//purchase coupon by objects.
	public void purchaseCoupon(long cust_id, long coup_id, int amount, double purchasePrice) throws SQLException;


}
