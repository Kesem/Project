package com.CouponSys.Facade;

import java.util.*;
import java.sql.*;

import com.CouponSys.DAO.*;
import com.CouponSys.beans.*;
import com.CouponSys.Common.*;

public class AdminFacade
{

	private CompanyDAO companyDAO = null;
	private CouponDAO couponDAO = null;
	private CustomerDAO customerDAO = null;

	public AdminFacade(CompanyDAO companyDAO,CustomerDAO customerDAO, CouponDAO couponDAO )
	{
		this.companyDAO = companyDAO;
		this.couponDAO = couponDAO;
		this.customerDAO = customerDAO;

	}

	public CompanyFacade createCompany(Company company) throws CouponSysExceptions
	{
		try {
			companyDAO.createCompany(company);
		} catch (SQLException e) {
			throws new CouponSysExceptions("Can't make new company, Company already exists", e.getErrorCode());
		}
		
		
		
	}

}
