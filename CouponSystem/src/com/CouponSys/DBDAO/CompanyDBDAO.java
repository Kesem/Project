package com.CouponSys.DBDAO;

import com.CouponSys.Common.CouponSysExceptions;
import com.CouponSys.DAO.CompanyDAO;
import com.CouponSys.beans.Company;
import com.CouponSys.beans.Coupon;
import com.CouponSys.beans.CouponType;

import connectionPool.DB_Executer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class CompanyDBDAO implements CompanyDAO
{


	@Override
	public void createCompany(Company compName)

	{

		try
		{

			Connection con = DB_Executer.getConnection();

			String newcompSQL = "INSERT INTO `CouponSys`.`Company` (`ID`, `COMP_NAME`, `PASSWORD`, `EMAIL`) "
					+ "VALUES ('?', '?', '?', '?')";
			PreparedStatement stmt = con.prepareStatement(newcompSQL);

			stmt.setLong(1, compName.getID());
			stmt.setString(2, compName.getName());
			stmt.setString(3, compName.getPassword());
			stmt.setString(4, compName.getEmail());

			stmt.executeUpdate(newcompSQL);

			DB_Executer.returnConnection(con);
		} catch (SQLException e)
		{

			e.printStackTrace();

		}

	}

	@Override
	public void removeCompany(Company compName)
	{
		try
		{

			Connection con = DB_Executer.getConnection();

			String removecompSQL = "DELETE FROM `CouponSys`.`Company` WHERE `ID`='?'";
			PreparedStatement stmt = con.prepareStatement(removecompSQL);
			stmt.setLong(1, compName.getID());

			stmt.executeUpdate(removecompSQL);

			DB_Executer.returnConnection(con);
		} catch (SQLException e)
		{

			e.printStackTrace();

		}

	}

	@Override
	public void updateCompany(Company compName)
	{

		try
		{

			Connection con = DB_Executer.getConnection();
			String newcompname = "UPDATE `CouponSys`.`Company` SET `COMP_NAME`='?' WHERE `ID`='?';";
			PreparedStatement stmt1 = con.prepareStatement(newcompname);

			stmt1.setLong(2, compName.getID());
			stmt1.setString(1, compName.getName());
			stmt1.setString(3, compName.getPassword());
			stmt1.setString(4, compName.getEmail());

			stmt1.executeUpdate(newcompname);

			DB_Executer.returnConnection(con);
		} catch (SQLException e)
		{

			e.printStackTrace();

		}

	}

	
	public void getCompanyID(Company compName)
	{
		{
			try
			{

				Connection con = DB_Executer.getConnection();

				String getcompidSQL = "SELECT ID FROM `CouponSys`.`Company` WHERE comp_name = ? ";
				PreparedStatement stmt = con.prepareStatement(getcompidSQL);
				stmt.setString(1, compName.getName());

				ResultSet rs = stmt.executeQuery(getcompidSQL);
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

	}

	
	public Map<Long, Coupon> getAllCompanys()
	{

		{
			try
			{

				Connection con = DB_Executer.getConnection();

				Statement stmt = con.createStatement();
				// TODO
				String allcompSQL = "SELECT * FROM CouponSys.Company";
				ResultSet rs = stmt.executeQuery(allcompSQL);
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

	
	public void getCoupons(Company compID)
	{
		{
			try
			{

				Connection con = DB_Executer.getConnection();

				String getcoupnfromcompSQL = "SELECT * FROM `CouponSys`.`Company_Coupon` WHERE COMP_ID=?";
				PreparedStatement stmt = con
						.prepareStatement(getcoupnfromcompSQL);

				stmt.setLong(1, compID.getID());

				ResultSet rs = stmt.executeQuery(getcoupnfromcompSQL);
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

	}

	
	@Override
	public Company login(String email, String password) throws SQLException
	{
		Connection con	= DB_Executer.getConnection(); //getting a connection
		Statement stmt = con.createStatement(); //create a statement 
		ResultSet rs = stmt.executeQuery("select * from customer");
		while (rs.next())
		{
			if (email.equals(rs.getString("EMAIL")) && password.equals(rs.getString("PASSWORD")))
			{
				return getCompany(rs.getLong("ID"));
			}
		}
		DB_Executer.returnConnection(con); //this function sending the connection back to connectionPool
		return null;
	}

	
	public void addCoupon(Coupon Coupon)
	{
		try
		{
			Connection con = DB_Executer.getConnection();

			String addCoupon = "INSERT INTO `CouponSys`.`Coupon` (`ID`, `TITLE`, `START_DATE`, `END_DATE`, `AMOUNT`, `TYPE`, `MESSAGE`, `PRICE`, `IMAGE`) "
					+ "VALUES ('1212', 'title', 'start_date', 'end_date', 'amount', 'type', 'msg', 'money', 'img')";
			PreparedStatement stmt = con.prepareStatement(addCoupon);

			stmt.setLong(1, Coupon.getID());
			stmt.setString(2, Coupon.getTitle());
			stmt.setObject(3, Coupon.getStartDate());
			stmt.setObject(4, Coupon.getEndDate());

			stmt.executeUpdate(addCoupon);

		} catch (SQLException e)
		{

			e.printStackTrace();

		}

	}

	
	public void removeCoupon(Coupon Coupon)
	{
		try
		{

			Connection con = DB_Executer.getConnection();

			String RemoveCoupon = "UPDATE `CouponSys`.`Coupon` SET `ACTIVE`='0' WHERE `ID`='?'";
			PreparedStatement stmt = con.prepareStatement(RemoveCoupon);
			stmt.setLong(1, Coupon.getID());

			stmt.executeUpdate();

		} catch (SQLException e)
		{

			e.printStackTrace();

		}

	}

	

	
	public void getCompanyInfo(Company compName) throws SQLException
	{
			try
			{
				
			
		Connection con = DB_Executer.getConnection();
		
		String getcompidSQL = "SELECT * FROM `CouponSys`.`Company` WHERE comp_name = ? ";
		PreparedStatement stmt = con.prepareStatement(getcompidSQL);
		stmt.setString(1, compName.getName());

		ResultSet rs = stmt.executeQuery(getcompidSQL);
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

	@Override
	public void removeCompany(long id) throws SQLException
	{
		Connection con	= DB_Executer.getConnection(); //getting a connection
		 //no need to check if id exist in database because id variable is primary key
		 
		 //Delete statement in SQL
		 PreparedStatement prepared_stmt = con.prepareStatement("DELETE FROM `company` WHERE `ID`=?");
		 prepared_stmt.setLong(1, id);
		 prepared_stmt.executeUpdate(); //this action sending new data to to dataBase
		 
		 DB_Executer.returnConnection(con); //this function sending the connection back to connectionPool
		
	}

	@Override
	public Company getCompany(long id) throws SQLException
	{
		Connection con	= DB_Executer.getConnection(); //getting a connection
		Statement stmt = con.createStatement(); //create a statement 
		ResultSet rs = stmt.executeQuery("select * from company");
		
		Company tempComp = null; //Will return null if id is no found.
		 while (rs.next())
		 {	 
			 if (id == rs.getLong("ID"))
			 {	 
				 try {
					tempComp = new Company(rs.getLong("ID"), rs.getString("COMP_NAME"),rs.getString("PASSWORD"),rs.getString("EMAIL"));
				} catch (CouponSysExceptions e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			 }
		}
		DB_Executer.returnConnection(con); //this function sending the connection back to connectionPool
		return tempComp; 
	}

	@Override
	public List<Coupon> companyPurchasedCoupons(long id) throws SQLException
	{
		Connection con	= DB_Executer.getConnection(); //getting a connection
		Statement stmt = con.createStatement(); //create a statement 
		ResultSet rs = stmt.executeQuery("SELECT * "
				+ "FROM coupon_project.coupon c JOIN coupon_project.company_coupon cc "
				+ "on cc.COUPON_ID = c.ID "
				+ "JOIN coupon_project.customer_coupon ccc"
				+ "on cc.COUPON_ID = ccc.COUPON_ID"
				+ "where cc.COMP_ID=" + id); 
		
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
	public boolean isCompanyHavePurchasedCoupons(long id) throws SQLException
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Company> getAllCompanies() throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Coupon> getAllCompanyCoupons(long id) throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
