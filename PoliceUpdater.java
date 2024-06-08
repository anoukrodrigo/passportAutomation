package emigrationDept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PoliceUpdater {
	
	  public boolean updatePoliceStatus(String nic, String policeUpdate) {
	        boolean success = false;
	        try {
	        
	             Connection con = databaseConnection.getConnection();
	            PreparedStatement ps = con.prepareStatement("UPDATE application SET policeStatus = ? WHERE nic = ?");
	             ps.setString(1, policeUpdate);
	             ps.setString(2, nic);
	             int rowsAffected = ps.executeUpdate();
	            if (rowsAffected > 0) {
	                success = true;
	             }
	             ps.close();
	             con.close();
	         
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle exceptions, if any
	        }
	        return success;
	    }
	
	
	

}
