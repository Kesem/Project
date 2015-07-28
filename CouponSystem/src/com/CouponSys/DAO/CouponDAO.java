package com.CouponSys.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.CouponSys.beans.*;

public interface CouponDAO
{


	//Using the coupon constructor variables to create the coupon.
	public void createCoupon(Coupon coupon) throws SQLException;
		
	//Removing a Coupon based on the Coupon object. - option 1
	public void removeCoupon(Coupon coupon) throws SQLException;

	//Removing a Coupon based on the Coupon object. - option 2
	public void removeCoupon(long id) throws SQLException;
		
	//Updating a coupon based on the coupon constructor.
	public void updateCoupn(Coupon coupon) throws SQLException;
		
	//Get the coupon ID based on the coupon ID.
	public Coupon getCoupon(long id) throws SQLException;
	
	//Get all set list of all the coupons. (each coupon is Unique)
	public Set<Coupon> getAllCoupons() throws SQLException;
	
	//Get set list of coupons by type (enum).
	public Set<Coupon> getCouponByType(CouponType type) throws SQLException;
	
	//Check if coupon is purchased.
	public boolean isThisCouponPurchasedByCouponID(long id) throws SQLException;

	//this function is adding details to company_coupon table int database
	public void linkBetweenCouponToCompany(Company company, Coupon coupon) throws SQLException;

	//this function is adding details to company_coupon table int database
	public void linkBetweenCouponToCompany(long comp_id, long coup_id) throws SQLException;
	
	//this function is removing details to company_coupon table int database
	public void unLinkBetweenSpecificCouponToCompany(Company company, Coupon coupon) throws SQLException;

	//this function is removing details to company_coupon table int database
	public void unLinkBetweenSpecificCouponToCompany(long comp_id, long coup_id) throws SQLException;

	//Unlinks all company's coupons from company.
	public void unLinkBetweenAllCompanyCouponsFromCompany(long comp_id) throws SQLException;
	
	//this function marks all the coupons in the DB as Un_Available for purchase.
	public void markExpiredCoupons() throws SQLException;
	
	//this function marks single coupon as Expired.
	public void markThisCouponIfExpired(long coupId) throws SQLException;	
	
	//Check if coupon is Available For Purchase Or Not
	public boolean isCouponAvailableForPurchaseByCouponID(long coupId) throws SQLException;
	
}
