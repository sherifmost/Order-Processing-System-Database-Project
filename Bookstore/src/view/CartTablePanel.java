package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CartTablePanel extends JTable {

	// Creating a table for the checkout cart
	private JTable table;
	private CartTableModel checkoutTableModel;

	public CartTablePanel() {
		checkoutTableModel = new CartTableModel();
		table = new JTable(checkoutTableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public void setData(ArrayList<TableElement> books) {
		checkoutTableModel.setData(books);
	}

	public void refresh() {
		checkoutTableModel.fireTableDataChanged();
	}

	private static final long serialVersionUID = 8668185028388867912L;

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public CartTableModel getCheckoutTableModel() {
		return checkoutTableModel;
	}

	public void setCheckoutTableModel(CartTableModel checkoutTableModel) {
		this.checkoutTableModel = checkoutTableModel;
	}
}
