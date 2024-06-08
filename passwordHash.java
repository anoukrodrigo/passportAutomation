package emigrationDept;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;
import javax.swing.*;

import javax.swing.JOptionPane;

public class passwordHash {
	 
	 private static final int MAX_FAILED_ATTEMPTS = 5;
	    private static final long LOCKOUT_DURATION_MILLIS = 15 * 60 * 1000; // 15 minutes in milliseconds
	    private static final long DELAY_AFTER_WRONG_LOGIN_MILLIS = 15000; // 15 minutes in milliseconds

	    
	

	
	  public static boolean registerUser(String fullname, String nic, String username, String email, String password) {
	        // Hash and salt the password
	        String salt = generateSalt();
	        int iterations = 10000; // Adjust the number of iterations as needed
	        String hashedPassword = hashPassword(password, salt,iterations );

	        if (hashedPassword != null && salt != null) {
	            // Store the user details in the database
	        	
	            return storeUserDetails(fullname, nic, username, email, hashedPassword, salt);
	        } else {
	            
	            return false;
	        }
	    }
           
	 
	  
	    public static boolean verifyUser(String username, String password, int iterations) {
	        // Retrieve the stored hashed password and salt from the database
	    	  simulateDelay(5); // 2 seconds delay
	    	  
	    	  if (isAccountLocked(username)) {
	            JOptionPane.showMessageDialog(null,"Account is locked due to too many failed attempts. Please try again later.");
	              return false;
	          }
	        String[] storedCredentials = getPasswordAndSaltFromDatabase(username);

	        if (storedCredentials != null) {
	            String storedHashedPassword = storedCredentials[0];
	            String storedSalt = storedCredentials[1];
	          
	            // Hash the input password with the retrieved salt
	            String hashedPasswordString = hashPassword(password, storedSalt, iterations);
	            boolean isVerified = storedHashedPassword.equals(hashedPasswordString);
	            
	            if (isVerified) {
	                // Reset failed attempts counter upon successful login
	                resetFailedAttempts(username);
	               
	            } else {
	                // Increment failed attempts counter
	                incrementFailedAttempts(username);
	                //JOptionPane.showMessageDialog(null,"Invalid username or password. Please try again.");

	                // Add delay after a wrong password attempt
	                addDelayAfterWrongLogin();
	                
	              
	            }
	            // Compare the hashed input password with the stored hashed password
	            return storedHashedPassword.equals(hashedPasswordString);
	           
	        } else {
	            // Username not found in the database
	          
	            return false;
	        }
	        
	        
	      
	        
	        
	      
	    }
	    
	  
	    
	  

	    private static String hashPassword(String password, String salt, int iterations) {
	        try {
	            if (password == null || salt == null) {
	                System.err.println("Password or salt is null.");
	                return null;
	            }

	            // Concatenate password and salt
	            String saltedPassword = password + salt;
	            simulateDelay(5); // 2 seconds delay
	            // Hash the salted password
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            byte[] hashedPassword = md.digest(saltedPassword.getBytes());

	            // Return the hashed password
	            String hashedPasswordString = Base64.getEncoder().encodeToString(hashedPassword);
	           
	            return hashedPasswordString;
	        } catch (NoSuchAlgorithmException e) {
	            System.err.println("Hashing algorithm not found.");
	            e.printStackTrace();
	            return null;
	        }
	    }


	    private static String generateSalt() {
	    	  simulateDelay(5); // 2 seconds delay
	        byte[] salt = new byte[16];
	        new SecureRandom().nextBytes(salt);
	        return Base64.getEncoder().encodeToString(salt);
	    }

	    private static boolean storeUserDetails(String fullname, String nic, String username, String email, String hashedPassword, String salt) {
	        try (Connection connection = databaseConnection.getConnection()) {
	            if (!userExists(connection, nic)) {
	                String query = "INSERT INTO userInfor (fullname, nic, username, email, password, salt) VALUES (?, ?, ?, ?, ?, ?)";
	                try (PreparedStatement statement = connection.prepareStatement(query)) {
	                    statement.setString(1, fullname);
	                    statement.setString(2, nic);
	                    statement.setString(3, username);
	                    statement.setString(4, email);
	                    statement.setString(5, hashedPassword);
	                    statement.setString(6, salt);

	                    int rowsAffected = statement.executeUpdate();
	                    return rowsAffected > 0;
	                }
	            } else {
	                return false;
	            }
	        } catch (SQLException e) {
	            System.err.println("Error storing user details in the database: " + e.getMessage());
	            e.printStackTrace();
	            return false;
	        }
	    }


	    private static String[] getPasswordAndSaltFromDatabase(String username) {
	    	  simulateDelay(5); // 2 seconds delay
	        try (Connection connection = databaseConnection.getConnection()) {
	        	
	            String query = "SELECT password, salt FROM userInfor WHERE username = ?";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	                statement.setString(1, username);
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
	            System.err.println("Error retrieving user details from the database: " + e.getMessage());
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	
	
	    private static boolean userExists(Connection connection, String nic) {
	        String sql = "SELECT COUNT(*) AS count FROM userInfor WHERE nic = ?";
	        ResultSet resultSet = null;
	        try {
	        	 connection = databaseConnection.getConnection();
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, nic);

	            try {
	                resultSet = statement.executeQuery();
	                if (resultSet.next()) {
	                    int count = resultSet.getInt("count");
	                    return count > 0;
	                }
	            } finally {
	                // Close the PreparedStatement
	                if (statement != null) {
	                    statement.close();
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close the ResultSet
	            if (resultSet != null) {
	                try {
	                    resultSet.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        return false;
	    }

	
	    private static void simulateDelay(int seconds) {
	        try {
	            TimeUnit.SECONDS.sleep(seconds);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }
	
	    public static boolean isAccountLocked(String username) {
	        boolean locked = false;
	        Timestamp lockoutTime = null;
	        
	        unlockAccountIfTimeExpired(username);

	        try (Connection connection = databaseConnection.getConnection()) {
	            String query = "SELECT locked, lockout_time FROM userInfor WHERE username = ?";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	                statement.setString(1, username);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        locked = resultSet.getBoolean("locked");
	                        lockoutTime = resultSet.getTimestamp("lockout_time");
	                    }
	                }
	            }
	            
	            // Check if the account is locked based on the retrieved information
	            if (locked && lockoutTime != null) {
	            	  System.out.println("Lockout time retrieved: " + lockoutTime.toString());
	                // Calculate the time elapsed since the account was locked
	            	long lockoutDurationMillis = System.currentTimeMillis() - lockoutTime.getTime();
	                if (lockoutDurationMillis >= LOCKOUT_DURATION_MILLIS) {
	                    // Account is still locked
	                    return true;
	                } else {
                            
	                	System.out.println("Lockout time is null."); // Debugging message
	                	resetFailedAttempts(username);
	                	
	                	//scheduleUnlock(username); ///////////////////////////////////////////////////////////////////////////////////////////
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle the exception
	        }
	        
	        return false;
	    }
	    
	    private static void incrementFailedAttempts(String username) {
	    	Connection connection = databaseConnection.getConnection();
	        String sql = "UPDATE userInfor SET failed_attempts = failed_attempts + 1 WHERE username = ?";// ,  INSERT INTO userInfor (username, failed_attempts) VALUES (?, 1) ON DUPLICATE KEY UPDATE   failed_attempts = failed_attempts + 1;
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, username);
	            int rowsAffected = statement.executeUpdate();
	            if (rowsAffected > 0) {
	                // Check if failed attempts exceed the threshold to lock the account
	                if (getFailedAttempts(username) >= MAX_FAILED_ATTEMPTS) {
	                    lockAccount(username);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private static void resetFailedAttempts(String username) {
	        // SQL query to update failed_attempts, locked, and lockout_time columns
	        String sql = "UPDATE userInfor SET failed_attempts = 0, locked = 0, lockout_time = NULL WHERE username = ? ";//,  INSERT INTO userInfor (username, failed_attempts, locked, lockout_time) VALUES (?, 0, 0, NULL) ON DUPLICATE KEY UPDATE   failed_attempts = VALUES(failed_attempts),  locked = VALUES(locked), lockout_time = VALUES(lockout_time);
	        
	        try (Connection connection = databaseConnection.getConnection(); // Assuming you have a method to get the database connection
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            // Set the username parameter
	            statement.setString(1, username);
	            
	            // Execute the update query
	            statement.executeUpdate();
	            
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    private static  void addDelayAfterWrongLogin() {
	        try {
	            Thread.sleep(DELAY_AFTER_WRONG_LOGIN_MILLIS); // Sleep for the specified duration
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
                
	    
	 

	
	  
	    
	    
	    public static void unlockAccount(String username) {
	        // Reset the locked status and lockout_time in the database
	        String query = "UPDATE userInfor SET locked = 0, lockout_time = NULL WHERE username = ?";//  ,  INSERT INTO userInfor (username, locked, lockout_time) VALUES (?, 0, NULL) ON DUPLICATE KEY UPDATE   locked = VALUES(locked), lockout_time = VALUES(lockout_time);
	        Connection connection = null;
	        PreparedStatement statement = null;

	        try {
	            connection = databaseConnection.getConnection();
	            statement = connection.prepareStatement(query);
	            statement.setString(1, username);
	            statement.executeUpdate();
	            JOptionPane.showMessageDialog(null,"Account unlocked successfully.");

	            // Create a timer to re-enable locking after the lockout duration
	            Timer timer = new Timer((int) LOCKOUT_DURATION_MILLIS, new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    lockAccount(username);
	                }
	            });
	            timer.setRepeats(false); // Only run the timer once
	            timer.start();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            System.out.println("Failed to unlock account.");
	        } finally {
	            // Close resources
	            try {
	                if (statement != null) {
	                    statement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	
	    
	    private static void lockAccount(String username) {
	        // Set locked status and lockout time in the database
	        String query = "UPDATE userInfor SET locked = 1, lockout_time = ? WHERE username = ?";  //  ,  INSERT INTO userInfor (username, locked, lockout_time) VALUES (?, 1, ?) ON DUPLICATE KEY UPDATE  locked = VALUES(locked), lockout_time = VALUES(lockout_time);
	        Connection connection = null;
	        PreparedStatement statement = null;

	        try {
	            connection = databaseConnection.getConnection();
	            statement = connection.prepareStatement(query);
	            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
	            statement.setString(2, username);
	            statement.executeUpdate();
	            JOptionPane.showMessageDialog(null,"Account locked after lockout duration.");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            System.out.println("Failed to lock account.");
	        } finally {
	            // Close resources
	            try {
	                if (statement != null) {
	                    statement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }

	    private static int getFailedAttempts(String username) {
	        int failedAttempts = 0;
	        try (Connection connection = databaseConnection.getConnection()) {
	            String query = "SELECT failed_attempts FROM userInfor WHERE username = ?";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	                statement.setString(1, username);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        failedAttempts = resultSet.getInt("failed_attempts");
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return failedAttempts;
	    }
	  public static void scheduleUnlock(String username) {
	        // Create a ScheduledExecutorService with a single thread
	        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	        // Schedule a task to unlock the account after the lockout duration
	        executor.schedule(() -> {
	            // Automatically unlock the account after lockout duration
	            unlockAccount(username);//////////////////////////////////////////////////////////////////////////////////////////////////////
	            
	        }, LOCKOUT_DURATION_MILLIS, TimeUnit.MILLISECONDS);

	        // Shutdown the executor after all tasks have completed
	        executor.shutdown();
	    }
	    
	  
	    
	  private static void unlockAccountIfTimeExpired( String username) {
		 try( Connection connection = databaseConnection.getConnection()){
		    String query = "SELECT locked, lockout_time FROM userInfor WHERE username = ?";
		    try (PreparedStatement statement = connection.prepareStatement(query)) {
		        statement.setString(1, username);
		        try (ResultSet resultSet = statement.executeQuery()) {
		            if (resultSet.next()) {
		                boolean locked = resultSet.getBoolean("locked");
		                Timestamp lockoutTime = resultSet.getTimestamp("lockout_time");
		                if (locked && lockoutTime != null) {
		                    long lockoutDurationMillis = System.currentTimeMillis() - lockoutTime.getTime();
		                    if (lockoutDurationMillis >= LOCKOUT_DURATION_MILLIS) {
		                    	resetFailedAttempts(username); // Reset lock status if time expired
		                    }
		                }
		            }
		        }
		    }
		 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	/* // Method to hash and salt a password
    public static String hashPassword(String username,String password) {
    	try {
            // Generate a random salt
            String salt = generateSalt();

            // Concatenate password and salt
            String saltedPassword = password + salt;

            // Hash the salted password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(saltedPassword.getBytes());

            // Convert hashed password and salt to Base64
            String hashedPasswordWithSalt = Base64.getEncoder().encodeToString(hashedPassword) + ":" + salt;

            // Store the hashed password with salt in the database
            storePasswordInDatabase(username, hashedPasswordWithSalt);

            // Return the hashed password with salt
            return hashedPasswordWithSalt;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to generate a random salt
    private static String generateSalt() {
        byte[] salt = new byte[16];
        new java.security.SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Method to store hashed and salted password in the database
    public static boolean storePasswordInDatabase(String username, String hashedPasswordWithSalt) {
        try (Connection connection = databaseConnection.getConnection()) {
            String query = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, hashedPasswordWithSalt);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Password stored successfully!");
                    return true; // Successfully stored
                } else {
                    System.err.println("Failed to store password in database!");
                    return false; // Failed to store
                }
            }
        } catch (SQLException e) {
            System.err.println("Error storing password in database!");
            e.printStackTrace();
            return false; // Failed to store due to exception
        }
    }


    // Method to verify the password
    public static boolean verifyPassword(String username, String password) {
        try (Connection connection = databaseConnection.getConnection()) {
            String query = "SELECT password_hash FROM users WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String storedHashedPasswordWithSalt = resultSet.getString("password_hash");
                        String[] parts = storedHashedPasswordWithSalt.split(":");
                        String storedHashedPassword = parts[0];
                        String storedSalt = parts[1];

                        // Hash the input password with the stored salt and compare
                        String hashedInputPassword = hashPassword(password + storedSalt).split(":")[0];
                        return hashedInputPassword.equals(storedHashedPassword);
                    } else {
                        // Username not found
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error verifying password!");
            e.printStackTrace();
            return false;
        }
    }*/
}
