package view;

import java.util.EventObject;

public class AddToCartEvent extends EventObject{
	private int bookISBN;
	private int quantity;
	
	public AddToCartEvent(Object source) {
		super(source);
	}
	
	public AddToCartEvent(Object source, int bookISBN, int quantity) {
		super(source);
		this.bookISBN = bookISBN;
		this.quantity = quantity;
	}
	
	

	public int getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(int bookISBN) {
		this.bookISBN = bookISBN;
	}
	
	
	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
