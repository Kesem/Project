package com.CouponSys.Facade;

import java.util.*;
import java.sql.*;
import com.CouponSys.*;


import org.joda.time.DateTime;

import com.CouponSys.beans.*;
import com.CouponSys.DAO.*;

public class CompanyFacade
{

	private CompanyDAO companyDAO = null;
	private CouponDAO couponDAO = null;
	private Company company = null;

	public CompanyFacade(CompanyDAO companyDAO, CouponDAO couponDAO,
			Company company)
	{
		this.companyDAO = companyDAO;
		this.couponDAO = couponDAO;
		this.company = company;
	}

	public void createCoupon(long id, String title, DateTime startDate, DateTime endDate, int amount,
			CouponType type, double price, boolean active)
	{
		try
		{

			couponDAO.createCoupon(new Coupon(id, title, startDate, endDate, amount, type, price, active));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void removeCoupon(Coupon ID)
	{
		try
		{
			couponDAO.removeCoupon(ID);
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		
		
	}

	
	public void updateCoupon(Coupon ID)
	{
		try
		{
			couponDAO.updateCoupon(ID);
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		
	}
	
	public void getCompanyInfo(Company compName)
	{
		
		try
		{
			companyDAO.getCompanyInfo(compName);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getCoupons(Company compID)
	{
		try
		{
			companyDAO.getCoupons(compID);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
