package controller;

import java.util.ArrayList;

import model.Book;
import model.Cart;
import model.Database;
import model.Publisher;
import model.SearchQuery;
import model.User;
import model.UserRegistrationInfo;
import view.AddToCartEvent;
import view.BookEvent;
import view.LoginEvent;
import view.PublisherEvent;
import view.SearchEvent;
import view.SignUpEvent;
import view.UpdateDataEvent;

public class Controller {
	Database db;
	UserRegistrationInfo userInfo;
	Publisher publisher;
	Book book;
	User user;
	
	public Controller() {
		db = new Database();
		userInfo = new UserRegistrationInfo();
		publisher = new Publisher();
		book = new Book();
	}

	public void connectToDB() {
		db.createConnection();
		db.signUpSuperUser();
	}

	public void registerUser(SignUpEvent e) {
		userInfo.setUserName(e.getUsername());
		userInfo.setFirstName(e.getFirstName());
		userInfo.setLastName(e.getLastName());
		userInfo.setEmail(e.getEmail());
		userInfo.setPassword(e.getPassword());
		userInfo.setShippingAddress(e.getShippingAddress());
		userInfo.setPhone(e.getPhone());
		db.signUpNewUser(userInfo);
	}

	public void addPublisher(PublisherEvent e) {
		publisher.setAddress(e.getPublisherAddress());
		publisher.setPublisherName(e.getPubisherName());
		publisher.setTelephone(e.getPublihserTelephone());
		db.addNewPublisher(publisher);
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
		user = new User(e.getUsername(), e.isManager());
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
					book.getPublicationYear(), book.getPrice(), book.getCategory(),
					book.getThreshold(), book.getCopies()));
		}
		return results;
	}

	public boolean updateUserData(UpdateDataEvent e) {
		return db.updateUser(e);
	}
	
	public void addBookToCart(AddToCartEvent e) {
		Cart cart = user.getCart();
		Book book = new Book();
		book.setISBN(e.getBookISBN());
		cart.addBookToCart(book, e.getQuantity());
	}
	
	public boolean checkout() {
		return db.checkout(user.getCart().getSelectedBooks(), user.getCart().getQuantities());
	}
	
	public boolean isManager() {
		return user.isManager();
	}
}
