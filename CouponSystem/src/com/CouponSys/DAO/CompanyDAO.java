package com.CouponSys.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.CouponSys.beans.*;

public interface CompanyDAO
{

	//Using the company constructor variables to create the company.
		public void createCompany(Company company) throws SQLException;
		
		//Removing a company based on the company object. - option 1
		public void removeCompany(Company company) throws SQLException;

		//Removing a company based on the company object. - option 2
		public void removeCompany(long id) throws SQLException;

		//Updating a company based on the comp constructor.
		public void updateCompany(Company company) throws SQLException;
		
		//Get the company ID based on the company ID.
		public Company getCompany(long id) throws SQLException;
		
		public String getCompanyInfo(String compName) throws SQLException;
		
		// Returns a list of all the coupon purchases.
		public List<Coupon> companyPurchasedCoupons(long id) throws SQLException;
		
		// Returns if someone bought a coupon from company return true else false 
		public boolean isCompanyHavePurchasedCoupons(long id) throws SQLException;
		
		//Get all companies. (each company is Unique)
		public Set<Company> getAllCompanies() throws SQLException;
		
		//Get all company's coupons.
		public Set<Coupon> getAllCompanyCoupons(long id) throws SQLException;
		
		//Login - return a Boolean.
		public Company login(String compEmail, String password) throws SQLException;
	
}
