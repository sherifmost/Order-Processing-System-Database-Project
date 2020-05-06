package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class TableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<BookEvent> tableData;
	
	public TableModel() {
		tableData = new ArrayList<BookEvent>();
	}

	public void setData(ArrayList<BookEvent> tableData) {
		this.tableData = tableData;
	}
	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		BookEvent book = tableData.get(row);		
		switch(col) {
			case 0: return book.getTitle();
			case 1: return book.getISBN();
			case 2: return book.getCategory();
			case 3: return book.getCopies();
			case 4: return book.getPublisherName();
			case 5: return book.getPrice();
			//case 6: return book.getPublicationYear();
			default : return null;
		}
	}

}
