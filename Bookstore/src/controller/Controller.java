package controller;

import model.Database;
import model.User;
import view.LoginEvent;
import view.SignUpEvent;

public class Controller {
	Database db = new Database();
	User user = new User();
	
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
	
	public void logIn(LoginEvent e) {
		db.signIn(e.getUsername(), e.getPassword());
	}
}
