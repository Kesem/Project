package com.CouponSys.DBDAO;

import com.CouponSys.DAO.CompanyDAO;
import com.CouponSys.beans.Company;
import com.CouponSys.beans.Coupon;

import connectionPool.DB_Executer;

import java.sql.*;
import java.util.Map;

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
			stmt.setString(2, compName.getCompName());
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
			stmt1.setString(1, compName.getCompName());
			stmt1.setString(3, compName.getPassword());
			stmt1.setString(4, compName.getEmail());

			stmt1.executeUpdate(newcompname);

			DB_Executer.returnConnection(con);
		} catch (SQLException e)
		{

			e.printStackTrace();

		}

	}

	@Override
	public void getCompanyID(Company compName)
	{
		{
			try
			{

				Connection con = DB_Executer.getConnection();

				String getcompidSQL = "SELECT ID FROM `CouponSys`.`Company` WHERE comp_name = ? ";
				PreparedStatement stmt = con.prepareStatement(getcompidSQL);
				stmt.setString(1, compName.getCompName());

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

	@Override
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

	@Override
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

	// TODO
	@Override
	public boolean login(String compName, String password)
	{

		return false;
	}

	@Override
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

	@Override
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

	

	@Override
	public void getCompanyInfo(Company compName) throws SQLException
	{
			try
			{
				
			
		Connection con = DB_Executer.getConnection();
		
		String getcompidSQL = "SELECT * FROM `CouponSys`.`Company` WHERE comp_name = ? ";
		PreparedStatement stmt = con.prepareStatement(getcompidSQL);
		stmt.setString(1, compName.getCompName());

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
