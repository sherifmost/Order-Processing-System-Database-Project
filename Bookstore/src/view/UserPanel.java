package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UserPanel extends JPanel {
	// Adding buttons to support the required user functionalities
	private JButton editPersonalInfo, searchForBook, checkOutCart, logOut;
	private Listener listener;

	public UserPanel() {
		editPersonalInfo = new JButton("Edit profile");
		searchForBook = new JButton("Find a book");
		checkOutCart = new JButton("Check out");
		logOut = new JButton("Log out");
		editPersonalInfo.setBackground(java.awt.Color.ORANGE);
		searchForBook.setBackground(java.awt.Color.ORANGE);
		checkOutCart.setBackground(java.awt.Color.GREEN);
		logOut.setBackground(java.awt.Color.RED);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 8;
		gc.insets = new Insets(10, 10, 10, 10);
		gc.ipady = 50;
		gc.ipadx = 50;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(editPersonalInfo, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		add(searchForBook, gc);
		gc.gridx = 0;
		gc.gridy = 2;
		add(checkOutCart, gc);
		gc.gridx = 0;
		gc.gridy = 3;
		add(logOut, gc);
		this.setBackground(java.awt.Color.GRAY);
		editPersonalInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.eventOccured(e);
			}
		});
		searchForBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.eventOccured(e);
			}
		});
		checkOutCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.eventOccured(e);
			}
		});
		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.eventOccured(e);
			}
		});
	}

	public JButton getEditInfoBtn() {
		return editPersonalInfo;
	}

	public JButton getSearchForBookBtn() {
		return searchForBook;
	}

	public JButton getCheckOutCartBtn() {
		return checkOutCart;
	}

	public JButton getLogOutBtn() {
		return logOut;
	}

	void setListener(Listener listener) {
		this.listener = listener;
	}

	private static final long serialVersionUID = -7747595646489401L;

}
