package connectionPool;

import java.sql.*;
import java.util.*;

//this class is Creating and managing the connections 
public class DB_ConnectionPool
{
	// step 1 (singleton)
	private static DB_ConnectionPool jdbcConnectionPool = new DB_ConnectionPool();

	//Create an empty array list to hold the connections 
	private List<Connection> availableConnections = new ArrayList<>();
	private List<Connection> inUsedConnections = new ArrayList<>();

	
	// step 2 - singleton
	//this contractor is initialized start num of connections
	private DB_ConnectionPool()
	{
			for (int i=0 ; i < DB_Configuration.START_NUM_OF_CONNECTIONS; i++){
				createNewAvailableConnectionForPool();
			}
	}
	
	// step 3 (singleton) - getting initialized class
	public static DB_ConnectionPool getInstance()
	{ 
		return jdbcConnectionPool;
	}
	
	//checking if the Connection Pool reached to Max num of open connections
	private boolean isConnectionPoolReachedToMaxNumOfConnections()
	{
		return inUsedConnections.size() + availableConnections.size() == DB_Configuration.MAX_CONNECTIONS;
	}
	
	//checking if there is available connection in pool
	private boolean thereIsAvailableConnectionInPool()
	{
		return availableConnections.size() > 0;
	}
	
	//Creating a connection
	private void createNewAvailableConnectionForPool()
	{
		try
		{
			// step 1 - Load the driver. Use dynamic loading.
			Class.forName(DB_Configuration.DRIVER);
			// step 2 - Create a connection to the database.
			Connection con = DriverManager.getConnection(
					DB_Configuration.URL, 
					DB_Configuration.USER_NAME, 
					DB_Configuration.PASSWORD);
			// step 3 - insert connection in available list
			availableConnections.add(con);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
 
	//
	public synchronized Connection getConnectionFromPool() throws SQLException
	{
		//while all connections are busy wait
		while(!thereIsAvailableConnectionInPool() && isConnectionPoolReachedToMaxNumOfConnections())
		{		
			try 
			{
				this.wait();
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
		
		if(!thereIsAvailableConnectionInPool() && !isConnectionPoolReachedToMaxNumOfConnections())
		{		
			createNewAvailableConnectionForPool();
		}
		
		inUsedConnections.add(availableConnections.get(0));
		availableConnections.remove(0);
		return inUsedConnections.get(inUsedConnections.size()-1);			

	}
	
	// Return the connection to connection pool 
	public synchronized void returnConnectionToPool(Connection connection) throws SQLException
	{
		availableConnections.add(connection);
		inUsedConnections.remove(connection);
		this.notify();
	}

	@Override
	public String toString()
	{
		return "Connections in use: " + inUsedConnections.size() + " Available connections:" + availableConnections.size();
	}
	
	//Close and Remove all collections - Useful with the Shutdown() method in the CouponSystem main.
	public void closeAllConnections() throws SQLException
	{
		for (Connection inUsedConnection : inUsedConnections)
		{
				inUsedConnection.close();
				inUsedConnections.remove(inUsedConnection);
		}
		for (Connection availableConnection : availableConnections)
		{
			availableConnection.close();
			availableConnections.remove(availableConnection);
		}
	}
}

