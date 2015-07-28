package com.CouponSys.Common;


public class DateAndTimeExceptions extends Exception 
{
		private int errorCode;
		
		public DateAndTimeExceptions(String massage,int errorCode) 
		{
			super(massage);
			this.errorCode = errorCode;
		}

		public DateAndTimeExceptions(String massage) 
		{
			super(massage);
		}
		
		@Override
		public String toString() {
			return super.getMessage() + "** Error " + errorCode + " **";
		}
		
}
