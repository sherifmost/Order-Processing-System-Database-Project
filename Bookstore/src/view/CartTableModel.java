package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class CartTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 7064428593063487905L;
	private ArrayList<TableElement> books;
	private String[] columnsName = { "Book Title", "Price", "Quantity", "Total price" };

	public CartTableModel() {
		books = new ArrayList<TableElement>();
	}

	public void setData(ArrayList<TableElement> books) {
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
		if (books.get(row) == null)
			return null;
		return books.get(row).getData().get(col);
	}
}
