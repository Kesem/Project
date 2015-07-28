package com.CouponSys.Facade;

import java.sql.SQLException;
import java.util.*;

import com.CouponSys.*;
import com.CouponSys.beans.*;
import com.CouponSys.Common.CouponSysExceptions;
import com.CouponSys.DAO.CouponDAO;
import com.CouponSys.DAO.CustomerDAO;

public class CustomerFacade
{
		
		private  CustomerDAO customerDAO = null;
		private  CouponDAO couponDAO = null;
		private  Customer customer = null;
		
		//when the main program class gets a 'True' from login --> it will return a new CustomerFacade object. 
		//The facade object initializes by getting a DAO object from the main class.
		public CustomerFacade(CustomerDAO customerDAO, Customer customer) 
		{
			this.customerDAO = customerDAO;
			this.customer = customer;
		}

		//Purchase a coupon by receiving the cust_ID, the coup_ID and the amount of coupons.
		public void purchaseACoupon(long coup_id, int amount, double purchase_price) throws CouponSysExceptions
		{
			try {
				if (!couponDAO.isCouponAvailableForPurchaseByCouponID(coup_id))
					throw new CouponSysExceptions("Can't purchase because the coupon is not available for buy");
				
				customerDAO.purchaseCoupon(customer.getID(), coup_id, amount, purchase_price);
			} catch (SQLException e) {
				throw new CouponSysExceptions("SQL Exception - Can't Purchase because The coupon does not exist in the database",e.getErrorCode());
			}
		}
		
		//Purchase a coupon by receiving the customer, the coupon and the amount of coupons.
		public  void purchaseACoupon(Coupon coupon, int amount) throws CouponSysExceptions
		{
			try {
				if (!couponDAO.isCouponAvailableForPurchaseByCouponID(coupon.getID()))
					throw new CouponSysExceptions("Can't purchase because the coupon is not available for buy");
		
				customerDAO.purchaseCoupon(customer, coupon, amount);
			} catch (SQLException e) {		
				throw new CouponSysExceptions("SQL Exception - Can't Purchase The coupon does not exist in the database",e.getErrorCode());
			}
		}

		//Get/View a coupon.
			public  Coupon getCoupon(Long id) throws CouponSysExceptions
			{
				Coupon tempCoup = null;
				try {
					tempCoup = couponDAO.getCoupon(id);
				} catch (SQLException e) {
					throw new CouponSysExceptions("Wrong ID! Coupon does not exists in system",e.getErrorCode());
				}
				return tempCoup;

			} 
			
			
		//return a list of all the customer's purchased coupon.
		public  List<Coupon> getAllCustomerPurchasedCoupon() throws CouponSysExceptions
		{
			try {
				return customerDAO.getAllPurchasedCouponsByCustID(customer.getID());
			} catch (SQLException e) {
				throw new CouponSysExceptions("SQL Exception - Can't get your coupon purchased list");
			}
		}
		
		//Update customer's info straight to database.
		public void updateCustomerInfo(Customer customer) throws CouponSysExceptions
		{
			if (this.customer.getID() != customer.getID())
				throw new CouponSysExceptions("Can't update customer because the customer object is not equals to customer facade");

			this.customer = customer;
			
			try {
				customerDAO.updateCustomer(this.customer);
			} catch (SQLException e) {
				throw new CouponSysExceptions("SQL Exception - Can't update your info details",e.getErrorCode());
			}

		} 
		
		//Update customer's info straight to database.
		public void updateCustomerInfo(String compName, String password, String email) throws CouponSysExceptions
		{
			customer.setName(compName);
			customer.setPassword(password);
			customer.setEmail(email);
			
			try {
				customerDAO.updateCustomer(this.customer);
			} catch (SQLException e) {
				throw new CouponSysExceptions("SQL Exception - Can't update your info details",e.getErrorCode());
			}
		} 
		
		public Customer getCustomer(long id) throws CouponSysExceptions
		{
			try {
				return customerDAO.getCustomer(id);
			} catch (SQLException e) {
				throw new CouponSysExceptions("No such customer in database.");
			}
		}

}
