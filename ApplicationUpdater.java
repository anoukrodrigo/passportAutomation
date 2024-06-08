package emigrationDept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApplicationUpdater {
	
	 public boolean updateApplication(String nic, String applicationStatus) {
	        String sql = "UPDATE application SET applicationStatus = ? WHERE nic = ?";
	        
	        try (Connection con = databaseConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, applicationStatus);
	            ps.setString(2, nic);
	            
	            int rowsUpdated = ps.executeUpdate();
	            return rowsUpdated > 0; // Return true if rows were updated
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false; // Return false if an exception occurred
	        }
	    }
	
	
	
	
	
	

}
