package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

import controller.Controller;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1005606185398506511L;
	private Controller controller;
	private LoginPanel loginPanel;
	
	public MainFrame() {
		super("Bookstore");
		controller = new Controller();
		loginPanel = new LoginPanel();
		controller.createConnection();
		setLayout(new BorderLayout());
		setSize(600, 600);
		setMinimumSize(new Dimension(400, 400));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(loginPanel);
	}
}
