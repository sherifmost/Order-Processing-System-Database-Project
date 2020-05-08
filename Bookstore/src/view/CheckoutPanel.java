package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Book;
import model.Cart;
import model.CreditCardChecker;
import model.Database;
import utils.CreditCardData;

public class CheckoutPanel extends JPanel {

	private JLabel checkoutLabel, totalLabel, cardNameLabel, cardNumberLabel, expireDateLabel, errorLabel;
	private JTextField cardNumberField, expireDateField;
	private JComboBox<String> cardNameBox;
	private CartTablePanel booksInCartPanel;
	private JButton checkoutBtn, backBtn;
	private Listener listener;
	private ArrayList<Book> booksInCart = Database.getLoggedInUser().getCart().getSelectedBooks();
	private Cart cart = Database.getLoggedInUser().getCart();
	private CreditCardChecker checker = new CreditCardChecker();
	private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

	public CheckoutPanel() {
		// Labels
		checkoutLabel = new JLabel("Payment");
		cardNameLabel = new JLabel("Choose payment method");
		cardNumberLabel = new JLabel("Enter valid card number");
		expireDateLabel = new JLabel("Expire Date");
		errorLabel = new JLabel("");
		totalLabel = new JLabel("Total: " + cart.getTotalSum() + "$");
		// text fields
		cardNumberField = new JTextField(25);
		expireDateField = new JTextField("yyyy-MM-dd");
		expireDateField.setForeground(Color.GRAY);
		expireDateField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (expireDateField.getText().equals("yyyy-MM-dd")) {
					expireDateField.setText("");
					expireDateField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (expireDateField.getText().isEmpty()) {
					expireDateField.setForeground(Color.GRAY);
					expireDateField.setText("yyyy-MM-dd");
				}
			}
		});

		// combo boxes
		String[] cardOptions = { CreditCardData.VISA, CreditCardData.MASTERCARD, CreditCardData.AMERICAN_EXPRESS };
		cardNameBox = new JComboBox<>(cardOptions);
		cardNameBox.setSelectedIndex(0);
		// Buttons
		checkoutBtn = new JButton("Proceed to check out");
		backBtn = new JButton("Back");
		// table
		booksInCartPanel = new CartTablePanel();
		booksInCartPanel.setData(booksInCart);
		// Handling the case where the user has nothing in his cart
		if (cart.getSelectedBooks().size() == 0) {
			checkoutBtn.setEnabled(false);
			errorLabel.setText("Cart is empty!");
		}
		// setting the layout

		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 0;
		gc.fill = GridBagConstraints.FIRST_LINE_START;
		add(checkoutLabel, gc);

		gc.insets = new Insets(10, 10, 10, 10);
		gc.fill = GridBagConstraints.NONE;
		gc.ipadx = 20;
		gc.ipady = 10;
		gc.gridy = 2;
		gc.gridx = 0;
		gc.gridwidth = 1;

		add(cardNameLabel, gc);
		gc.gridy = 2;
		gc.gridx = 1;
		gc.gridwidth = 1;
		add(cardNameBox, gc);
		gc.gridy = 3;
		gc.gridx = 0;
		add(cardNumberLabel, gc);
		gc.gridy = 3;
		gc.gridx = 1;
		add(cardNumberField, gc);
		gc.gridy = 4;
		gc.gridx = 0;
		add(expireDateLabel, gc);
		gc.gridy = 4;
		gc.gridx = 1;
		add(expireDateField, gc);
		gc.gridy = 6;
		gc.gridx = 0;
		add(totalLabel, gc);
		gc.gridy = 6;
		gc.gridx = 1;
		add(checkoutBtn, gc);
		gc.gridx = 2;
		add(backBtn, gc);
		gc.gridy = 7;
		gc.gridx = 1;
		add(errorLabel, gc);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 0;
		gc.gridy++;
		gc.gridwidth = 4;
		gc.ipady = 100;
		add(booksInCartPanel, gc);
		// Styling
		checkoutBtn.setBackground(Color.orange);
		backBtn.setBackground(Color.green);
		this.setBackground(Color.GRAY);
		// adding the actions
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.eventOccurred(new SwitchEvent(this));

			}
		});
		checkoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checker.setCreditCardName(cardNameBox.getItemAt(cardNameBox.getSelectedIndex()));
				checker.setCreditCardNumber(cardNumberField.getText());
				if (!checker.validateNumber()) {
					errorLabel.setText("Invalid credit card data");
				} else
					try {
						checker.setExpireDate(sdFormat.parse(expireDateField.getText()));
						if (!checker.validateDate()) {
							errorLabel.setText("Credit card is expired");
						} else {
							errorLabel.setText("");
							// TO DO: perform the update transaction to checkout the cart
							listener.eventOccurred(new CheckoutEvent(this));
						}
					} catch (ParseException e1) {
						errorLabel.setText("Please enter an expire date in form: yyy-MM-dd");

					}
			}
		});
	}

	public Listener getListener() {
		return listener;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public CartTablePanel getBooksInCartPanel() {
		return booksInCartPanel;
	}

	public JLabel getErrorLabel() {
		return errorLabel;
	}

	private static final long serialVersionUID = 4097556501562960076L;
}
