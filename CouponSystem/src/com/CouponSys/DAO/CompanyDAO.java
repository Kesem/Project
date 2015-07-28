package com.CouponSys.DAO;

import java.sql.SQLException;
import java.util.Map;

import com.CouponSys.beans.*;

public interface CompanyDAO
{

	public void createCompany(Company compName) throws SQLException;

	public void removeCompany(Company compName) throws SQLException;

	public void updateCompany(Company compName) throws SQLException;

	public void getCompanyID(Company compName) throws SQLException;

	public Map<Long, Coupon> getAllCompanys() throws SQLException; 

	public void getCoupons(Company compID) throws SQLException;

	public boolean login(String compName, String password) throws SQLException;

	public void addCoupon(Coupon Coupon) throws SQLException;

	public void removeCoupon(Coupon ID) throws SQLException;
	
	public void getCompanyInfo(Company company) throws SQLException;
	
}
