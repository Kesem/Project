package connectionPool;

import java.sql.*;

// this class is Designed for getting and returning connection to connection pool 
public class DB_Executer 
{
	public static Connection getConnection()
	{
		try {
			return DB_ConnectionPool.getInstance().getConnectionFromPool();
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}
  
	public static void returnConnection(Connection connection) 
	{
		try {
			DB_ConnectionPool.getInstance().returnConnectionToPool(connection);
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}
	
	public static void shutdownConnections()
	{
		try {
			DB_ConnectionPool.getInstance().closeAllConnections();
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}
		
}
