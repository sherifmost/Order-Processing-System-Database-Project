package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PlaceOrderPanel extends JPanel {
	private static final long serialVersionUID = -924660491830100575L;
	private JLabel bookISBNLabel, quantityLabel, placeOrderLabel;
	private JTextField bookISBNField;
	private JSpinner quantitySpinner;
	private SpinnerNumberModel spinnerModel;
	private JButton orderBtn;
	private Listener listener;
	
	public PlaceOrderPanel() {
		placeOrderLabel = new JLabel("Place Order");
		bookISBNLabel = new JLabel("ISBN");
		bookISBNField = new JTextField(15);
		quantityLabel = new JLabel("Quantity");
		spinnerModel = new SpinnerNumberModel(50, 0, 100, 1);
		quantitySpinner = new JSpinner(spinnerModel);
		orderBtn = new JButton("Order!");
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(10, 10, 10, 10);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(placeOrderLabel, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		add(bookISBNLabel, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		add(bookISBNField, gc);
		gc.gridx = 0;
		gc.gridy = 2;
		add(quantityLabel, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		add(quantitySpinner, gc);
		gc.gridy = 3;
		gc.gridx = 1;
		add(orderBtn, gc);
		
		orderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int isbn = Integer.parseInt(bookISBNField.getText());
				int quantity = (int) quantitySpinner.getValue();
				listener.eventOccured(new OrderEvent(e, isbn, quantity));
			}
		});
	}
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}
}
