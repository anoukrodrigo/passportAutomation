package emigrationDept;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
	
	  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pas";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "hybridWolf@131902";

	    // Method to get a connection to the database
	    public static Connection getConnection() {
	        Connection connection = null;
	        try {
	            // Establishing a connection to the database
	            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	        } catch (SQLException e) {
	            System.err.println("Failed to connect to the database!");
	            e.printStackTrace();
	        }
	        return connection;
	    }
	
	
	
	

}
