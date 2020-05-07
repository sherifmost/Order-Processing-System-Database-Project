package view;

import java.util.EventObject;

public class OrderEvent extends EventObject {
	
	private static final long serialVersionUID = 7097018042433277100L;
	private int ISBN, quantity;
	String title;
	
	public OrderEvent(Object source) {
		super(source);
	}
	
	public OrderEvent(Object source, int ISBN, int quantity) {
		super(source);
		this.ISBN = ISBN;
		this.quantity = quantity;
	}
	
	public OrderEvent(Object source, int ISBN, int quantity, String title) {
		super(source);
		this.ISBN = ISBN;
		this.quantity = quantity;
		this.title = title;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
