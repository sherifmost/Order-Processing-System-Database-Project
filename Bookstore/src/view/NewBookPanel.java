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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utils.Category;

public class NewBookPanel extends JPanel {
	private JLabel bookISBNLabel, bookTitleLabel,
		publisherNameLabel, publicationYearLabel, priceLabel,
		thresholdLabel, numberOfCopiesLabel, categoryLabel,
		authorsLabel, newBookLabel;
	private JTextField bookISBNField, bookTitleField,
		publisherNameField, priceField, thresholdField,
		numberOfCopiesField, authorField;
	private JTextArea authors;
	private JRadioButton scienceBtn, artBtn, religionBtn, historyBtn, geographyBtn;
	private ButtonGroup categoryGroup;
	private JComboBox<String> yearComboBox;
	private JButton addBookBtn, addAuthorBtn, addPublisherBtn;
	private Listener listener;
	
	public NewBookPanel() {
		newBookLabel = new JLabel("New Book");
		bookISBNLabel = new JLabel("Book ISBN");
		bookTitleLabel = new JLabel("Book title");
		publisherNameLabel = new JLabel("Publisher name");
		publicationYearLabel = new JLabel("Publication year");
		priceLabel = new JLabel("Price");
		thresholdLabel = new JLabel("Threshold");
		authorsLabel = new JLabel("Author(s)");
		numberOfCopiesLabel = new JLabel("Number of copies");
		categoryLabel = new JLabel("Category");
		bookISBNField = new JTextField(15);
		bookTitleField = new JTextField(15);
		publisherNameField = new JTextField(15);
		priceField = new JTextField(15);
		thresholdField = new JTextField(15);
		numberOfCopiesField = new JTextField(15);
		authorField = new JTextField(15);
		scienceBtn = new JRadioButton("Science");
		artBtn = new JRadioButton("Art");
		religionBtn = new JRadioButton("Religion");
		historyBtn = new JRadioButton("History");
		geographyBtn = new JRadioButton("Geography");
		categoryGroup = new ButtonGroup();
		categoryGroup.add(scienceBtn);
		categoryGroup.add(artBtn);
		categoryGroup.add(religionBtn);
		categoryGroup.add(historyBtn);
		categoryGroup.add(geographyBtn);
		scienceBtn.setSelected(true);
		addBookBtn = new JButton("Add Book!");
		addAuthorBtn = new JButton("Add author");
		addPublisherBtn = new JButton("Add publisher");
		authors = new JTextArea(3, 15);
		authors.setEditable(false);
		String[] boxOptions = new String[100];
		for (int i = 0, year = 1921; year <= 2020; year++, i++) {
			boxOptions[i] = "" + year;
		}
		yearComboBox = new JComboBox<>(boxOptions);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 8;
		gc.fill = GridBagConstraints.FIRST_LINE_START;
		add(newBookLabel, gc);
		gc.insets = new Insets(10, 10, 10, 10);
		gc.fill = GridBagConstraints.NONE;
		gc.gridy = 1;
		gc.gridx = 0;
		gc.gridwidth = 1;
		add(bookISBNLabel, gc);
		gc.gridy = 1;
		gc.gridx = 1;
		gc.gridwidth = 1;
		add(bookISBNField, gc);
		gc.gridy = 2;
		gc.gridx = 0;
		add(bookTitleLabel, gc);
		gc.gridy = 2;
		gc.gridx = 1;
		add(bookTitleField, gc);
		gc.gridy = 3;
		gc.gridx = 0;
		add(publisherNameLabel, gc);
		gc.gridy = 3;
		gc.gridx = 1;
		add(publisherNameField, gc);
		gc.gridy = 3;
		gc.gridx = 2;
		add(addPublisherBtn, gc);
		gc.gridy = 4;
		gc.gridx = 0;
		add(publicationYearLabel, gc);
		gc.gridy = 4;
		gc.gridx = 1;
		add(yearComboBox, gc);
		gc.gridy = 5;
		gc.gridx = 0;
		add(priceLabel, gc);
		gc.gridy = 5;
		gc.gridx = 1;
		add(priceField, gc);
		gc.gridy = 6;
		gc.gridx = 0;
		add(thresholdLabel, gc);
		gc.gridy = 6;
		gc.gridx = 1;
		add(thresholdField, gc);
		gc.gridy = 7;
		gc.gridx = 0;
		add(numberOfCopiesLabel, gc);
		gc.gridy = 7;
		gc.gridx = 1;
		add(numberOfCopiesField, gc);
		gc.gridy = 8;
		gc.gridx = 0;
		add(categoryLabel, gc);
		gc.gridy = 8;
		gc.gridx = 1;
		add(scienceBtn, gc);
		gc.gridy = 8;
		gc.gridx = 2;
		add(artBtn, gc);
		gc.gridy = 9;
		gc.gridx = 0;
		add(religionBtn, gc);
		gc.gridy = 9;
		gc.gridx = 1;
		add(historyBtn, gc);
		gc.gridy = 9;
		gc.gridx = 2;
		add(geographyBtn, gc);
		gc.gridy = 10;
		gc.gridx = 0;
		add(authorsLabel, gc);
		gc.gridy = 10;
		gc.gridx = 1;
		add(authorField, gc);
		gc.gridy = 10;
		gc.gridx = 2;
		add(addAuthorBtn, gc);
		gc.gridy = 11;
		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.gridheight = 3;
		add(new JScrollPane(authors), gc);
		gc.gridy = 14;
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.ipady = 30;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(addBookBtn, gc);
		addBookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String errorMsg = NewBookPanel.this.validateForm();
				if (errorMsg.equals("")) {
					int isbn = Integer.parseInt(bookISBNField.getText());
					int threshold = Integer.parseInt(thresholdField.getText());
					int numberOfCopies = Integer.parseInt(numberOfCopiesField.getText());
					int price = Integer.parseInt(priceField.getText());
					int year = Integer.parseInt(yearComboBox.getItemAt(yearComboBox.getSelectedIndex()));
					listener.eventOccured(new BookEvent(this, isbn, 
							bookTitleField.getText(), publisherNameField.getText(),
							year, price, Enum.valueOf(Category.class,
							getSelectedButtonText(categoryGroup).toUpperCase()),
							threshold, numberOfCopies));
				} else {
					JOptionPane.showMessageDialog(null, errorMsg, "Invalid input",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		addAuthorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				authors.setEditable(true);
				authors.append(authorField.getText() + "\n");
				authors.setEditable(false);
			}
		});
		addPublisherBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.eventOccured(e);
			}
		});
		this.setBackground(java.awt.Color.GRAY);
		addPublisherBtn.setBackground(java.awt.Color.orange);
		addAuthorBtn.setBackground(java.awt.Color.orange);
	}
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}
	
	public JButton getAddBookBtn() {
		return addBookBtn;
	}
	
	public JButton getAddPublisherBtn() {
		return addPublisherBtn;
	}
	
	private String validateForm() {
		StringBuilder errorMsg = new StringBuilder();
		if (bookISBNField.getText().isEmpty()) {
			errorMsg.append("ISBN field is mandatory\n");
		}
		try {
			int isbn = Integer.parseInt(bookISBNField.getText());
			if (isbn <= 0) throw new Exception();
		} catch(Exception e) {
			errorMsg.append("ISBN is not valid\n");
		}
		if (bookTitleField.getText().isEmpty()) {
			errorMsg.append("Title field is mandatory\n");
		}
		if (publisherNameField.getText().isEmpty()) {
			errorMsg.append("Publisher field is mandatory\n");
		}
		if (priceField.getText().isEmpty()) {
			errorMsg.append("Price field is mandatory\n");
		}
		try {
			int price = Integer.parseInt(priceField.getText());
			if (price <= 0) throw new Exception();
		} catch(Exception e) {
			errorMsg.append("Price is not valid\n");
		}
		if (thresholdField.getText().isEmpty()) {
			errorMsg.append("Threshold field is mandatory\n");
		}
		try {
			int threshold = Integer.parseInt(thresholdField.getText());
			if (threshold <= 0) throw new Exception();
		} catch(Exception e) {
			errorMsg.append("Threshold is not valid\n");
		}
		if (numberOfCopiesField.getText().isEmpty()) {
			errorMsg.append("Copies field is mandatory\n");
		}
		try {
			int numberOfCopies = Integer.parseInt(numberOfCopiesField.getText());
			if (numberOfCopies <= 0) throw new Exception();
		} catch(Exception e) {
			errorMsg.append("Number of copies is not valid\n");
		}
		if (authorField.getText().isEmpty()) {
			errorMsg.append("Author field is mandatory\n");
		}
		
		return errorMsg.toString();
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
	
	private static final long serialVersionUID = 1452871517467335603L;

}
