package com.CouponSys.Common;

import java.text.*;
import java.util.*;

public class DateAndTimeExecuter 
{
	// step 1 (singleton)
	private static DateAndTimeExecuter dateAndTimeExecuter = new DateAndTimeExecuter();
	
	//public variables
	public static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	
	//private variables
	private int[] seperateDateByNum = new int[6];
	private String[] seperateDateByString = new String[seperateDateByNum.length];
	private final int[] DAY_IN_MONTH = {31,28,31,30,31,30,31,31,30,31,30,31}; 
	//Useful variables
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minutes;
	private int seconds;
	
	
	// step 2 - singleton
	private DateAndTimeExecuter()
	{
		
	}
	
	// step 3 (singleton) - getting initialized class
	public static DateAndTimeExecuter getDateAndTimeExecuter()
	{ 
		return dateAndTimeExecuter;
	}
	
	//this function is getting string number and return a date object after Checking it
	public Date getStringDateCheckItAndReturnDate(String date) throws DateAndTimeExceptions
	{
//		if (checkTheStringStructure(date))
//			throw new MyDateAndTimeExceptions("Structure inValid");
		//converting date string to integer numbers
			convertStringDateToInt(date);
		//order array value in variables
			day = seperateDateByNum[0];
			month = seperateDateByNum[1];
			year = seperateDateByNum[2];
			hour = seperateDateByNum[3];
			minutes = seperateDateByNum[4];
			seconds = seperateDateByNum[5];
		//Checking if date is valid - if not throw exception
		if (!isValidDate(year, month, day))
			throw new DateAndTimeExceptions("**Date Is invaild**");
		//Checking if date is valid - if not throw exception
		if (!isValidTime(hour, minutes, seconds))
			throw new DateAndTimeExceptions("**Time Is invaild**");
		//if not throw exception create a date object and return it to user
		Date temp_date = null;
		try {
				temp_date = FORMAT.parse(createNewSring(date));		
		} catch (ParseException e) {
				e.printStackTrace();
		}
		return temp_date;
	}
	
//	//step 1 - check the String Structure 
//	private boolean checkTheStringStructure(String date)
//	{
//		boolean bool = false;
//		String[] ePatterns = new String[6];
//		ePatterns[0] = "^([0-9]{2}+[\\s;.,:/-]+[0-9]{2}+[\\s;.,:/-]+[0-9]{4})$";
//		ePatterns[1] = "^([0-9]{2}+[\\s;.,:/-]+[0-9]{2}+[\\s;.,:/-]+[0-9]{2})$";
//		ePatterns[2] = "^([0-9]{2}+[\\s;.,:/-]+[0-9]{2}+[\\s;.,:/-]+[0-9]{4})+(\\s)+([0-9]{2}+:+[0-9]{2}+:+[0-9]{2})$";
//		ePatterns[3] = "^([0-9]{2}+[\\s;.,:/-]+[0-9]{2}+[\\s;.,:/-]+[0-9]{2})+(\\s)+([0-9]{2}+:+[0-9]{2}+:+[0-9]{2})$";
//		ePatterns[4] = "^([0-9]{2}+[\\s;.,:/-]+[0-9]{2}+[\\s;.,:/-]+[0-9]{4})+(\\s)+([0-9]{2}+:+[0-9]{2})$";
//		ePatterns[5] = "^([0-9]{2}+[\\s;.,:/-]+[0-9]{2}+[\\s;.,:/-]+[0-9]{2})+(\\s)+([0-9]{2}+:+[0-9]{2})$";
//				
//		for (int i = 0 ; i < ePatterns.length ; i++)
//		{
//			 java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePatterns[i]);
//			 java.util.regex.Matcher m = p.matcher(date);
//			 if (m.matches())
//			 {
//				 bool = true;
//				 break;
//			 }
//		}
//		
//		return bool;
//	}
	
	//step 2 - split date string and creat integer array (length=3) 0=day, 1=month,2=year
	private void convertStringDateToInt(String date)
	{
		initializeArraysWithNullOr0();
		seperateDateByString = date.split("[\\s;.,:/-]");
		for (int i = 0 ; i < seperateDateByString.length ; i++)
		{	
			seperateDateByNum[i] = new Integer(seperateDateByString[i]);
		}
	}
	
	private void initializeArraysWithNullOr0()
	{
		for (int i = 0 ; i < seperateDateByNum.length ; i++)
		{	
			seperateDateByNum[i] = 0;
		}
	}	
	
	
	//step 3 - check only if date is vaild
	private boolean isValidDate(int year, int month, int day)
	{
			if (year<=0 || year>9999)	
				return false;
			
			if (month<=0 || month>12)
				return false;
			
			if (isLeapYear(year) && month==2)
			{
				if (day<=0 || day>29)
				{
					return false;
				}
			}

			if (day<=0 || day>DAY_IN_MONTH[month-1])	
				return false;

		return true;
	}
	
	//return if leap year (=29 days in feb)
	private boolean isLeapYear(int year)
	{
		return (year%4==0 && year%100!=0 || year%400==0);
	}
	
	//step 4 - check only if Time is vaild
	private boolean isValidTime(int hour, int minute, int second)
	{

		if (hour > 23 || hour < 0)
			return false;
		
		if (minute > 59 || minute < 0)
			return false;
		
		if (second > 59 || second < 0)
			return false;
		
		return true;
	}
	
	//create a new string - to mach the format
	public String createNewSring(String date)
	{
		//turn int to string
		String D = day + "";
		String M = month + "";
		String Y = year + "";
		String h = hour + "";
		String m = minutes + "";
		String s = seconds + "";
		
		// adding 0 to hour if digit small then 9
		if (day <= 9) D = "0" + day;
		if (month <= 9) M = "0" + month;
		if (year <= 9) Y = "0" + year;
		if (hour <= 9) h = "0" + hour;
		if (minutes <= 9) m = "0" + minutes;
		if (seconds <= 9) s = "0" + seconds;
		
		return D+"/"+M+"/"+Y+" "+h+":"+m+":"+s;
	}
	
	
	//final static functions
	public static double calculateSecondsBetween2Dates(Date dateStart, Date dateStop)
	{
		double diff = dateStop.getTime() - dateStart.getTime();
		return diff/1000;
	}
	
	public static double calculateMinutesBetween2Dates(Date dateStart, Date dateStop)
	{
		double diff = dateStop.getTime() - dateStart.getTime();
		return diff / (1000 * 60);
	}
	
	public static double calculateHoursBetween2Dates(Date dateStart, Date dateStop)
	{
		double diff = dateStop.getTime() - dateStart.getTime();
		return diff / (1000 * 60 * 60);
	}
	
	public static long calculateDaysBetween2Dates(Date dateStart, Date dateStop)
	{
		long diff = dateStop.getTime() - dateStart.getTime();
		return diff / (1000 * 60 * 60 * 24);
	}
	
	public static String getNiceDateToPrint(Date date)
	{
		return FORMAT.format(date);
	}
}
