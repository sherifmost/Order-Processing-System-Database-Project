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

public class SignUpPanel extends JPanel {
	JLabel registrationFormLabel;
	private JLabel usernameLabel, passwordLabel, firstNameLabel, lastNameLabel, emailLabel,
	phoneLabel, shippingAddressLabel, retypePasswordLabel;
	private JTextField usernameField, firstNameField, lastNameField, emailField,
	phoneField, shippingAddressField;
	private JPasswordField passwordField, retypePasswordField;
	private JButton registerButton;
	private Listener listener;

	public SignUpPanel() {
		 registrationFormLabel = new JLabel("Registration Form");
		 usernameLabel = new JLabel("Username");
		 firstNameLabel = new JLabel("First Name");
		 lastNameLabel = new JLabel("Last Name");
		 emailLabel = new JLabel("Email");
		 phoneLabel = new JLabel("Phone");
		 shippingAddressLabel = new JLabel("Shipping Address");
		 passwordLabel = new JLabel("Password");
		 retypePasswordLabel = new JLabel("Retype Password");
		 usernameField = new JTextField(15);
		 firstNameField = new JTextField(15);
		 lastNameField = new JTextField(15);
		 emailField = new JTextField(15);
		 phoneField = new JTextField(15);
		 shippingAddressField = new JTextField(15);
		 passwordField = new JPasswordField(15);
		 retypePasswordField = new JPasswordField(15);
		 registerButton = new JButton("Register");
		 this.setLayout(new GridBagLayout());
		 GridBagConstraints gc = new GridBagConstraints();
		 gc.gridx = 0;
		 gc.gridy = 0;
		 gc.gridwidth = 8;
		 gc.fill = GridBagConstraints.FIRST_LINE_START;
		 add(registrationFormLabel, gc);
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
		 add(registerButton, gc);
		 this.setBackground(java.awt.Color.CYAN);
		 registerButton.setBackground(java.awt.Color.GREEN);
		 registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				listener.eventOccurred(new SignUpEvent(this, usernameField.getText(),
						firstNameField.getText(), lastNameField.getText(), 
						emailField.getText(), passwordField.getText(), shippingAddressField.getText(),
						phoneField.getText()));
			}
		});
	}
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}
	
	private static final long serialVersionUID = 7298764152390830707L;
}
