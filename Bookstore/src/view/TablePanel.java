package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JTable;
import model.Book;

public class TablePanel extends JTable{
	
	private JTable table;
	private TableModel tableModel;
	
	public TablePanel() {
		tableModel = new TableModel();
		table = new JTable(tableModel);
		setLayout(new BorderLayout());
		add(table, BorderLayout.CENTER);
	}
	
	public void setData(ArrayList<Book> tableData) {
		tableModel.setData(tableData);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}

}
