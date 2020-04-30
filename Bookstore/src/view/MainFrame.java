package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import controller.Controller;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1005606185398506511L;
	private Controller controller;
	private LoginPanel loginPanel;
	private SignUpPanel signUpPanel;
	private static MainFrame uniqueInstance;
	
	private MainFrame() {
		super("Bookstore");
		controller = new Controller();
		loginPanel = new LoginPanel();
		signUpPanel = new SignUpPanel();
		loginPanel.setListener(new Listener() {
			@Override
			public void eventOccurred(SwitchEvent e) {
				if (e.getNextFrame() == "signup") {
					loginPanel.setVisible(false);
					uniqueInstance.add(signUpPanel);
				}
			}
		});
		signUpPanel.setListener(new Listener() {
			@Override
			public void eventOccurred(SignUpEvent e) {
				controller.registerUser(e);
			}
		});
		
		loginPanel.setListener(new Listener() {
			@Override
			public void eventOccurred(LoginEvent e) {
				controller.logIn(e);
			}
		});
		controller.connectToDB();
		setLayout(new BorderLayout());
		setSize(600, 600);
		setMinimumSize(new Dimension(400, 400));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(loginPanel);
	}
	
	public static synchronized MainFrame getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MainFrame();
		}
		return uniqueInstance;
	}
}
