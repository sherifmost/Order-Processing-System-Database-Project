package model;

public class User {
	private String userName;
	private Cart cart;
	private boolean isManager;
	
	public User(String userName, boolean isManager) {
		this.setUserName(userName);
		setCart(new Cart());
		this.setIsManager(isManager);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setIsManager(boolean userType) {
		this.isManager = userType;
	}
	
	
}
