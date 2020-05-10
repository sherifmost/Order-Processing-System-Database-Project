package model;

public class Order {
	private long ISBN;
	private int quantity;

	public Order() {

	}

	public Order(long isbn, int quantity) {
		this.ISBN = isbn;
		this.quantity = quantity;
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

}
