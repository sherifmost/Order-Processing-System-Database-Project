package controller;

import java.util.ArrayList;

import model.Book;
import model.Database;
import model.Publisher;
import model.SearchQuery;
import model.User;
import view.BookEvent;
import view.LoginEvent;
import view.PublisherEvent;
import view.SearchEvent;
import view.SignUpEvent;
import view.UpdateDataEvent;

public class Controller {
	Database db;
	User user;
	Publisher publisher;
	Book book;
	
	public Controller() {
		db = new Database();
		user = new User();
		publisher = new Publisher();
		book = new Book();
	}

	public void connectToDB() {
		db.createConnection();
		db.signUpSuperUser();
	}

	public void registerUser(SignUpEvent e) {
		user.setUserName(e.getUsername());
		user.setFirstName(e.getFirstName());
		user.setLastName(e.getLastName());
		user.setEmail(e.getEmail());
		user.setPassword(e.getPassword());
		user.setShippingAddress(e.getShippingAddress());
		user.setPhone(e.getPhone());
		db.signUpNewUser(user);
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
}
