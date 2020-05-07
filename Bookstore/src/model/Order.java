package model;

public class Order {
	private int ISBN, quantity;
	
	public Order() {
		
	}
	
	public Order(int isbn, int quantity) {
		this.ISBN = isbn;
		this.quantity = quantity;
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
	
}
