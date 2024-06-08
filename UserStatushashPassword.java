package emigrationDept;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.swing.JOptionPane;

public class UserStatushashPassword {
	

	public static String verifyUserDetails(String nic, String password) {
	    // Retrieve the stored hashed password and salt from the database
	    String[] storedCredentials = getPasswordAndSaltFromDatabase(nic);

	    if (storedCredentials != null) {
	        String storedHashedPassword = storedCredentials[0];
	        String storedSalt = storedCredentials[1];
	      
	        // Hash the input password with the retrieved salt
	        String hashedPasswordString = hashPassword(password, storedSalt);
	    
	        
	        // Compare the hashed input password with the stored hashed password
	        boolean verified = storedHashedPassword.equals(hashedPasswordString);
	        if (verified) {
	            // Return the hashed password if verification succeeds
	            JOptionPane.showMessageDialog(null,"Verification successful!");
	            return hashedPasswordString;
	        } else {
	        	   JOptionPane.showMessageDialog(null,"Verification unsuccessful!");
	            return null;
	        }
	    } else {
	    	  JOptionPane.showMessageDialog(null,"user not found!");
	        return null;
	    }
	}


    private static String hashPassword(String password, String salt) {
        try {
            if (password == null || salt == null) {
            	JOptionPane.showMessageDialog(null,"Password or salt is null.");
                return null;
            }

            // Concatenate password and salt
            String saltedPassword = password + salt;

            // Hash the salted password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(saltedPassword.getBytes());

            // Return the hashed password
            String hashedPasswordString = Base64.getEncoder().encodeToString(hashedPassword);
         
            return hashedPasswordString;
        } catch (NoSuchAlgorithmException e) {
          
            e.printStackTrace();
            return null;
        }
    }


    private static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

  

    private static String[] getPasswordAndSaltFromDatabase(String nic) {
        try (Connection connection = databaseConnection.getConnection()) {
            String query = "SELECT password, salt FROM userInfor WHERE nic = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nic);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String storedHashedPassword = resultSet.getString("password");
                        String storedSalt = resultSet.getString("salt");
                       
                        return new String[]{storedHashedPassword, storedSalt};
                    } else {
                        // Username not found
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
         
            e.printStackTrace();
            return null;
        }
    }



	
	

}
