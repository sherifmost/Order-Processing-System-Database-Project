package model;

import java.util.ArrayList;

public class Cart {
	
	private ArrayList<Book> selectedBooks;
	private ArrayList<Integer> quantities; 
	private float totalSum;
	
	public Cart() {
		selectedBooks = new ArrayList<Book>();
		quantities = new ArrayList<Integer>();
		totalSum = 0;
	}
	
	public void addBookToCart(Book book, int quantity) {
		selectedBooks.add(book);
		quantities.add(quantity);
		totalSum += (quantity * book.getPrice());
	}
	
	public ArrayList<Book> getSelectedBooks() {
		return selectedBooks;
	}
	
	public ArrayList<Integer> getQuantities(){
		return quantities;
	}
	
	public float getTotalSum() {
		return totalSum;
	}
	
		
}
