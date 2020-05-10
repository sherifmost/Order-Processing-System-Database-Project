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
	
	public void showCart() {
		System.out.println("*************");
		for (int i = 0; i < selectedBooks.size(); i++) {
			System.out.println(selectedBooks.get(i).getTitle());
		}
	}
	
	public void filter(ArrayList<Boolean> filter) {
		float newSum = 0;
		ArrayList<Book> filteredBooks = new ArrayList<>();
		ArrayList<Integer> filteredQuantities = new ArrayList<>();
		for (int index = 0; index < selectedBooks.size(); index++) {
			if (!filter.get(index)) {
				filteredBooks.add(selectedBooks.get(index));
				filteredQuantities.add(quantities.get(index));
				newSum += selectedBooks.get(index).getPrice() * quantities.get(index);
			}
		}
		this.selectedBooks = filteredBooks;
		this.quantities = filteredQuantities;
		totalSum = newSum;
	}
	
		
}