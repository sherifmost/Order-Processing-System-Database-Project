package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class NewBookPanel extends JPanel {
	private JLabel bookISBNLabel, bookTitleLabel,
		publisherNameLabel, publicationYearLabel, priceLabel,
		thresholdLabel, numberOfCopiesLabel, categoryLabel, newBookLabel;
	private JTextField bookISBNField, bookTitleField,
		publisherNameField, priceField, thresholdField,
		numberOfCopiesField;
	private JRadioButton scienceBtn, artBtn, religionBtn, historyBtn, geographyBtn;
	private ButtonGroup categoryGroup;
	private JComboBox<?> yearComboBox;
	private JButton addBookBtn;
	private Listener listener;
	
	public NewBookPanel() {
		newBookLabel = new JLabel("New Book");
		bookISBNLabel = new JLabel("Book ISBN");
		bookTitleLabel = new JLabel("Book title");
		publisherNameLabel = new JLabel("Publisher name");
		publicationYearLabel = new JLabel("Publication year");
		priceLabel = new JLabel("Price");
		thresholdLabel = new JLabel("Threshold");
		numberOfCopiesLabel = new JLabel("Number of copies");
		categoryLabel = new JLabel("Category");
		bookISBNField = new JTextField(15);
		bookTitleField = new JTextField(15);
		publisherNameField = new JTextField(15);
		priceField = new JTextField(15);
		thresholdField = new JTextField(15);
		numberOfCopiesField = new JTextField(15);
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
		addBookBtn = new JButton("Add Book!");
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
		gc.gridx = 1;
		gc.gridwidth = 2;
		gc.ipady = 30;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(addBookBtn, gc);
		addBookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.eventOccured(e);
			}
		});
		this.setBackground(java.awt.Color.CYAN);
	}
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}
	
	public JButton getAddBookBtn() {
		return addBookBtn;
	}
	private static final long serialVersionUID = 1452871517467335603L;

}
