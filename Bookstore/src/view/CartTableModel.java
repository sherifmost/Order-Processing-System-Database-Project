package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import model.Book;
import model.Cart;
import model.Database;

public class CartTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 7064428593063487905L;
	private ArrayList<Book> books;
	private Cart cart = Database.getLoggedInUser().getCart();
	private String[] columnsName = { "Book Title", "Price", "Quantity", "Total price" };

	public CartTableModel() {
		books = new ArrayList<Book>();
	}

	public void setData(ArrayList<Book> books) {
		this.books = books;
	}

	@Override
	public String getColumnName(int column) {
		return columnsName[column];
	}

	@Override
	public int getColumnCount() {
		return columnsName.length;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Book currentBook = books.get(row);
		int currentQuantity = cart.getQuantities().get(row);
		switch (col) {
		// Title
		case 0:
			return currentBook.getTitle();
		// price
		case 1:
			return currentBook.getPrice();
		case 2:
			return currentQuantity;
		case 3:
			return currentQuantity * currentBook.getPrice();
		default:
			return null;
		}
	}

}
