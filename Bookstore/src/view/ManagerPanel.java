package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagerPanel extends JPanel {

	private static final long serialVersionUID = -1715362469751215553L;
	private JButton addBookBtn, modifyBookBtn, placeOrderBtn, confirmOrderBtn,
	promoteUsersBtn, viewReportsBtn, searchBtn;
	private Listener listener;
	
	public ManagerPanel() {
		addBookBtn = new JButton("Add new books");
		modifyBookBtn = new JButton("Modify existing books");
		placeOrderBtn = new JButton("Place orders for books");
		confirmOrderBtn = new JButton("Confirm orders");
		promoteUsersBtn = new JButton("Promote registered customers");
		viewReportsBtn = new JButton("View sales reports");
		searchBtn = new JButton("Search Books");
		addBookBtn.setBackground(java.awt.Color.RED);
		modifyBookBtn.setBackground(java.awt.Color.RED);
		placeOrderBtn.setBackground(java.awt.Color.RED);
		confirmOrderBtn.setBackground(java.awt.Color.RED);
		promoteUsersBtn.setBackground(java.awt.Color.RED);
		viewReportsBtn.setBackground(java.awt.Color.RED);
		searchBtn.setBackground(java.awt.Color.RED);

		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 8;
		gc.insets = new Insets(10, 10, 10, 10);
		gc.ipady = 30;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(addBookBtn, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		add(modifyBookBtn, gc);
		gc.gridx = 0;
		gc.gridy = 2;
		add(placeOrderBtn, gc);
		gc.gridx = 0;
		gc.gridy = 3;
		add(confirmOrderBtn, gc);
		gc.gridx = 0;
		gc.gridy = 4;
		add(promoteUsersBtn, gc);
		gc.gridx = 0;
		gc.gridy = 5;
		add(viewReportsBtn, gc);
		gc.gridx = 0;
		gc.gridy = 6;
		add(searchBtn, gc);
		this.setBackground(java.awt.Color.BLACK);
		addBookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.eventOccured(e);
			}
		});
		
		searchBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.eventOccured(e);
			}
		});
	}
	
	public JButton getAddBookBtn() {
		return addBookBtn;
	}

	public JButton getModifyBookBtn() {
		return modifyBookBtn;
	}

	public JButton getPlaceOrderBtn() {
		return placeOrderBtn;
	}

	public JButton getConfirmOrderBtn() {
		return confirmOrderBtn;
	}

	public JButton getPromoteUsersBtn() {
		return promoteUsersBtn;
	}

	public JButton getViewReportsBtn() {
		return viewReportsBtn;
	}
	
	public JButton getSearchBtn() {
		return searchBtn;
	}

	void setListener(Listener listener) {
		this.listener = listener;
	}
}
