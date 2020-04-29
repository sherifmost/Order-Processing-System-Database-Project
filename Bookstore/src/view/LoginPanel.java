package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameTextField, passwordTextField;
	private JButton loginButton, signUpButton;
	
	public LoginPanel() {
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		usernameTextField = new JTextField(12);
		passwordTextField = new JTextField(12);
		loginButton = new JButton("Login");
		signUpButton = new JButton("Sign up");
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridy = 0;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.ipadx = 20;
		gc.ipady = 20;
		this.add(usernameLabel, gc);
		gc.gridx++;
		this.add(usernameTextField, gc);
		gc.gridx = 0;
		gc.gridy++;
		this.add(passwordLabel, gc);
		gc.gridx++;
		this.add(passwordTextField, gc);
		gc.gridy++;
		gc.gridx = 0;
		this.add(signUpButton, gc);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.gridx++;
		gc.gridheight = 2;
		this.add(loginButton, gc);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 20;
	
}
