package view;

import java.util.EventObject;

public class LoginEvent extends EventObject{

	private String username;
	private String password;
	public LoginEvent(Object source) {
		super(source);
	}
	
	public LoginEvent(Object source, String username, String password) {
		super(source);
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
