package view;

import java.util.EventObject;

public class LoginEvent extends EventObject{
	private String username;
	private String password;
	private boolean manager;
	private static final long serialVersionUID = 343128712041729123L;
	public LoginEvent(Object source) {
		super(source);
	}
	
	public LoginEvent(Object source, String username, String password, boolean isManager) {
		super(source);
		this.username = username;
		this.password = password;
		this.manager = isManager;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean isManager() {
		return manager;
	}

	
}
