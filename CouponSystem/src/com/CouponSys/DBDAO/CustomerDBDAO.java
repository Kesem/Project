package com.CouponSys.DBDAO;

import com.CouponSys.DAO.CustomerDAO;
import com.CouponSys.beans.Coupon;
import com.CouponSys.beans.Customer;

import connectionPool.DB_Executer;

import java.sql.*;
import java.util.Set;

public class CustomerDBDAO implements CustomerDAO
{



	@Override
	public void creathCustomer(Customer Customer)
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

	@Override
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

	@Override
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
	public boolean login(String username, String pass) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

}
