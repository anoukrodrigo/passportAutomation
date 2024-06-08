package emigrationDept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class forgetPassNew {
	
	  public static boolean verifyNICAndUsername(String nic, String username) {
	        String sql = "SELECT COUNT(*) FROM userInfor WHERE nic = ? AND username = ?";
	        
	        try (Connection con = databaseConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, nic);
	            ps.setString(2, username);
	            
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    int count = rs.getInt(1);
	                    return count > 0; // If count > 0, NIC and username exist
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	
	
	
	

}
