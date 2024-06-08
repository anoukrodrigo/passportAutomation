package emigrationDept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	
	 public static String[] getUserInfo(String nic, String password) {
	        String sql = "SELECT ua.applicationStatus ,ua.fullName FROM userInfor ui INNER JOIN application ua ON ui.nic = ua.nic WHERE ui.nic = ? AND ui.password = ?";
	        String hashedPassword = UserStatushashPassword.verifyUserDetails(nic, password);

	        if (hashedPassword != null) {
	            try (Connection con = databaseConnection.getConnection();
	                 PreparedStatement ps = con.prepareStatement(sql)) {
	                ps.setString(1, nic);
	                ps.setString(2, hashedPassword);

	                try (ResultSet rs = ps.executeQuery()) {
	                    if (rs.next()) {
	                        String applicationStatus = rs.getString("applicationStatus");
	                        String fullName = rs.getString("fullName");
	                        
	                      
	                        
	                        return new String[] { applicationStatus, fullName };
	                        
	                        
	                       
	                        
	                    }
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            
	            
	        }
	        return null; // Return null if no user found or if an error occurred
	    }
	

}
