package com.CouponSys.beans;
import java.util.*;
public class Customer
{
	// variables 
	private long id;
	private String custName;
	private String password;
	private String email;
	private Map<Long , Coupon> custCoupons = new HashMap<>();
	
	// Constructor 
	public Customer(long id, String name, String pass, String email)
	{
		this.id = id;
		custName = name;
		password = pass;
		this.email = email;
	}
	public Customer(long id, String name, String pass, String email, Map<Long, Coupon> custCoupons)
	{
		this.id = id;
		custName = name;
		password = pass;
		this.email = email;
		this.custCoupons = custCoupons;
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
	
	public Map<Long, Coupon> getCoupons()
	{
		return custCoupons;
	}
	public String getPassword()
	{
		return password;
	}
	public String getEmail()
	{
		return email;
	}
	
	//   Setters
	
	public void setID(long id)
	{
		this.id = id;
	}
	
	public void setCompName(String newName)
	{
		custName = newName;
	}
	
	public void setMap(Map<Long,Coupon> coupons)
	{
		this.custCoupons = coupons;
	}
	
	public void setPass(String pass)
	{
		this.password = pass;
	}
	
	
	public void addCoupon(Map<Long,Coupon> coupon)
	{
		this.custCoupons.entrySet();
		
	}
	
	
	@Override
	public String toString()
	{
	return "("+id+")" + custName + "Have this coupons: " + custCoupons.keySet();
	}
	
	public void showAllCoupons()
	{
		for (Coupon c:custCoupons.values())
		{
			System.out.println(c);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
