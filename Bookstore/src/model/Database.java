package model;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import view.UpdateDataEvent;

public class Database {
	private Connection connection;
	private static User loggedInUser;

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

	// we can change the return type to boolean to return false in case of existing
	// email
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
			String manager = user.isManager() ? "'1'" : "'0'";
			String operation = "INSERT INTO USER VALUES('" + user.getUserName() + "', '" + hashedPassword + "', '"
					+ user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getEmail() + "', '"
					+ user.getPhone() + "', '" + user.getShippingAddress() + "'," + manager + ")";
			statement.execute(operation);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void signUpSuperUser() {
		User sudo = new User("root", "root", "root", "root@alexu.edu.eg", "password", "FOE - Shatby", "07775000");
		sudo.setManager();
		if (!isDuplicateUser(sudo.getUserName(), sudo.getEmail())) {
			signUpNewUser(sudo);
		}
	}

	public String signIn(String username, String password, boolean isManager) {
		String hashedPassword = "";
		String errorMsg = "NoError";
		try {
			hashedPassword = PasswordHashing.getSHA(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		ResultSet rs;
		try {
			Statement statement = connection.createStatement();
			// Made it select all to fill in the logged in user data
			rs = statement.executeQuery("SELECT * FROM USER where username = '" + username + "'");
			if (rs.next()) {
				String tempPass = rs.getString("passwordHash");
				if (tempPass.compareTo(hashedPassword) != 0) {
					return "wrong password! Try again.";
				}
				boolean storedAsManager = rs.getBoolean("IsManager");
				if (isManager && !storedAsManager) {
					return "You are not a manager!";
				}
			} else {
				return "This Username is not registered!";
			}
		} catch (SQLException e) {
			return e.getLocalizedMessage();
		}
		if (errorMsg.equals("NoError")) {
			try {
				fillInUser(rs.getString("userName"), rs.getString("Fname"), rs.getString("Lname"),
						rs.getString("email"), password, rs.getString("shippingAddress"), rs.getString("phone"),
						rs.getBoolean("isManager"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return errorMsg;
	}

	private boolean isDuplicateUser(String userName, String email) {
		boolean isDuplicate = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT username, email FROM USER WHERE username = " + "'" + userName
					+ "'" + " OR email = " + "'" + email + "'");
			while (rs.next()) {
				String tempUserName = rs.getString("username");
				String tempEmail = rs.getString("email");
				if (userName.compareTo(tempUserName) == 0 || email.compareTo(tempEmail) == 0) {
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

	// methods to add a publisher to the database
	public void addNewPublisher(Publisher publisher) {
		if (isDuplicatePublisher(publisher)) {
			System.out.println("This publisher already exists!");
			return;
		}
		try {
			Statement statement = connection.createStatement();
			String operation = "INSERT INTO PUBLISHER VALUES('" + publisher.getPublisherName() + "', '"
					+ publisher.getTelephone() + "', '" + publisher.getAddress() + "')";
			statement.execute(operation);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// This function is used to check if a publisher with the same name already
	// exists
	private boolean isDuplicatePublisher(Publisher publisher) {
		boolean isDuplicate = false;
		String name = publisher.getPublisherName();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT PublisherName FROM PUBLISHER WHERE PublisherName = " + "'" + name + "'");
			while (rs.next()) {
				String tempPublisherName = rs.getString("PublisherName");
				if (name.compareTo(tempPublisherName) == 0) {
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

	// This function is to update the user info
	public boolean updateUser(UpdateDataEvent e) {
		String userName = e.getUsername();
		String firstaName = e.getFirstName();
		String lastName = e.getLastName();
		String password = e.getPassword();
		String email = e.getEmail();
		String phone = e.getPhone();
		String shippingAddress = e.getShippingAddress();
		if ((!loggedInUser.getUserName().equals(userName) && userNameExists(userName))
				|| (!loggedInUser.getEmail().equals(email) && emailExists(email)))
			return false;
		else {
			// updating the user
			try {
				Statement statement = connection.createStatement();
				String hashedPassword = "";
				try {
					hashedPassword = PasswordHashing.getSHA(password);
				} catch (NoSuchAlgorithmException exception) {
					exception.printStackTrace();
				}
				System.out.println(phone);
				String operation = "UPDATE USER SET " + "userName = '" + userName + "', " + "passwordHash = '"
						+ hashedPassword + "', " + "Fname = '" + firstaName + "', " + "Lname = '" + lastName + "', "
						+ "Email = '" + email + "', " + "Phone = '" + phone + "', " + "shippingAddress = '"
						+ shippingAddress + "' WHERE USERNAME = '" + loggedInUser.getUserName() + "'";
				statement.execute(operation);
				statement.close();
				fillInUser(userName, firstaName, lastName, email, password, shippingAddress, phone,
						loggedInUser.isManager());
			} catch (SQLException exception) {
				exception.printStackTrace();
			}

			return true;
		}
	}

	// Function checks if email only is repeated
	private boolean emailExists(String email) {
		boolean isDuplicate = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT email FROM USER WHERE email = " + "'" + email + "'");
			while (rs.next()) {
				String tempEmail = rs.getString("email");
				if (email.compareTo(tempEmail) == 0) {
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

	public void addNewBook(Book book) {
		try {
			Statement statement = connection.createStatement();
			String operation = "INSERT INTO BOOK VALUES('" + book.getISBN() + "', '" + book.getTitle() + "', '"
					+ book.getPublisherName() + "', '" + book.getPublicationYear() + "', '" + book.getPrice() + "', '"
					+ book.getCategory() + "', '" + book.getThreshold() + "', '" + book.getCopies() + "')";
			statement.execute(operation);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean userNameExists(String userName) {
		boolean isDuplicate = false;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT username, email FROM USER WHERE username = " + "'" + userName + "'");
			while (rs.next()) {
				String tempUserName = rs.getString("username");
				if (userName.compareTo(tempUserName) == 0) {
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

	// Function to fill in the logged in user data
	private void fillInUser(String userName, String firstName, String lastName, String email, String password,
			String shippingAddress, String phone, boolean isManager) {
		setLoggedInUser(new User(userName, firstName, lastName, email, password, shippingAddress, phone, isManager));
	}

	public static User getLoggedInUser() {
		return loggedInUser;
	}

	public static void setLoggedInUser(User loggedInUser) {
		Database.loggedInUser = loggedInUser;
	}

	public void searchBooks(SearchQuery searchQuery) {
		try {
			Statement statement = connection.createStatement();
			StringBuilder sb = new StringBuilder();
			String mainOperation, publisherFilter, lowerPriceFilter, upperPriceFilter, fromYearFilter, toYearFilter;
			mainOperation = "SELECT * FROM BOOK WHERE TITLE LIKE '%" + searchQuery.getBookTitle() + "%'"
					+ " AND CATEGORY = '" + searchQuery.getCategory() + "'";
			publisherFilter = " AND PUBLISHERNAME LIKE '%" + searchQuery.getPublisherName() + "%'";
			lowerPriceFilter = " AND PRICE >= '" + searchQuery.getLowerPrice() + "'";
			upperPriceFilter = "AND PRICE <= '" + searchQuery.getUpperPrice() + "'";
			fromYearFilter = " AND PUBLICATIONYEAR >= '" + searchQuery.getFromYear() + "'";
			toYearFilter = " AND PUBLICATIONYEAR <= '" + searchQuery.getToYear() + "'";
			sb.append(mainOperation);
			if (searchQuery.getPublisherName().compareTo("none") != 0) {
				sb.append(publisherFilter);
			}
			if (searchQuery.getLowerPrice() != 0) {
				sb.append(lowerPriceFilter);
			}
			if (searchQuery.getUpperPrice() != 999999) {
				sb.append(upperPriceFilter);
			}
			if (searchQuery.getFromYear() != 1921) {
				sb.append(fromYearFilter);
			}
			if (searchQuery.getToYear() != 2020) {
				sb.append(toYearFilter);
			}

			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				System.out.println();
				System.out.print(rs.getString("ISBN") + "  ");
				System.out.print(rs.getString("title") + "  ");
				System.out.print(rs.getString("publisherName") + "  ");
				System.out.print(rs.getString("publicationYear") + "  ");
				System.out.print(rs.getString("price") + "  ");
				// next steps:
				// show results in a table
				// add an option to the user for book selection
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
