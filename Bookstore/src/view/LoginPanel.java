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

public class LoginPanel extends JPanel {
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JButton loginButton, signUpButton;
	private Listener listener;
	
	public JButton getSignUpButton() {
		return signUpButton;
	}
	
	public LoginPanel() {
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		usernameTextField = new JTextField(15);
		passwordField = new JPasswordField(15);
		loginButton = new JButton("Login");
		signUpButton = new JButton("Sign up");
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(10, 0, 0, 0);
		gc.gridy = 0;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.ipadx = 20;
		gc.ipady = 20;
		this.add(usernameLabel, gc);
		gc.gridx = 1;
		gc.gridy = 0;
		this.add(usernameTextField, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		this.add(passwordLabel, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		this.add(passwordField, gc);
		gc.gridy = 2;
		gc.gridx = 0;
		gc.insets = new Insets(30, 0, 0, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		this.add(signUpButton, gc);
		gc.gridx = 1;
		gc.gridheight = 3;
		this.add(loginButton, gc);
		this.setBackground(java.awt.Color.CYAN);
		signUpButton.setBackground(java.awt.Color.BLUE);
		loginButton.setBackground(java.awt.Color.GREEN);
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				listener.eventOccurred(new SwitchEvent(this, "signup"));
			}
		});
		
		loginButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listener.eventOccurred(new LoginEvent(this, usernameTextField.getText(), passwordField.getText()));
			}
		});
	}
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}
	
	private static final long serialVersionUID = 20;
	
}
