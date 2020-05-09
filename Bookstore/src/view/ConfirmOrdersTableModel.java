package view;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.AbstractTableModel;


public class ConfirmOrdersTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 424009695305475278L;
	private static String[] colNames = {"Title", "ISBN", "Quantity", "Confirm Order"};
	private ArrayList<OrderEvent> tableData;
	private ArrayList<Boolean> confirmValues;
	
	public ConfirmOrdersTableModel() {
		tableData = new ArrayList<OrderEvent>();
		confirmValues = new ArrayList<Boolean>(Collections.nCopies(tableData.size(), false));
	}

	public void setData(ArrayList<OrderEvent> tableData) {
		this.tableData = tableData;
		confirmValues = new ArrayList<Boolean>(Collections.nCopies(tableData.size(), false));
	}
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		OrderEvent order = tableData.get(row);		
		switch(col) {
			case 0: return order.getTitle();
			case 1: return order.getISBN();
			case 2: return order.getQuantity();
			case 3: return confirmValues.get(row);
			default : return null;
		}
	}
	
	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		switch(col) {
			case 3: return true;
			default: return false;
		}
	}

	@Override
	public Class<?> getColumnClass(int col) {
		switch(col) {
		case 0: return String.class;
		case 1: return Integer.class;
		case 2: return Integer.class ;
		case 3: return Boolean.class;
		default : return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int row, int col) {
		switch(col) {
		case 3:
			confirmValues.set(row, (Boolean) aValue);
			break;
		}
	}

	public ArrayList<OrderEvent> getTableData() {
		return tableData;
	}

	public ArrayList<Boolean> getConfirmValues() {
		return confirmValues;
	}
	
}
