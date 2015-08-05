package com.CouponSys.Facade;

import java.util.*;
import java.util.Date;
import java.sql.*;

import org.joda.time.DateTime;

import com.CouponSys.DAO.*;
import com.CouponSys.beans.*;
import com.CouponSys.Common.*;

public class AdminFacade
{

	private CompanyDAO companyDAO = null;
	private CouponDAO couponDAO = null;
	private CustomerDAO customerDAO = null;

	public AdminFacade(CompanyDAO companyDAO, CustomerDAO customerDAO,
			CouponDAO couponDAO)
	{
		this.companyDAO = companyDAO;
		this.couponDAO = couponDAO;
		this.customerDAO = customerDAO;

	}

	public CompanyFacade createCompany(Company company)
			throws CouponSysExceptions
	{
		try
		{
			companyDAO.createCompany(company);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't create company! Company already exists",
					e.getErrorCode());
		}
		CouponSystem.onlineCompanies.add(company);
		return new CompanyFacade(companyDAO, couponDAO, company);
	}

	// insert a new company to data base by receiving the actual company values
	// - and return companyFacade
	public CompanyFacade createCompany(Long id, String name, String password,
			String email) throws CouponSysExceptions
	{
		Company tempCompany = new Company(id, name, password, email);
		try
		{
			companyDAO.createCompany(tempCompany);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't create company! Company already exists",
					e.getErrorCode());
		}
		CouponSystem.onlineCompanies.add(tempCompany);
		return new CompanyFacade(companyDAO, couponDAO, tempCompany);
	}

	// Remove a company by receiving an object of type 'Company'.
	public void removeCompany(Company company) throws CouponSysExceptions
	{
		// first - if there is Company Purchased Coupons - user can't delete the
		// company from data base
		try
		{
			if (companyDAO.isCompanyHavePurchasedCoupons(company.getID()))
				throw new CouponSysExceptions(
						"Can't remove the company, some of the company's coupons were already bought.");
		} catch (SQLException e)
		{
			throw new CouponSysExceptions("The company ID does not exist");
		}

		// Second - Removes all the links between the company's coupons and the
		// company.
		try
		{
			couponDAO
					.unLinkBetweenAllCompanyCouponsFromCompany(company.getID());
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't unLink Between All Company Coupons!",
					e.getErrorCode());
		}
		// third - Removes all the company's coupons
		try
		{
			removeAllCompanyCoupons(company.getID());
		} catch (SQLException e)
		{
			throw new CouponSysExceptions("Can't remove company's coupons!",
					e.getErrorCode());
		}
		// forth - Remove the company
		try
		{
			companyDAO.removeCompany(company);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't remove company! Company does not exist",
					e.getErrorCode());
		}
	}

	// Remove a company by receiving the company's ID(Long).
	public void removeCompany(Long id) throws CouponSysExceptions
	{
		// first - if there is Company Purchased Coupons - user can't delete the
		// company from data base
		try
		{
			if (companyDAO.isCompanyHavePurchasedCoupons(id))
				throw new CouponSysExceptions(
						"Can't remove the company, some of the company's coupons were already bought.");
		} catch (SQLException e1)
		{
			throw new CouponSysExceptions("The company ID does not exist");
		}
		// Second - Removes all the links between the company's coupons and the
		// company.
		try
		{
			couponDAO.unLinkBetweenAllCompanyCouponsFromCompany(id);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't unLink Between All Company Coupons!",
					e.getErrorCode());
		}
		// third - Removes all the company's coupons
		try
		{
			removeAllCompanyCoupons(id);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions("Can't remove company's coupons!",
					e.getErrorCode());
		}
		// forth - Remove the company
		try
		{
			companyDAO.removeCompany(id);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't remove company! Company does not exist",
					e.getErrorCode());
		}
	}

	// remove All Company's Coupons by company id
	private void removeAllCompanyCoupons(Long id) throws CouponSysExceptions,
			SQLException
	{
		Set<Coupon> tempCoupons = companyDAO.getAllCompanyCoupons(id);
		for (Coupon c : tempCoupons)
		{
			couponDAO.removeCoupon(c);
		}
	}

	// Returns all the company's purchased coupons.
	public List<Coupon> getCompanyPurchasedCoupons(long id)
			throws CouponSysExceptions
	{
		try
		{
			return companyDAO.companyPurchasedCoupons(id);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions("The company ID does not exist");
		}
	}

	// Update a company by receiving an object of type 'Company'. because
	public void updateCompany(Company company) throws CouponSysExceptions
	{
		// Check if company is online. if yes don't allow admin to update data
		if (CouponSystem.onlineCompanies.contains(company))
			throw new CouponSysExceptions(
					"Can't Update database because Company is online");

		try
		{
			companyDAO.updateCompany(company);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't update a company that does not exist",
					e.getErrorCode());
		}
	}

	// Get/View all company's info without the coupon list.
	public Company getCompanyPersonalInfoOnly(Long id)
			throws CouponSysExceptions
	{
		Company tempComp = null;
		try
		{
			tempComp = companyDAO.getCompany(id);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions("Wrong ID! Company does not exist",
					e.getErrorCode());
		}
		return tempComp;
	}

	// Get/View all company's info with the coupon list.
	public Company getCompanyAndItsCouponsInfo(Long id)
			throws CouponSysExceptions
	{
		Company tempComp = null;
		try
		{
			tempComp = companyDAO.getCompany(id);
			tempComp.setCoupons(companyDAO.getAllCompanyCoupons(id));
		} catch (SQLException e)
		{
			throw new CouponSysExceptions("Wrong ID! Customer does not exist",
					e.getErrorCode());
		}
		return tempComp;
	}

	// Get/View all companies without coupons.
	public Set<Company> getAllCompaniesWithoutCoupon()
			throws CouponSysExceptions
	{
		Set<Company> tempSetList = null;
		try
		{
			tempSetList = companyDAO.getAllCompanies();
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"There are no companies in the database", e.getErrorCode());
		}
		return tempSetList;
	}

	// Get/View all companies with coupons.
	public Set<Company> getAllCompaniesWithCoupon() throws CouponSysExceptions
	{
		Set<Company> tempSetList = getAllCompaniesWithoutCoupon();
		for (Company c : tempSetList)
		{
			try
			{
				c.setCoupons(companyDAO.getAllCompanyCoupons(c.getID()));
			} catch (SQLException e)
			{
				throw new CouponSysExceptions(c.getName()
						+ "Does Not Exist in database.", e.getErrorCode());
			}
		}
		return tempSetList;
	}

	// ////////////////////////////
	// control panel of customers//
	// ///////////////////////////

	// insert a new customer to data base by getting a 'Customer' object. - and
	// return customerFacade
	public CustomerFacade createCustomer(Customer customer)
			throws CouponSysExceptions
	{
		try
		{
			customerDAO.createCustomer(customer);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't create customer! Customer already exists",
					e.getErrorCode());
		}
		CouponSystem.onlineCustomers.add(customer);
		return new CustomerFacade(customerDAO, customer);
	}

	// insert a new customer to data base by receiving the actual company values
	// - and return customerFacade
	public CustomerFacade createCustomer(long id, String name, String password,
			String email) throws CouponSysExceptions
	{
		Customer tempCustomer = new Customer(id, name, password, email);
		try
		{
			customerDAO.createCustomer(tempCustomer);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't create company! Company already exists",
					e.getErrorCode());
		}
		CouponSystem.onlineCustomers.add(tempCustomer);
		return new CustomerFacade(customerDAO, tempCustomer);
	}

	// Remove a customer by receiving an object of type 'Customer'.
	public void removeCustomer(Customer customer) throws CouponSysExceptions
	{
		try
		{
			if (customerDAO.didCustomerPurchaseAnyCoupon(customer.getID()))
				throw new CouponSysExceptions(
						"Can't remove the customer, because customer has bought coupon.");

			customerDAO.removeCustomer(customer);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't remove customer! Customer does not exist",
					e.getErrorCode());
		}
	}

	// Remove a customer by receiving an object of type 'ID'.
	public void removeCustomer(Long id) throws CouponSysExceptions
	{
		try
		{
			if (customerDAO.didCustomerPurchaseAnyCoupon(id))
				throw new CouponSysExceptions(
						"Can't remove the customer, because customer has bought coupon.");

			customerDAO.removeCustomer(id);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't remove customer! Customer does not exist",
					e.getErrorCode());
		}
	}

	// return all customer purchase coupon - by costumer ID.
	public List<Coupon> getCouponPurchasedHistoryByCustomerID(long id)
			throws CouponSysExceptions
	{
		try
		{
			return customerDAO.getAllPurchasedCouponsByCustID(id);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions("The Coupon ID does not exist");
		}
	}

	// Update a customer.
	public void updateCustomer(Customer customer) throws CouponSysExceptions
	{
		// Check if customer is online. if yes don't allow admin to update data
		if (CouponSystem.onlineCompanies.contains(customer))
			throw new CouponSysExceptions(
					"Can't Update database because Company is online");

		try
		{
			customerDAO.updateCustomer(customer);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't update a customer that does not exist",
					e.getErrorCode());
		}
	}

	// Get/View all customer's info without the coupon list.
	public Customer getCustomerPersonalInfoOnly(Long id)
			throws CouponSysExceptions
	{
		Customer tempCust = null;
		try
		{
			tempCust = customerDAO.getCustomer(id);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions("Wrong ID! Customer does not exist",
					e.getErrorCode());
		}
		return tempCust;
	}

	// Get/View all customer's info with the coupon list.
	public Customer getCustomerAndHisCouponInfo(Long id)
			throws CouponSysExceptions
	{
		Customer tempCust = null;
		try
		{
			tempCust = customerDAO.getCustomer(id);
			tempCust.setCoupons(customerDAO.getAllPurchasedCouponsByCustID(id));
		} catch (SQLException e)
		{
			throw new CouponSysExceptions("Wrong ID! Customer does not exist",
					e.getErrorCode());
		}
		return tempCust;
	}

	// Get/View all Customers without coupons.
	public Set<Customer> getAllCustomersWithoutCoupon()
			throws CouponSysExceptions
	{
		Set<Customer> tempSetList = null;
		try
		{
			tempSetList = customerDAO.getAllCustomers();
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"There are no customers in the database", e.getErrorCode());
		}
		return tempSetList;
	}

	// Get/View all customers with coupons.
	public Set<Customer> getAllCustomersWithCoupon() throws CouponSysExceptions
	{
		Set<Customer> tempSetList = getAllCustomersWithoutCoupon();
		for (Customer c : tempSetList)
		{
			try
			{
				c.setCoupons(customerDAO.getAllPurchasedCouponsByCustID(c
						.getID()));
			} catch (SQLException e)
			{
				throw new CouponSysExceptions(c.getCustName()
						+ "Does Not Exist in database.", e.getErrorCode());
			}
		}
		return tempSetList;
	}

	// ///////////////////////////
	// control panel of coupons///
	// //////////////////////////

	// Create a coupon.
	public void createCoupon(Coupon coupon, Company company)
			throws CouponSysExceptions
	{
		try
		{
			couponDAO.createCoupon(coupon);
			// This will initiate the function for marking the coupon as expired
			// if needed. If not will not change a thing.
			couponDAO.markThisCouponIfExpired(coupon.getID());
			couponDAO.linkBetweenCouponToCompany(company, coupon);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't create coupon! This coupon already exists",
					e.getErrorCode());
		}
	}

	// Create a coupon.
	public void createCoupon(long coup_id,long comp_id, String title, DateTime startDate, DateTime endDate, int amount,
			CouponType type, double price, boolean Valid) throws CouponSysExceptions
	{
		try {
			//This will initiate the function for marking the coupon as expired if needed. If not will not change a thing.
			couponDAO.markThisCouponIfExpired(coup_id);
			couponDAO.linkBetweenCouponToCompany(comp_id,coup_id);
		} catch (SQLException e) {
			throw new CouponSysExceptions("Can't create coupon! This coupon already exists",e.getErrorCode());
		}
	}
	// Remove a coupon by receiving an object of type 'Coupon'.
	public void removeCoupon(Coupon coupon) throws CouponSysExceptions
	{
		try
		{
			if (couponDAO.isThisCouponPurchasedByCouponID(coupon.getID()))
				throw new CouponSysExceptions(
						"Can't remove because someone already bought this coupon.");

			couponDAO.removeCoupon(coupon);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't remove coupon! Coupon does not exist",
					e.getErrorCode());
		}
	}

	// Remove a coupon by receiving an object of type 'ID'.
	public void removeCoupon(Long id) throws CouponSysExceptions
	{
		try
		{
			if (couponDAO.isThisCouponPurchasedByCouponID(id))
				throw new CouponSysExceptions(
						"Can't remove because someone already bought this coupon.");

			couponDAO.removeCoupon(id);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't remove coupon! Coupon does not exist",
					e.getErrorCode());
		}
	}

	// Update a coupon.
	public void updateCoupon(Coupon coupon) throws CouponSysExceptions
	{
		try
		{
			couponDAO.updateCoupon(coupon);
			// This will initiate the function for marking the coupon as expired
			// if needed. If not will not change a thing.
			couponDAO.markThisCouponIfExpired(coupon.getID());
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Can't update. Coupon does not exist", e.getErrorCode());
		}
	}

	// Get/View a coupon.
	public Coupon getCoupon(Long id) throws CouponSysExceptions
	{
		Coupon tempCoup = null;
		try
		{
			tempCoup = couponDAO.getCoupon(id);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"Wrong ID! Coupon does not exists in system",
					e.getErrorCode());
		}
		return tempCoup;

	}

	// Get/View all coupons.
	public Set<Coupon> getAllCoupons() throws CouponSysExceptions
	{
		Set<Coupon> tempSetList = null;
		try
		{
			tempSetList = couponDAO.getAllCoupons(null);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions(
					"There are no coupons in the database", e.getErrorCode());
		}
		return tempSetList;
	}

	// Get/View all coupons by type.
	public Set<Coupon> getListOfCouponsByType(CouponType type)
			throws CouponSysExceptions
	{
		Set<Coupon> tempSetList = null;
		try
		{
			tempSetList = couponDAO.getCouponByType(type);
		} catch (SQLException e)
		{
			throw new CouponSysExceptions("Type does not exist in database",
					e.getErrorCode());
		}
		return tempSetList;
	}

}
