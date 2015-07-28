package com.CouponSys.beans;

import java.util.*;

import com.CouponSys.Common.CouponSysExceptions;

public class Company extends CommonDetails
{
	
	
	// Variables....
	// asdasdasd

	private Collection<Coupon> coupons = new HashSet<>();


	// Constructor to create a company with a list of coupons - useful to create
	// a new object for an existing company in the SQL.
	public Company(long id, String compName, String password, String email,
			Set<Coupon> couponList) throws CouponSysExceptions
	{
		super(id, compName, password, email);
		coupons = couponList;
	}

	// constructor to create a company without a coupon list - Useful for new
	// companies.
	public Company(long id, String compName, String password, String email)
			throws CouponSysExceptions
	{
		super(id, compName, password, email);
	}

	//
	// Getters & Setters.
	//

	// return company's set of coupons.
	public Set<Coupon> getCoupons()
	{
		return (Set<Coupon>) coupons;
	}

	// adding one Coupon object to set of coupons
	public void setCoupon(Coupon coupon)
	{
		this.coupons.add(coupon);
	}

	// adding Several of Coupon objects
	public void setCoupons(Set<Coupon> coupons)
	{
		this.coupons = coupons;
	}

	// This method overrides the entire coupon list OR sets a new one.
	// The constructor might not contain any coupon list.
	public void couponListOverride(Set<Coupon> couponList)
	{
		coupons = couponList;
	}

	// toString method.
	public String toString()
	{
		return super.toString();
	}

	public void printAllCouponList()
	{
		for (Coupon c : coupons)
		{
			System.out.println(c);
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coupons == null) ? 0 : coupons.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (coupons == null)
		{
			if (other.coupons != null)
				return false;
		} else if (!coupons.equals(other.coupons))
			return false;
		return true;
	}
}
