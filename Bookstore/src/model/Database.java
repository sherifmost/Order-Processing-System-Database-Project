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
		User sudo = new User("root", "root", "root", "root@alexu.edu.eg",
				"password", "FOE - Shatby", "07775000");
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

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT passwordHash, IsManager FROM USER where username = '" + username + "'");
			if (rs.next()) {
				String tempPass = rs.getString("passwordHash");
				if (tempPass.compareTo(hashedPassword) == 0) {
					
				} else {
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
	
	public void addNewBook(Book book) {
		try {
			Statement statement = connection.createStatement();
			String operation = "INSERT INTO BOOK VALUES('" + book.getISBN() + "', '"
					+ book.getTitle() + "', '" + book.getPublisherName() + "', '" +
					book.getPublicationYear() + "', '" + book.getPrice() + "', '" + 
					book.getCategory() + "', '" + book.getThreshold() + "', '" +
					book.getCopies() + "')";
			statement.execute(operation);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
