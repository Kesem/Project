package com.CouponSys.beans;

import java.util.*;

public class Customer
{
	// variables
	private long id;
	private String custName;
	private String password;
	private String email;

	private List<Coupon> coupons = new ArrayList<Coupon>(); // Customers can
															// purchase one
															// coupon multiple
															// times

	// Constructor
	public Customer(long id, String name, String pass, String email)
	{
		this.id = id;
		custName = name;
		password = pass;
		this.email = email;
	}

	public Customer(long id, String name, String pass, String email,
			List<Coupon> custCoupons)
	{
		this.id = id;
		custName = name;
		password = pass;
		this.email = email;
		this.coupons = custCoupons;
	}

	// Getters

	public long getID()
	{
		return id;
	}

	public String getCustName()
	{
		return custName;
	}

	public List<Coupon> getCoupons()
	{
		return coupons;
	}

	public String getPassword()
	{
		return password;
	}

	public String getEmail()
	{
		return email;
	}

	// Setters

	public void setID(long id)
	{
		this.id = id;
	}

	public void setCompName(String newName)
	{
		custName = newName;
	}

	public void setMap(List<Coupon> coupons)
	{
		this.coupons = coupons;
	}

	public void setPass(String pass)
	{
		this.password = pass;
	}

	public void addCoupon(List<Coupon> coupon)
	{
		this.coupons = coupon;

	}

	@Override
	public String toString()
	{
		return "id=" + id + " Name=" + custName + ", password=" + password
				+ ", email=" + email + ".";
	}

	public void setCoupons(List<Coupon> allPurchasedCouponsByCustID)
	{
		this.coupons = coupons;
		
	}

}
