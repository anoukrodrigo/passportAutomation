package emigrationDept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class applicationDataSave {
	
	

	public boolean sendToDatabase(String fullname, String nic, String gender, String email, String address, String tele, String country) {
		
		databaseConnection c = new databaseConnection();
	
		
		  try (Connection connection = c.getConnection()) {
	            String query = "INSERT INTO application (fullname, nic, gender, email, address, tele, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	                statement.setString(1, fullname);
	                statement.setString(2, nic);
	                statement.setString(3, gender);
	                statement.setString(4, email);
	                statement.setString(5, address);
	                statement.setString(6, tele);
	                statement.setString(7, country);

	                int rowsAffected = statement.executeUpdate();
	                return rowsAffected > 0;
	            }
	        } catch (SQLException e) {
	            System.err.println("Error storing user details in the database: " + e.getMessage());
	            e.printStackTrace();
	            return false;
	        }
		
		
		
		
	}
	
	

}
