package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Database;
import model.User;

public class EditInfoPanel extends JPanel {

	private JLabel editInfoFormLabel;
	private JLabel errorLabel;
	private JLabel usernameLabel, passwordLabel, firstNameLabel, lastNameLabel, emailLabel, phoneLabel,
			shippingAddressLabel, retypePasswordLabel;
	private JTextField usernameField, firstNameField, lastNameField, emailField, phoneField, shippingAddressField;
	private JPasswordField passwordField, retypePasswordField;
	private JButton doneButton;
	private Listener listener;

	// The logged in user data is filled in a static variable in the database class
	// to ease the access
	private User user = Database.getLoggedInUser();

	public EditInfoPanel() {
		editInfoFormLabel = new JLabel("Account Info");
		usernameLabel = new JLabel("Username");
		firstNameLabel = new JLabel("First Name");
		lastNameLabel = new JLabel("Last Name");
		emailLabel = new JLabel("Email");
		phoneLabel = new JLabel("Phone");
		shippingAddressLabel = new JLabel("Shipping Address");
		passwordLabel = new JLabel("Password");
		retypePasswordLabel = new JLabel("Retype Password");
		errorLabel = new JLabel("");
		// For each field we fill in the logged in user data
		usernameField = new JTextField(15);
		usernameField.setText(user.getUserName());
		firstNameField = new JTextField(15);
		firstNameField.setText(user.getFirstName());
		lastNameField = new JTextField(15);
		lastNameField.setText(user.getLastName());
		emailField = new JTextField(15);
		emailField.setText(user.getEmail());
		phoneField = new JTextField(15);
		phoneField.setText(user.getPhone());
		shippingAddressField = new JTextField(15);
		shippingAddressField.setText(user.getShippingAddress());
		passwordField = new JPasswordField(15);
		passwordField.setText(user.getPassword());
		retypePasswordField = new JPasswordField(15);
		retypePasswordField.setText(user.getPassword());
		// setting the button,layout and events
		doneButton = new JButton("Update data");
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 8;
		gc.fill = GridBagConstraints.FIRST_LINE_START;
		add(editInfoFormLabel, gc);
		gc.insets = new Insets(10, 10, 10, 10);
		gc.fill = GridBagConstraints.NONE;
		gc.gridy = 1;
		gc.gridx = 0;
		gc.gridwidth = 1;
		add(usernameLabel, gc);
		gc.gridy = 1;
		gc.gridx = 1;
		gc.gridwidth = 1;
		add(usernameField, gc);
		gc.gridy = 2;
		gc.gridx = 0;
		add(firstNameLabel, gc);
		gc.gridy = 2;
		gc.gridx = 1;
		add(firstNameField, gc);
		gc.gridy = 3;
		gc.gridx = 0;
		add(lastNameLabel, gc);
		gc.gridy = 3;
		gc.gridx = 1;
		add(lastNameField, gc);
		gc.gridy = 4;
		gc.gridx = 0;
		add(emailLabel, gc);
		gc.gridy = 4;
		gc.gridx = 1;
		add(emailField, gc);
		gc.gridy = 5;
		gc.gridx = 0;
		add(passwordLabel, gc);
		gc.gridy = 5;
		gc.gridx = 1;
		add(passwordField, gc);
		gc.gridy = 6;
		gc.gridx = 0;
		add(retypePasswordLabel, gc);
		gc.gridy = 6;
		gc.gridx = 1;
		add(retypePasswordField, gc);
		gc.gridy = 7;
		gc.gridx = 0;
		add(shippingAddressLabel, gc);
		gc.gridy = 7;
		gc.gridx = 1;
		add(shippingAddressField, gc);
		gc.gridy = 8;
		gc.gridx = 0;
		add(phoneLabel, gc);
		gc.gridy = 8;
		gc.gridx = 1;
		add(phoneField, gc);
		gc.insets = new Insets(30, 0, 0, 0);
		gc.gridy = 9;
		gc.gridx = 1;
		gc.ipady = 20;
		add(doneButton, gc);
		gc.gridy = 10;
		gc.gridx = 1;
		gc.ipady = 20;
		add(errorLabel, gc);
		// Styling
		errorLabel.setBackground(java.awt.Color.RED);
		this.setBackground(java.awt.Color.CYAN);
		doneButton.setBackground(java.awt.Color.GREEN);
		doneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (!new String(passwordField.getPassword()).equals(new String(retypePasswordField.getPassword()))) {
					errorLabel.setText("Passwords don't match!");
				} else {
					errorLabel.setText("");
					listener.eventOccurred(new UpdateDataEvent(this, usernameField.getText(), firstNameField.getText(),
							lastNameField.getText(), emailField.getText(), new String(passwordField.getPassword()),
							shippingAddressField.getText(), phoneField.getText()));
				}
			}
		});
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	private static final long serialVersionUID = -5684519534379106091L;
}
