package com.CouponSys.Common;

import java.sql.SQLException;
import java.util.*;

import DailyCouponExpirationTask.DailyCouponExpirationTask;

import com.CouponSys.*;
import com.CouponSys.DAO.*;
import com.CouponSys.beans.*;
import com.CouponSys.Common.*;
import com.CouponSys.DBDAO.*;
import com.CouponSys.Facade.*;

import connectionPool.DB_Executer;


public class CouponSystem
{

		
		//Singleton step 1
		private static CouponSystem couponSystem = new CouponSystem();
		
		private CompanyDAO companyDAO = new CompanyDBDAO();
		private CustomerDAO customerDAO = new CustomerDBDAO();
		private CouponDAO couponDAO = new CouponDBDAO();

		private DailyCouponExpirationTask task = new DailyCouponExpirationTask(couponDAO);
		
		//those sets saving all online users
		public static List<Company> onlineCompanies = new ArrayList<>();
		public static List<Customer> onlineCustomers = new ArrayList<>();

		
		//Singleton step 2
		private CouponSystem()
		{
			task.start();
		}
		
		//Singleton step 3
		public static CouponSystem getInstance()
		{
			return couponSystem;
		}
		
		///////////////////////
		//LogIN/Out Functions//
		//////////////////////
		
		public synchronized AdminFacade AdminLogin(String name, String password)
		{
			if (name.equals("admin") && password.equals("1234"))
			{
				return new AdminFacade(companyDAO, customerDAO, couponDAO);
			}
			return null;
		}
		
		public synchronized CustomerFacade CusotmerLogin(String email, String password) throws CouponSysExceptions
		{
			try {
				if (customerDAO.login(email, password) != null)
				{
					CustomerFacade tempCustomerFacade = new CustomerFacade(customerDAO, customerDAO.login(email, password));
					onlineCustomers.add(customerDAO.login(email, password));
					return tempCustomerFacade;
				}
			} catch (SQLException e) {
				throw new CouponSysExceptions("Customer does not exist", e.getErrorCode());
			}
			return null;
		}
		
		public synchronized CompanyFacade CompanyLogin(String email, String password) throws CouponSysExceptions
		{
			try {
				if (companyDAO.login(email, password) != null)
				{
					CompanyFacade tempCompanyFacade = new CompanyFacade(companyDAO, couponDAO, companyDAO.login(email, password));
					onlineCompanies.add(companyDAO.login(email, password));
					return tempCompanyFacade;
				}
			} catch (SQLException e) {
				throw new CouponSysExceptions("Company does not exist", e.getErrorCode());
			}
			return null;
		}
		
		public void comapyLogOff(CompanyFacade cf)
		{
			onlineCompanies.remove(cf);
		}
		
		
		public void customerLogOff(CustomerFacade cf)
		{
			onlineCustomers.remove(cf);
		}
		
		//////////////////////
		//generic functions//
		/////////////////////
		
		public void shutdown()
		{
			DB_Executer.shutdownConnections();
			task.stopTask();
		}
}
