package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ConfirmOrdersPanel extends JPanel {

	private static final long serialVersionUID = -7135515334902425414L;
	private JLabel confirmOrders;
	private JTable tablePanel;
	private Listener listener;
	private JButton confirmBtn;
	private ConfirmOrdersTableModel model;
	
	public ConfirmOrdersPanel() {
		setLayout(new BorderLayout());
		confirmOrders = new JLabel("Confirm orders");
		model = new ConfirmOrdersTableModel();
		tablePanel = new JTable(model);
		confirmBtn = new JButton("Confirm Orders!");
		add(confirmOrders, BorderLayout.NORTH);
		add(new JScrollPane(tablePanel), BorderLayout.CENTER);
		add(confirmBtn, BorderLayout.SOUTH);
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> orders = new ArrayList<Integer>();
				for (int i = 0; i < model.getTableData().size(); i++) {
					if (model.getConfirmValues().get(i))
						orders.add(model.getTableData().get(i).getISBN());
				}
				listener.eventOccured(new ConfirmEvent(e.getSource(), orders));
			}
		});
	}
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}
	
	public void setData(ArrayList<OrderEvent> tableData) {
		model.setData(tableData);
	}
	
	public void refresh() {
		model.fireTableDataChanged();
	}
}
