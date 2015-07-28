package com.CouponSys.Common;

public class CouponSysExceptions extends Exception
{
	private int errorCode;
	
	public CouponSysExceptions(String msg,int errorCode) 
	{
		super(msg);
		this.errorCode = errorCode;
	}

	public CouponSysExceptions(String msg) 
	{
		super(msg);
	}
	
	@Override
	public String toString() {
		return super.getMessage() + " **Error " + errorCode + " ** ";
	}
}
