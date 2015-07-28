package com.CouponSys.beans;

import com.CouponSys.Common.CouponSysExceptions;

public class CommonDetails
{
	// Variables

	private long id;
	private String name;
	private String password;
	private String email;

	// Constructor for creating a company

	public CommonDetails(long id, String compName, String password, String email)
			throws CouponSysExceptions
	{
		this.id = id;
		this.name = compName;
		this.password = password;
		isValidEmailAddress(email);
	}

	// Getters & Setters

	// return ID
	public long getId()
	{
		return id;
	}

	// return Name
	public String getName()
	{
		return name;
	}

	// set Name
	public void setName(String compName)
	{
		this.name = compName;
	}

	// return the password
	public String getPassword()
	{
		return password;
	}

	// set password
	public void setPassword(String password)
	{
		this.password = password;
	}

	// return email address
	public String getEmail()
	{
		return email;
	}

	// set email address.
	public void setEmail(String email) throws CouponSysExceptions
	{
		isValidEmailAddress(email);
	}

	// toString method.
	public String toString()
	{
		return "id=" + id + " Name=" + name + ", password=" + password
				+ ", email=" + email + ".";
	}

	// Checking if email is valid, if not return exception
	private void isValidEmailAddress(String email) throws CouponSysExceptions
	{
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);

		if (m.matches())
		{
			this.email = email;
		} else
		{
			throw new CouponSysExceptions("Email is invaildm, please try again");
		}
	}

}
