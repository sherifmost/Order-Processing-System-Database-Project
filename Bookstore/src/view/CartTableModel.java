package view;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.AbstractTableModel;

public class CartTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 7064428593063487905L;
	private ArrayList<TableElement> books;
	private ArrayList<Boolean> selectedRows;
	private String[] columnsName = { "Book Title", "Price", "Quantity", "Total price", "Selected"};

	public CartTableModel() {
		books = new ArrayList<TableElement>();
		selectedRows = new ArrayList<Boolean>(Collections.nCopies(books.size(), false));
	}

	public void setData(ArrayList<TableElement> books) {
		this.books = books;
		selectedRows = new ArrayList<Boolean>(Collections.nCopies(books.size(), false));
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
		if (col == 4) return selectedRows.get(row);
		return books.get(row).getData().get(col);
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		switch(col) {
			case 4: return true;
			default: return false;
		}
	}

	@Override
	public Class<?> getColumnClass(int col) {
		switch(col) {
		case 0: return String.class;
		case 1: return Integer.class;
		case 2: return Integer.class ;
		case 3: return Integer.class;
		case 4: return Boolean.class;
		default : return null;
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int col) {
		switch(col) {
		case 4:
			selectedRows.set(row, (Boolean) aValue);
			break;
		}
	}
	
	public ArrayList<TableElement> getBooks() {
		return this.books;
	}
	
	public ArrayList<Boolean> getSelectedRows() {
		return selectedRows;
	}

}
