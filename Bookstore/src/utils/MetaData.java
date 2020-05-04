package utils;

//This class contains all the database meta data needed
public class MetaData {
	// Publisher table
	public static String PUBLISHER_NAME = "PublisherName";
	public static String PUBLISHER_TELEPHONE = "Telephone";
	public static String PUBLISHER_ADDRESS = "Address";
	// Book table
	public static String BOOK_ISBN = "ISBN";
	public static String BOOK_TITLE = "Title";
	public static String BOOK_PUBLISHER = "PublisherName";
	public static String BOOK_PRICE = "Price";
	public static String BOOK_CATEGORY = "Category";
	public static String BOOK_THRESHOLD = "Threshold";
	public static String BOOK_COPIES = "Copies";
	// Book orders
	public static String ORDERS_ISBN = "ISBN";
	public static String ORDERS_TOTAL = "TotalOrdered";
	// Book Author
	public static String AUTHOR_ISBN = "ISBN";
	public static String AUTHOR_FNAME = "Fname";
	public static String AUTHOR_LNAME = "Lname";
	// User table
	public static String USER_NAME = "UserName";
	public static String USER_PASSWORDHASH = "PasswordHash";
	public static String USER_FNAME = "Fname";
	public static String USER_LNAME = "Lname";
	public static String USER_EMAIL = "Email";
	public static String USER_PHONE = "Phone";
	public static String USER_SHIPPINGADDRESS = "ShippingAddress";
	public static String USER_ISMANAGER = "IsManager";
}
