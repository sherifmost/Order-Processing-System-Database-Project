package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JButton loginButton, signUpButton;
	private Listener listener;
	private JRadioButton managerBtn, customerBtn;
	private ButtonGroup userOption;
	
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
		managerBtn = new JRadioButton("Manager");
		customerBtn = new JRadioButton("Customer");
		userOption = new ButtonGroup();
		userOption.add(managerBtn);
		userOption.add(customerBtn);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(10, 10, 10, 10);
		gc.gridy = 0;
		gc.gridx = 0;
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
		gc.fill = GridBagConstraints.HORIZONTAL;
		this.add(customerBtn, gc);
		gc.gridx = 1;
		this.add(managerBtn, gc);
		gc.gridy = 3;
		gc.gridx = 0;
		this.add(signUpButton, gc);
		gc.gridy = 3;
		gc.gridx = 1;
		this.add(loginButton, gc);
		customerBtn.setSelected(true);
		this.setBackground(java.awt.Color.GRAY);
		signUpButton.setBackground(java.awt.Color.ORANGE);
		loginButton.setBackground(java.awt.Color.ORANGE);
		customerBtn.setBackground(java.awt.Color.GRAY);
		managerBtn.setBackground(java.awt.Color.GRAY);
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				listener.eventOccurred(new SwitchEvent(this));
			}
		});
		
		loginButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isManager = getSelectedButtonText(userOption).equals("Manager");
				listener.eventOccurred(new LoginEvent(this, usernameTextField.getText(),
						new String(passwordField.getPassword()), isManager));
			}
		});
	}
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
	
	private static final long serialVersionUID = 20;
	
}
