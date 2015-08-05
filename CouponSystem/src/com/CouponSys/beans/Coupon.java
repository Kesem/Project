package com.CouponSys.beans;

import java.sql.Date;

import org.joda.time.DateTime;

import com.CouponSys.Common.CouponSysExceptions;
import com.CouponSys.Common.DateAndTimeExceptions;
import com.CouponSys.Common.DateAndTimeExecuter;

public class Coupon
{
	// variables
	private long coup_ID;
	private long comp_ID;
	private String title;
	private java.util.Date startDate = null;
	private java.util.Date endDate = null;
	private int amount = 0;
	private CouponType type;
	private String message;
	private double price = 00.00;
	private String image;
	private boolean Valid = true;

	// Constructor
	public Coupon(long CouponID, String title, String startDate,
			String endDate, int amount, CouponType type, String message,
			double price, String image) throws CouponSysExceptions,
			DateAndTimeExceptions
	{
		// Check Dates entries
		java.util.Date start_Date = DateAndTimeExecuter
				.getDateAndTimeExecuter().getStringDateCheckItAndReturnDate(
						startDate);
		java.util.Date end_Date = DateAndTimeExecuter.getDateAndTimeExecuter()
				.getStringDateCheckItAndReturnDate(endDate);

		// Check if startDate is earlier then endDate
		if (DateAndTimeExecuter
				.calculateDaysBetween2Dates(start_Date, end_Date) < 0)
			throw new DateAndTimeExceptions(
					"Error! startDate is earlier then endDate");
		this.coup_ID = CouponID;
		this.startDate = start_Date;
		this.endDate = end_Date;
		this.title = title;
		setAmount(amount);
		this.type = type;
		this.message = message;
		setPrice(price);
		this.image = image;
	}

	public Coupon(long CouponID, long CompanyID, String title,
			String startDate, String endDate, int amount, CouponType type,
			String message, double price, String image)
			throws CouponSysExceptions, DateAndTimeExceptions
	{
		// Check Dates entries
		java.util.Date start_Date = DateAndTimeExecuter
				.getDateAndTimeExecuter().getStringDateCheckItAndReturnDate(
						startDate);
		java.util.Date end_Date = DateAndTimeExecuter.getDateAndTimeExecuter()
				.getStringDateCheckItAndReturnDate(endDate);

		// Check if startDate is earlier then endDate
		if (DateAndTimeExecuter
				.calculateDaysBetween2Dates(start_Date, end_Date) < 0)
			throw new DateAndTimeExceptions(
					"Error! startDate is earlier then endDate");
		this.coup_ID = CouponID;
		this.comp_ID = CompanyID;
		this.startDate = start_Date;
		this.endDate = end_Date;
		this.title = title;
		setAmount(amount);
		this.type = type;
		this.message = message;
		setPrice(price);
		this.image = image;

	}

	// N.2 - will be used to pull coupon data from DB,
	// to create coupons that already have data in DB
	public Coupon(long CouponID, String title, Date startDate, Date endDate,
			int amount, CouponType type, String message, double price,
			String image)
	{
		this.coup_ID = CouponID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
		setAmount(amount);
		this.type = type;
		this.message = message;
		setPrice(price);
		this.image = image;
	}

	public Coupon(long id, String title, DateTime startDate2,
			DateTime endDate2, int amount, CouponType type, double price,
			boolean active)
	{
		this.coup_ID = id;
		this.title = title;
		this.startDate = startDate2;
		this.endDate = endDate2;
		this.amount = amount;
		this.type = type;
		this.price = price;
		this.Valid = active;
		
		
		
	}

	public long getID()
	{
		return coup_ID;
	}

	public String getTitle()
	{
		return title;
	}

	public java.util.Date getStartDate()
	{
		return startDate;
	}

	public java.util.Date getEndDate()
	{
		return endDate;
	}

	public int getAmount()
	{
		return amount;
	}

	public CouponType getType()
	{
		return type;
	}

	public String getMessage()
	{
		return message;
	}

	public double getPrice()
	{
		return price;
	}

	public String getImage()
	{
		return image;
	}

	// Setters

	public void setID(long id)
	{
		this.coup_ID = id;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public void setCouponType(CouponType type)
	{
		this.type = type;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public void setType(CouponType type)
	{
		this.type = type;
	}

	public void setType(String type)
	{
		this.type = CouponType.valueOf(type);
	}

	// TODO setImage()

	public boolean isValid()
	{
		if (this.Valid == true)
		{
			return true;
		}
		return false;
	}

	public void setValid(boolean active)
	{
		this.Valid = active;
	}

	@Override
	public String toString()
	{
		return ("This coupon is for " + type + "and it price is: " + price
				+ " the Start date is " + startDate.toString())
				+ " and will end on " + endDate.toString();
	}

}