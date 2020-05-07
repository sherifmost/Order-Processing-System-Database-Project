package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Database;

public class PromotionPanel extends JPanel {

	// setting up the GUI objects used in the panel
	private JLabel formLabel, userNameLabel, errorLabel;
	private JTextField userNameField;
	private JButton promoteBtn, backBtn;
	private Listener listener;

	// setting up the panel
	public PromotionPanel() {
		// setting the labels
		formLabel = new JLabel("Promote a customer");
		userNameLabel = new JLabel("User name");
		// setting the text field
		userNameField = new JTextField(15);
		// setting the buttons
		promoteBtn = new JButton("Promote");
		backBtn = new JButton("Back");
		errorLabel = new JLabel("");
		// setting the layout as a grid bag layout
		this.setLayout(new GridBagLayout());
		// setting the grid and adding the elements
		GridBagConstraints gc = new GridBagConstraints();
		// adding the GUI items
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 8;
		gc.fill = GridBagConstraints.FIRST_LINE_START;
		add(formLabel, gc);
		gc.insets = new Insets(10, 10, 10, 10);
		gc.fill = GridBagConstraints.NONE;
		gc.gridy = 2;
		gc.gridx = 0;
		gc.gridwidth = 1;
		add(userNameLabel, gc);
		gc.gridy = 2;
		gc.gridx = 1;
		gc.gridwidth = 1;
		add(userNameField, gc);
		gc.gridy = 4;
		gc.gridx = 1;
		gc.gridwidth = 1;
		add(errorLabel, gc);
		// Adding the buttons
		gc.insets = new Insets(30, 0, 0, 0);
		gc.gridy = 7;
		gc.gridx = 2;
		gc.ipady = 20;
		add(promoteBtn, gc);
		gc.gridy = 12;
		gc.gridx = 2;
		gc.ipady = 20;
		add(backBtn, gc);
		// styling
		this.setBackground(java.awt.Color.GRAY);
		promoteBtn.setBackground(java.awt.Color.orange);
		backBtn.setBackground(java.awt.Color.green);
		// adding the button action
		promoteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (!Database.userNameExists(userNameField.getText())) {
					errorLabel.setText("User name doesn't exist");
				} else {
					errorLabel.setText("");
					listener.eventOccured(new PromotionEvent(this, userNameField.getText()));
				}

			}
		});
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.eventOccured(e);
			}
		});
	}

	// To get the event
	public JButton getBackBtn() {
		return backBtn;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	// setter for the listener
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	private static final long serialVersionUID = -8783421495766018745L;
}
