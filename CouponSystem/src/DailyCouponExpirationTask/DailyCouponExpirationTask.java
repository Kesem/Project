package DailyCouponExpirationTask;

import java.sql.SQLException;

import com.CouponSys.DAO.CouponDAO;

public class DailyCouponExpirationTask extends Thread
{
	
		
		private CouponDAO couponDAO = null;
		private boolean quit = false;
		
		public DailyCouponExpirationTask(CouponDAO couponDAO)
		{
			this.couponDAO = couponDAO;
		}
		
		
		public void run()
		{
			while(quit)
			{
				
				try {
					couponDAO.checkIfDateExp();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//The sleep func uses Milliseconds hence we need to multiply by 1000 milliseconds in a second, 
				//60 minutes in an hour, then 60 seconds in a minute and then 24 hours.	
				try {
					sleep(1000 * 60 * 60 * 24);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void stopTask()
		{
			quit = true;
			this.interrupt();
		}
}
