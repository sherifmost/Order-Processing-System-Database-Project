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
import javax.swing.JTextField;

import utils.Category;


public class SearchPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel searchLabel, bookTitleLabel, filtersLabel, categoryLabel, publisherNameLabel, priceRangeLabel, yearRangeLabel;
	private JLabel toPriceLabel, toYearLable;
	private JTextField titleField, publisherNameField, lowerPrice, upperPrice;
	private ButtonGroup categoryGroup;
	private JRadioButton scienceBtn, artBtn, religionBtn, historyBtn, geographyBtn;
	private JComboBox<String> fromYearComboBox, toYearComboBox;
	private JButton searchBtn;
	private Listener listener;
	
	public SearchPanel() {
		// labels
		searchLabel = new JLabel("SEARCH BOOKS");
		bookTitleLabel = new JLabel("Book Title");
		filtersLabel = new JLabel("Optional Filters");
		categoryLabel = new JLabel("Category");
		publisherNameLabel = new JLabel("Publisher Name");
		priceRangeLabel = new JLabel("Price Range");
		yearRangeLabel = new JLabel("Year Range");
		toPriceLabel = new JLabel("TO");
		toYearLable = new JLabel("TO");
		
		// text fields
		titleField = new JTextField(30);
		publisherNameField = new JTextField(20);
		lowerPrice = new JTextField(6);
		upperPrice = new JTextField(6);
		
		// radio buttons
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
		
		// comboBoxes
		String[] yearOptions = new String[100];
		for (int i = 0, year = 1921; year <= 2020; year++, i++) {
			yearOptions[i] = "" + year;
		}
		fromYearComboBox = new JComboBox<>(yearOptions);
		toYearComboBox = new JComboBox<>(yearOptions);
		fromYearComboBox.setSelectedIndex(0);
		toYearComboBox.setSelectedIndex(yearOptions.length - 1);
		
		// button
		searchBtn = new JButton("SEARCH");
		
		// set layout
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 0;
		gc.fill = GridBagConstraints.FIRST_LINE_START;

		add(searchLabel, gc);
		gc.insets = new Insets(10, 10, 10, 10);
		gc.fill = GridBagConstraints.NONE;
		gc.gridy = 1;
		gc.gridx = 0;
		gc.gridwidth = 1;;
		
		add(bookTitleLabel, gc);
		gc.gridy = 1;
		gc.gridx = 1;
		gc.gridwidth = 1;
		add(titleField, gc);
		gc.gridy = 2;
		gc.gridx = 0;
		add(filtersLabel, gc);
		gc.gridy = 3;
		gc.gridx = 0;
		add(categoryLabel, gc);
		gc.gridy = 4;
		gc.gridx = 0;
		add(scienceBtn, gc);
		gc.gridy = 4;
		gc.gridx = 1;
		add(historyBtn, gc);
		gc.gridy = 4;
		gc.gridx = 2;
		add(geographyBtn, gc);
		gc.gridy = 5;
		gc.gridx = 0;
		add(artBtn, gc);
		gc.gridy = 5;
		gc.gridx = 1;
		add(religionBtn, gc);
		gc.gridy = 6;
		gc.gridx = 0;
		add(publisherNameLabel, gc);
		gc.gridy = 6;
		gc.gridx = 1;
		add(publisherNameField, gc);
		gc.gridy = 7;
		gc.gridx = 0;
		add(yearRangeLabel, gc);
		gc.gridy = 7;
		gc.gridx = 1;
		add(fromYearComboBox, gc);
		gc.gridy = 7;
		gc.gridx = 2;
		add(toYearLable, gc);
		gc.gridy = 7;
		gc.gridx = 3;
		add(toYearComboBox, gc);
		gc.gridy = 8;
		gc.gridx = 0;
		add(priceRangeLabel, gc);
		gc.gridy = 8;
		gc.gridx = 1;
		add(lowerPrice, gc);
		gc.gridy = 8;
		gc.gridx = 2;
		add(toPriceLabel, gc);
		gc.gridy = 8;
		gc.gridx = 3;
		add(upperPrice, gc);
		gc.gridx = 0;
		gc.gridy++;
		gc.ipady = 30;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(searchBtn, gc);
	
		// search button action
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String errorMsg = validateSearchForm();
				if (errorMsg == "") {
				int lowerP = Integer.parseInt(lowerPrice.getText());
				int upperP = Integer.parseInt(upperPrice.getText());
				int from = Integer.parseInt(fromYearComboBox.getItemAt(fromYearComboBox.getSelectedIndex()));
				int to = Integer.parseInt(toYearComboBox.getItemAt(toYearComboBox.getSelectedIndex()));
				
				listener.eventOccured(new SearchEvent(this, titleField.getText(), Enum.valueOf(Category.class,
						getSelectedButtonText(categoryGroup).toUpperCase()), publisherNameField.getText(),
						lowerP, upperP, from, to));
				} else {
					JOptionPane.showMessageDialog(null, errorMsg, "Invalid search input",
							JOptionPane.ERROR_MESSAGE);
				}
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
	
	public String validateSearchForm() {
		String errorMsg = "";
		if (titleField.getText().isEmpty()) {
			errorMsg = "Book title is missed !";
		}
		if (publisherNameField.getText().isEmpty()) {
			publisherNameField.setText("none");
		}
		if (lowerPrice.getText().isEmpty()) {
			lowerPrice.setText("0");
		}
		if (upperPrice.getText().isEmpty()) {
			upperPrice.setText("999999");
		}
		
		return errorMsg;
	}
	
	
	
}
