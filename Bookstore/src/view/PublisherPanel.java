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

public class PublisherPanel extends JPanel {
	// setting up the GUI objects used in the panel
	private JLabel formLabel, nameLabel, addressLabel, telephoneLabel;
	private JTextField nameField, addressField, telephoneField;
	private JButton addPublisherBtn;
	private Listener listener;

	// setting up the panel
	public PublisherPanel() {
		// setting the labels
		formLabel = new JLabel("Add a publisher");
		nameLabel = new JLabel("Publisher's name");
		addressLabel = new JLabel("Publisher's address");
		telephoneLabel = new JLabel("Publisher's telephone");
		// setting the text fields
		nameField = new JTextField(15);
		addressField = new JTextField(15);
		telephoneField = new JTextField(15);
		// setting the buttons
		addPublisherBtn = new JButton("Add");
		// setting the layout as a grid bag layout
		this.setLayout(new GridBagLayout());
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
		add(nameLabel, gc);
		gc.gridy = 2;
		gc.gridx = 1;
		gc.gridwidth = 1;
		add(nameField, gc);
		gc.gridy = 4;
		gc.gridx = 0;
		add(addressLabel, gc);
		gc.gridy = 4;
		gc.gridx = 1;
		add(addressField, gc);
		gc.gridy = 6;
		gc.gridx = 0;
		add(telephoneLabel, gc);
		gc.gridy = 6;
		gc.gridx = 1;
		add(telephoneField, gc);
		gc.insets = new Insets(30, 0, 0, 0);
		gc.gridy = 7;
		gc.gridx = 2;
		gc.ipady = 20;
		add(addPublisherBtn, gc);
		// Styling
		this.setBackground(java.awt.Color.GRAY);
		addPublisherBtn.setBackground(java.awt.Color.orange);
		// Adding the button action
		addPublisherBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				listener.eventOccurred(new PublisherEvent(this, nameField.getText(), addressField.getText(),
						telephoneField.getText()));
			}
		});
	}

	// setter for the listener
	public void setListener(Listener listener) {
		this.listener = listener;
	}

	// Serial ID
	private static final long serialVersionUID = 1280421448332080666L;

}
