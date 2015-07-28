package com.CouponSys.DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.*;
import org.joda.time.LocalDate.Property;

import com.CouponSys.Common.DateAndTimeExecuter;
import com.CouponSys.DAO.CouponDAO;
import com.CouponSys.beans.Coupon;
import com.CouponSys.beans.CouponType;

import connectionPool.DB_Executer;

public class CouponDBDAO implements CouponDAO
{

	@Override
	public void createCoupon(Coupon Coupon)
	{

		try
		{

			Connection con = DB_Executer.getConnection();

			String newcoupon = "INSERT INTO `CouponSys`.`Coupon` (`ID`, `TITLE`, `START_DATE`, `END_DATE`, `AMOUNT`, `TYPE`, `MESSAGE`, `PRICE`, `ACTIVE`)"
					+ " VALUES ('?', '?', '?', '?', '?', '?', '?', '?', '?');";
			PreparedStatement stmt = con.prepareStatement(newcoupon);

			stmt.setLong(1, Coupon.getID());
			stmt.setString(2, Coupon.getTitle());
			stmt.setObject(3, Coupon.getStartDate());
			stmt.setObject(4, Coupon.getEndDate());
			stmt.setInt(5, Coupon.getAmount());
			stmt.setObject(6, Coupon.getType());
			stmt.setString(7, Coupon.getMessage());
			stmt.setDouble(8, Coupon.getPrice());
			stmt.setBoolean(9, true);

			stmt.executeUpdate(newcoupon);

			DB_Executer.returnConnection(con);
		} catch (SQLException e)
		{

			e.printStackTrace();

		}

	}

	@Override
	public void removeCoupon(Coupon Coupon)
	{
		{

			try
			{

				Connection con = DB_Executer.getConnection();

				String removecoupon = "UPDATE `CouponSys`.`Coupon` SET `ACTIVE`='0' WHERE `ID`='?'";
				PreparedStatement stmt = con.prepareStatement(removecoupon);
				stmt.setLong(1, Coupon.getID());

				stmt.executeUpdate(removecoupon);

				DB_Executer.returnConnection(con);
			} catch (SQLException e)
			{

				e.printStackTrace();

			}
		}
	}

	@Override
	public void updateCoupon(Coupon Coupon)
	{
		{

			try
			{

				Connection con = DB_Executer.getConnection();

				String updatecoupon = "UPDATE `CouponSys`.`Coupon` SET `ID`='?', `TITLE`='?', `START_DATE`='?', `END_DATE`='?',"
						+ " `AMOUNT`='?', `TYPE`='?', `MESSAGE`='?', `PRICE`='?', `ACTIVE`='?', WHERE `ID`='?'";
				PreparedStatement stmt = con.prepareStatement(updatecoupon);

				stmt.setLong(1, Coupon.getID());
				stmt.setString(2, Coupon.getTitle());
				stmt.setObject(3, Coupon.getStartDate());
				stmt.setObject(4, Coupon.getEndDate());
				stmt.setInt(5, Coupon.getAmount());
				stmt.setObject(6, Coupon.getType());
				stmt.setString(7, Coupon.getMessage());
				stmt.setDouble(8, Coupon.getPrice());
				stmt.setBoolean(9, Coupon.isValid());
				stmt.setLong(10, Coupon.getID());

				stmt.executeUpdate(updatecoupon);

				DB_Executer.returnConnection(con);
			} catch (SQLException e)
			{

				e.printStackTrace();

			}
		}

	}

	public Coupon getCoupon(long ID)
	{
		{

			try
			{

				Connection con = DB_Executer.getConnection();
				String getCouponfromDB = "SELECT * FROM `CouponSys`.`Coupon` WHERE `ID`='?'";
				PreparedStatement stmt = con.prepareStatement(getCouponfromDB);
				stmt.setLong(1, ID);

				ResultSet rs = stmt.executeQuery(getCouponfromDB);
				ResultSetMetaData md = rs.getMetaData();
				int colCount = md.getColumnCount();

				DB_Executer.returnConnection(con);
			} catch (SQLException e)
			{

				e.printStackTrace();

			}
		}
		return null;

	}

	@Override
	public Set<Coupon> getAllCoupons(Coupon Coupon)
	{
		{

			try
			{

				Connection con = DB_Executer.getConnection();
				Statement stmt = con.createStatement();

				String getallcoupons = "SELECT * FROM CouponSys.Coupon;";
				ResultSet rs = stmt.executeQuery(getallcoupons);
				ResultSetMetaData md = rs.getMetaData();
				int colCount = md.getColumnCount();
				while (rs.next())
				{
					System.out.println("----------------");
					for (int i = 1; i <= colCount; i++)
						System.out.println(md.getColumnName(i) + " : "
								+ rs.getString(i));

				}

				stmt.executeUpdate(getallcoupons);

				DB_Executer.returnConnection(con);
			} catch (SQLException e)
			{

				e.printStackTrace();

			}
		}
		return null;

	}

	@Override
	public Set<Coupon> getCouponByType(CouponType Type)
	{

		try
		{

			Connection con = DB_Executer.getConnection();

			String getcoupontype = "SELECT * FROM `CouponSys`.`Coupon` WHERE `TYPE`='?'";
			PreparedStatement stmt = con.prepareStatement(getcoupontype);
			// stmt.setObject(1, Coupon.);
			ResultSet rs = stmt.executeQuery(getcoupontype);

			stmt.setObject(1, Type);

			Set<Coupon> couponbytype = new HashSet<>();
			while (rs.next())
			{
				if (Type.equals(rs.getType()))
					;

				DB_Executer.returnConnection(con);
			}

		} catch (SQLException e)
		{

			e.printStackTrace();
			// WTF?!?!?!
		}
		return getCouponByType(null);

	}

	

	@Override
	public String getAmount(long ID ) throws SQLException
	{
		Connection con = DB_Executer.getConnection();
		String getAmount = "SELECT * FROM `CouponSys`.`Coupon` WHERE `ID`='?'";
		PreparedStatement stmt = con.prepareStatement(getAmount);
		stmt.setObject(1, ID);
		ResultSet rs = stmt.executeQuery(getAmount);
		rs.getInt("AMOUNT");
		
		return getAmount;
	}

	

	@Override
	public void checkIfDateExp() throws SQLException
	{
		Connection con = DB_Executer.getConnection(); // getting a connection
		Statement stmt = con.createStatement(); // create a statement
		ResultSet rs = stmt.executeQuery("select * from coupon");

		java.util.Date currentDate = new java.util.Date();

		while (rs.next())
		{
			long passedDate = DateAndTimeExecuter.calculateDaysBetween2Dates(
					currentDate, rs.getDate("END_DATE"));
			if (passedDate < 0)
			{
				PreparedStatement prepared_stmt = con
						.prepareStatement("UPDATE `coupon_project`.`coupon` SET `AVAILABILITY`='F' WHERE `ID`=?");
				prepared_stmt.setLong(1, rs.getLong("ID"));
				prepared_stmt.executeUpdate(); // this action sending new data
												// to to dataBase
			}

		}
		DB_Executer.returnConnection(con); // this function sending the
											// connection back to connectionPool
	}

	

}
