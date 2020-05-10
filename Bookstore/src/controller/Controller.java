package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

import model.Book;
import model.Cart;
import model.CreditCardChecker;
import model.Database;
import model.Order;
import model.Publisher;
import model.SearchQuery;
import model.User;
import view.BookEvent;
import view.LoginEvent;
import view.PromotionEvent;
import view.OrderEvent;
import view.PublisherEvent;
import view.SearchEvent;
import view.SignUpEvent;
import view.TableElement;
import view.UpdateDataEvent;

public class Controller {
	Database db;
	Publisher publisher;
	Book book;
	Order order;
	static CreditCardChecker checker;

	public Controller() {
		db = new Database();
		publisher = new Publisher();
		book = new Book();
		checker = new CreditCardChecker();
	}

	public void connectToDB() {
		db.createConnection();
		db.signUpSuperUser();
	}

	public void registerUser(SignUpEvent e) {
		User user = new User();
		user.setUserName(e.getUsername());
		user.setFirstName(e.getFirstName());
		user.setLastName(e.getLastName());
		user.setEmail(e.getEmail());
		user.setPassword(e.getPassword());
		user.setShippingAddress(e.getShippingAddress());
		user.setPhone(e.getPhone());
		db.signUpNewUser(user);
	}

	public boolean addPublisher(PublisherEvent e) {
		publisher.setAddress(e.getPublisherAddress());
		publisher.setPublisherName(e.getPubisherName());
		publisher.setTelephone(e.getPublihserTelephone());
		return db.addNewPublisher(publisher);
	}

	public void addBook(BookEvent e) {
		book.setCategory(e.getCategory());
		book.setCopies(e.getCopies());
		book.setISBN(e.getISBN());
		book.setPrice(e.getPrice());
		book.setPublicationYear(e.getPublicationYear());
		book.setPublisherName(e.getPublisherName());
		book.setThreshold(e.getThreshold());
		book.setTitle(e.getTitle());
		db.addNewBook(book);
	}

	public String logIn(LoginEvent e) {
		return db.signIn(e.getUsername(), e.getPassword(), e.isManager());
	}

	public ArrayList<BookEvent> searchBooks(SearchEvent e) {
		SearchQuery query = new SearchQuery();
		query.setBookTitle(e.getBookTitle());
		query.setCategory(e.getCategory());
		query.setFromYear(e.getFromYear());
		query.setLowerPrice(e.getLowerPrice());
		query.setPublisherName(e.getPublisherName());
		query.setToYear(e.getToYear());
		query.setUpperPrice(e.getUpperPrice());
		ArrayList<BookEvent> results = new ArrayList<>();
		for (Book book : db.searchBooks(query)) {
			results.add(new BookEvent(this, book.getISBN(), book.getTitle(), book.getPublisherName(),
					book.getPublicationYear(), book.getPrice(), book.getCategory(), book.getThreshold(),
					book.getCopies()));
		}
		return results;
	}

	public ArrayList<OrderEvent> searchOrders() {
		ArrayList<OrderEvent> results = new ArrayList<>();
		ArrayList<Order> orderResults = db.searchOrders();
		HashMap<Integer, String> title = db.findOrdersBookTitles();
		for (Order order : orderResults) {
			results.add(new OrderEvent(this, order.getISBN(), order.getQuantity(), title.get(order.getISBN())));
		}
		return results;
	}

	public void placeOrder(OrderEvent e) {
		db.placeOrder(new Order(e.getISBN(), e.getQuantity()));
	}

	public boolean updateUserData(UpdateDataEvent e) {
		User user = new User();
		user.setUserName(e.getUsername());
		user.setFirstName(e.getFirstName());
		user.setLastName(e.getLastName());
		user.setPassword(e.getPassword());
		user.setEmail(e.getEmail());
		user.setPhone(e.getPhone());
		user.setShippingAddress(e.getShippingAddress());
		return db.updateUser(user);
	}

	public void addBookToCart(BookEvent e) {
		Book book = new Book();
		book.setISBN(e.getISBN());
		book.setCategory(e.getCategory());
		book.setCopies(e.getCopies());
		book.setPrice(e.getPrice());
		book.setPublisherName(e.getPublisherName());
		book.setTitle(e.getTitle());
		db.addBookToCart(book, e.getQuantity());
	}

	public void clearCart() {
		Database.getLoggedInUser().setCart(new Cart());
	}

	public String checkout() {
		return db.checkout();
	}

	public boolean promoteUser(PromotionEvent e) {
		return db.promoteUser(e.getUserName());
	}

	public boolean isManager() {
		return db.isManager();
	}

	public void confirmOrders(ArrayList<Integer> orders) {
		db.confirmOrders(orders);
	}

	public static Cart getCart() {
		return Database.getLoggedInUser().getCart();
	}

	public static float getTotalSum() {
		return getCart().getTotalSum();
	}

	public static ArrayList<TableElement> getBooksInCart() {
		ArrayList<TableElement> tableElements = new ArrayList<TableElement>();
		ArrayList<Book> books = getCart().getSelectedBooks();
		ArrayList<Integer> quantities = getCart().getQuantities();
		for (int i = 0; i < books.size(); i++) {
			Book currentBook = books.get(i);
			int quantity = quantities.get(i);
			TableElement current = new TableElement();
			current.getData().add(currentBook.getTitle());
			current.getData().add("" + currentBook.getPrice());
			current.getData().add("" + quantity);
			current.getData().add("" + quantity * currentBook.getPrice());
			tableElements.add(current);
		}
		return tableElements;
	}
	
	public static void filterCart(ArrayList<Boolean> filter) {
		System.out.println(filter.get(0));
		Database.getLoggedInUser().getCart().filter(filter);
	}

	public static boolean validateCard(String cardName, String cardNumber) {
		checker.setCreditCardName(cardName);
		checker.setCreditCardNumber(cardNumber);
		return checker.validateNumber();
	}

	public static boolean validateDate(Date date) {
		checker.setExpireDate(date);
		return checker.validateDate();
	}
}
