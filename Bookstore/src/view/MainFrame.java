package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.EventObject;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1005606185398506511L;
	private Controller controller;
	private LoginPanel loginPanel;
	private SignUpPanel signUpPanel;
	private ManagerPanel managerPanel;
	private NewBookPanel newBookPanel;
	private static MainFrame uniqueInstance;
	private static JPanel cards;
	
	private MainFrame() {
		super("Bookstore");
		controller = new Controller();
		loginPanel = new LoginPanel();
		signUpPanel = new SignUpPanel();
		managerPanel = new ManagerPanel();
		newBookPanel = new NewBookPanel();
		cards = new JPanel(new CardLayout());
		loginPanel.setListener(new Listener() {
			@Override
			public void eventOccurred(SwitchEvent e) {
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.show(cards, "SIGNUP");
			}
			@Override
			public void eventOccurred(LoginEvent e) {
				controller.logIn(e);
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.show(cards, "MANAGER");
			}
		});
		signUpPanel.setListener(new Listener() {
			@Override
			public void eventOccurred(SignUpEvent e) {
				controller.registerUser(e);
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.show(cards, "LOGIN");
			}
		});
		managerPanel.setListener(new Listener() {
			@Override
			public void eventOccured(EventObject e) {
				if (e.getSource() == managerPanel.getAddBookBtn()) {
					CardLayout cl = (CardLayout) cards.getLayout();
					cl.show(cards, "NEWBOOK");
				}
			}
		});
		newBookPanel.setListener(new Listener() {
			@Override
			public void eventOccured(EventObject e) {
				if (e.getSource() == newBookPanel.getAddBookBtn()) {
					
				}
			}
		});
		controller.connectToDB();
		setSize(600, 600);
		setMinimumSize(new Dimension(400, 400));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cards.add(loginPanel, "LOGIN");
		cards.add(signUpPanel, "SIGNUP");
		cards.add(managerPanel, "MANAGER");
		cards.add(newBookPanel, "NEWBOOK");
		this.add(cards);		
	}
	
	public static synchronized MainFrame getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MainFrame();
		}
		return uniqueInstance;
	}
}
