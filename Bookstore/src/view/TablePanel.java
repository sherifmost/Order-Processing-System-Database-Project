package view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel extends JTable {
	
	private static final long serialVersionUID = -805205485500366310L;
	private JTable table;
	private TableModel tableModel;
	
	public TablePanel() {
		tableModel = new TableModel();
		table = new JTable(tableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		table.addMouseListener(new MouseAdapter() {
			
		});
	}
	
	public void setData(ArrayList<BookEvent> tableData) {
		tableModel.setData(tableData);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
	
	public JTable getTable() {
		return table;
	}
	
	public TableModel getTableModel() {
		return tableModel;
	}
	
	
	
	

}
