package com.CouponSys.Common;

public class CouponSysRunTimeExceptions extends Exception
{
	private int errorCode;
	
	public CouponSysRunTimeExceptions(String msg,int errorCode) 
	{
		super(msg);
		this.errorCode = errorCode;
	}

	public CouponSysRunTimeExceptions(String msg) 
	{
		super(msg);
	}
	
	@Override
	public String toString() {
		return super.getMessage() + " **Error " + errorCode + " ** ";
	}
}
