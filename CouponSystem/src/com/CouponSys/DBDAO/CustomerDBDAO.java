package com.CouponSys.DBDAO;

import com.CouponSys.Common.CouponSysExceptions;
import com.CouponSys.DAO.CustomerDAO;
import com.CouponSys.beans.Coupon;
import com.CouponSys.beans.CouponType;
import com.CouponSys.beans.Customer;

import connectionPool.DB_Executer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CustomerDBDAO implements CustomerDAO
{



	@Override
	public void createCustomer(Customer Customer)
	{
		{
			try
			{

				Connection con = DB_Executer.getConnection();

				String newcust = "INSERT INTO `CouponSys`.`Customer` (`ID`, `CUST_NAME`, `PASSWORD`,`EMAIL`) "
						+ "VALUES ('?', '?', '?','?')";
				PreparedStatement stmt = con.prepareStatement(newcust);
				stmt.setLong(1, Customer.getID());
				stmt.setString(2, Customer.getCustName());
				stmt.setString(3, Customer.getPassword());
				stmt.setString(4, Customer.getEmail());
				stmt.executeUpdate(newcust);

				DB_Executer.returnConnection(con);

			} catch (SQLException e)
			{

				e.printStackTrace();

			}
		}

	}

	@Override
	public void removeCustomer(Customer Customer)
	{
		{
			try
			{

				Connection con = DB_Executer.getConnection();

				String removecustSQL = "DELETE FROM `CouponSys`.`Customer` WHERE `ID`='?'";
				PreparedStatement stmt = con.prepareStatement(removecustSQL);
				stmt.setLong(1, Customer.getID());

				stmt.executeUpdate(removecustSQL);

				DB_Executer.returnConnection(con);

			} catch (SQLException e)
			{

				e.printStackTrace();

			}
		}

	}

	@Override
	public void updateCustomer(Customer Customer)
	{
		{
			try
			{

				Connection con = DB_Executer.getConnection();

				String updatecust = "INSERT INTO `CouponSys`.`Customer` (`ID`, `CUST_NAME`, `PASSWORD`,`EMAIL`) "
						+ "VALUES ('?', '?', '?','?')";
				PreparedStatement stmt = con.prepareStatement(updatecust);
				stmt.setLong(1, Customer.getID());
				stmt.setString(2, Customer.getCustName());
				stmt.setString(3, Customer.getPassword());
				stmt.setString(4, Customer.getEmail());
				stmt.executeUpdate(updatecust);

				DB_Executer.returnConnection(con);

			} catch (SQLException e)
			{

				e.printStackTrace();

			}
		}

	}

	public Customer getCustomerID(Customer Customer)
	{
		{
			try
			{

				Connection con = DB_Executer.getConnection();

				String getCustID = "SELECT ID FROM `CouponSys`.`Customer` WHERE comp_name = ? ";
				PreparedStatement stmt = con.prepareStatement(getCustID);
				stmt.setString(1, Customer.getCustName());

				ResultSet rs = stmt.executeQuery(getCustID);
				ResultSetMetaData md = rs.getMetaData();
				int colCount = md.getColumnCount();
				while (rs.next())
				{
					System.out.println("----------------");
					for (int i = 1; i <= colCount; i++)
						System.out.println(md.getColumnName(i) + " : "
								+ rs.getString(i));

				}

				DB_Executer.returnConnection(con);

			} catch (SQLException e)
			{

				e.printStackTrace();

			}
		}
		return Customer;

	}

	@Override
	public Set<Customer> getAllCustomers()
	{
		{
			try
			{

				Connection con = DB_Executer.getConnection();

				Statement stmt = con.createStatement();

				String allCustomers = "SELECT * FROM CouponSys.Customer";
				ResultSet rs = stmt.executeQuery(allCustomers);
				ResultSetMetaData md = rs.getMetaData();
				int colCount = md.getColumnCount();
				while (rs.next())
				{
					System.out.println("----------------");
					for (int i = 1; i <= colCount; i++)
						System.out.println(md.getColumnName(i) + " : "
								+ rs.getString(i));

				}

				DB_Executer.returnConnection(con);

			} catch (SQLException e)
			{

				e.printStackTrace();

			}
		}
		return null;

	}

	public Set<Coupon> getCoupons(Customer Customer)
	{
		{
			try
			{

				Connection con = DB_Executer.getConnection();

				String getCuponforCust = "SELECT * FROM `CouponSys`.`Customer_Coupon` WHERE CUST_ID=?";
				PreparedStatement stmt = con.prepareStatement(getCuponforCust);

				stmt.setLong(1, Customer.getID());

				ResultSet rs = stmt.executeQuery(getCuponforCust);
				ResultSetMetaData md = rs.getMetaData();
				int colCount = md.getColumnCount();
				while (rs.next())
				{
					System.out.println("----------------");
					for (int i = 1; i <= colCount; i++)
						System.out.println(md.getColumnName(i) + " : "
								+ rs.getString(i));

				}
				DB_Executer.returnConnection(con);

			} catch (SQLException e)
			{

				e.printStackTrace();

			}
		}
		return null;

	}



	@Override
	public void removeCustomer(long id) throws SQLException
	{
		Connection con	= DB_Executer.getConnection();
		
		 //no need to check if id exist in database because id variable is primary key
		 
		 //Delete statement in SQL
		 PreparedStatement prepared_stmt = con.prepareStatement("DELETE FROM `customer` WHERE `ID`=?");
		 prepared_stmt.setLong(1, id);
		 prepared_stmt.executeUpdate(); //this action sending new data to to dataBase
		 
		 DB_Executer.returnConnection(con); //this function sending the connection back to connectionPool
		
	}

	@Override
	public Customer getCustomer(long id) throws SQLException
	{
		Connection con	= DB_Executer.getConnection(); //getting a connection
		Statement stmt = con.createStatement(); //create a statement 
		ResultSet rs = stmt.executeQuery("select * from customer"); // The result set is like an iterator on the results.
		
		Customer tempCust = null; //Will return null if id is no found.
		while (rs.next())
		{	 
			 if (id == rs.getLong("ID"))
			 {	 
					tempCust = new Customer(rs.getLong("ID"), rs.getString("CUST_NAME"),rs.getString("PASSWORD"),rs.getString("EMAIL"));
			 }
		}
		DB_Executer.returnConnection(con); //this function sending the connection back to connectionPool
		return tempCust; 
	}

	@Override
	public List<Coupon> getAllPurchasedCouponsByCustID(long id)
			throws SQLException
	{
		Connection con	= DB_Executer.getConnection(); //getting a connection
		Statement stmt = con.createStatement(); //create a statement 
		ResultSet rs = stmt.executeQuery("SELECT * "
				+ "FROM coupon_project.coupon c JOIN coupon_project.customer_coupon cc "
				+ "on cc.COUPON_ID = c.ID "
				+ "where cc.CUST_ID=" + id); 
		
		List<Coupon> Coupons = new ArrayList<>(); // Initializes list of Customer's coupon Will return empty list if there is no purchase coupons.
		while (rs.next())
		{	 
			Coupons.add(new Coupon(rs.getLong("c.ID"), rs.getString("c.TITLE"), rs.getDate("c.startDate"), rs.getDate("c.endDate"), 
					rs.getInt("cc.AMOUNT"), CouponType.valueOf((String) rs.getObject("c.TYPE")), rs.getDouble("c.price"), rs.getBoolean("c.ACTIVE")));
		}
		DB_Executer.returnConnection(con); //this function sending the connection back to connectionPool
		return Coupons; 
	}

	@Override
	public boolean didCustomerPurchaseAnyCoupon(long id) throws SQLException
	{
		Connection con	= DB_Executer.getConnection(); //getting a connection
		Statement stmt = con.createStatement(); //create a statement 
		ResultSet rs = stmt.executeQuery("SELECT * "
				+ "FROM coupon_project.coupon c JOIN coupon_project.customer_coupon cc "
				+ "on cc.COUPON_ID = c.ID "
				+ "where cc.CUST_ID=" + id); 
		
		boolean isIt = false;
		if (rs.next())
		{	 
			isIt = true; 
		}
		DB_Executer.returnConnection(con); //this function sending the connection back to connectionPool
		return isIt; 
	}

	@Override
	public Customer login(String custEmail, String password)
			throws SQLException
	{
		Connection con	= DB_Executer.getConnection(); //getting a connection
		Statement stmt = con.createStatement(); //create a statement 
		ResultSet rs = stmt.executeQuery("select * from customer");
		while (rs.next())
		{
			if (custEmail.equals(rs.getString("EMAIL")) && password.equals(rs.getString("PASSWORD")))
			{
				return getCustomer(rs.getLong("ID"));
			}
		}
		DB_Executer.returnConnection(con); //this function sending the connection back to connectionPool
		return null;
	}

	@Override
	public void purchaseCoupon(long cust_id, long coup_id, int amount, double purchasePrice) throws SQLException
	{
		Connection con	= DB_Executer.getConnection();
		 //no need to check if id exist in database because id variable is primary key
		 
		 //Insert new data - SQL statement
		 PreparedStatement prepared_stmt = con.prepareStatement("INSERT INTO `customer_coupon` VALUES (?, ?, ?, ?)");
		 prepared_stmt.setLong(1, cust_id);
		 prepared_stmt.setLong(2, coup_id);
		 prepared_stmt.setInt(3, amount);
		 prepared_stmt.setDouble(4, purchasePrice);
		 prepared_stmt.executeUpdate(); //this action sending new data to to dataBase
		 
		 DB_Executer.returnConnection(con); //this function sending the connection back to connectionPool	
		
	}



		


}
