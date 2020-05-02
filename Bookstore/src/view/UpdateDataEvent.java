package view;

import java.util.EventObject;

public class UpdateDataEvent extends EventObject {
	private static final long serialVersionUID = 105572062388360309L;
	private String username, firstName, lastName, email, password, shippingAddress, phone;

	public UpdateDataEvent(Object source) {
		super(source);
	}

	public UpdateDataEvent(Object source, String username, String firstName, String lastName, String email,
			String password, String shippingAddress, String phone) {
		super(source);
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.shippingAddress = shippingAddress;
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public String getPhone() {
		return phone;
	}
}
