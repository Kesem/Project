package com.CouponSys.beans;
import org.joda.time.DateTime;


public class Coupon
{
	// variables 
	private long id;
	private String title;
	private DateTime startDate = null;
	private DateTime endDate = null;
	private int amount = 0;
	private CouponType type;
	private String message;
	private double price = 00.00;
	private String image;
	private boolean Valid = true;
	
	
	
	
//	Constructor 
	
	public Coupon(long id, String title, DateTime startDate, DateTime endDate, int amount,
			CouponType type, double price, boolean active)
	{
		
		
		
		this.startDate = (startDate = new DateTime());
		this.endDate = (endDate = startDate.plusMonths(1));
		
		if (endDate.isBefore(startDate))
		{
			System.out.println("**The end date cant be before the Start date!**");
		}
		
		
		this.id = id;
		this.title = title;
		this.amount = amount;
		this.type = type;
		this.price = price;
		this.setValid(true);
	}
	
	
 // Getters
	
	public long getID()
	{
		return id;
	}
	public String getTitle()
	{
		return title;
	}
	
	public DateTime getStartDate()
	{
		return startDate;
	}
	public DateTime getEndDate()
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
	
	//  Setters
	
	public void setID(long id)
	{
		this.id = id;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setStartDate(DateTime startDate)
	{
		this.startDate = startDate;
	}
	
	
	public void setEndDate(DateTime endDate)
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
	
	//TODO setImage()
	
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
	return ("This coupon is for " + type + "and it price is: " + price + " the Start date is " + 
	startDate.toString("dd-MMM-yyyy")) + " and will end on " + endDate.toString("dd-MMM-yyyy");
	}
	
	
	
}