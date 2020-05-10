package view;

import java.util.EventObject;

public class OrderEvent extends EventObject {

	private static final long serialVersionUID = 7097018042433277100L;
	private long ISBN;
	private int quantity;
	String title;

	public OrderEvent(Object source) {
		super(source);
	}

	public OrderEvent(Object source, long ISBN, int quantity) {
		super(source);
		this.ISBN = ISBN;
		this.quantity = quantity;
	}

	public OrderEvent(Object source, long ISBN, int quantity, String title) {
		super(source);
		this.ISBN = ISBN;
		this.quantity = quantity;
		this.title = title;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long iSBN) {
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
