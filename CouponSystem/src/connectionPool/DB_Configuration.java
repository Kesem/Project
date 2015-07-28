package connectionPool;

//this class is holding the DB_Configuration Of the Database.
public abstract class DB_Configuration
{	
	public static final String USER_NAME = "root";
	public static final String PASSWORD = ""; 
	public static final String URL = "jdbc:mysql://localhost:3306/CouponSystem";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final Integer MAX_CONNECTIONS = 10;
	public static final Integer START_NUM_OF_CONNECTIONS = 3;

}
