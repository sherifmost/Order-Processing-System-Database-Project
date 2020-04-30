package model;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private Connection connection;
	
	public void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/BOOKSTORE";
			connection = DriverManager.getConnection(url, "root", "password");
			System.out.println("Connected with DB!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Disconnected");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// we can change the return type to boolean to return false in case of existing email
	public void signUpNewUser(User user) {
		if (isDuplicateUser(user.getUserName(), user.getEmail())) {
			System.out.println("This username or email already exists!");
			return;
		}
		try {
			Statement statement = connection.createStatement();
			String hashedPassword = "";
			try {
				hashedPassword = PasswordHashing.getSHA(user.getPassword());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			String operation = "INSERT INTO USER VALUES('" + user.getUserName() + "', '" + user.getPassword() + "', '" +
					hashedPassword + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" +
					user.getEmail() + "', '" + user.getPhone() + "', '" + user.getShippingAddress() + "', '0')";
			statement.execute(operation);
			statement.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void signIn(String username, String password) {
		String hashedPassword = "";
		try {
			hashedPassword = PasswordHashing.getSHA(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT passwordHash FROM USER where username = '" + username + "'");
			if (rs.next()) {
				String tempPass = rs.getString("passwordHash");
				if (tempPass.compareTo(hashedPassword) == 0) {
					System.out.println("welcome " + username + " !");
				}else {
					System.out.println("wrong password! Try again.");
				}
			} else {
				System.out.println("This Username is not registered!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean isDuplicateUser(String userName, String email) {
		boolean isDuplicate = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT username, email FROM USER");
			while(rs.next()) {
				String tempUserName = rs.getString("username");
				String tempEmail = rs.getString("email");
				if (userName.compareTo(tempUserName) == 0 || email.compareTo(tempEmail) == 0){
					isDuplicate = true;
					break;
				}
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isDuplicate;
	}
}
