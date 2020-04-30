package controller;

import model.Database;
import model.Publisher;
import model.User;
import view.LoginEvent;
import view.PublisherEvent;
import view.SignUpEvent;

public class Controller {
	Database db = new Database();
	User user = new User();
	Publisher publisher = new Publisher();

	public void connectToDB() {
		db.createConnection();
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

	public void logIn(LoginEvent e) {
		db.signIn(e.getUsername(), e.getPassword());
	}
}
