package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import utils.Category;

public class SearchPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel searchLabel, bookTitleLabel, filtersLabel, categoryLabel, publisherNameLabel, priceRangeLabel, yearRangeLabel;
	private JLabel toPriceLabel, toYearLable, selectedBookLabel, quantityLabel;
	private JTextField titleField, publisherNameField, lowerPrice, upperPrice, quantityField;
	private ButtonGroup categoryGroup;
	private JRadioButton scienceBtn, artBtn, religionBtn, historyBtn, geographyBtn;
	private JComboBox<String> fromYearComboBox, toYearComboBox;
	private JButton searchBtn, modifyBtn, addToCartBtn;
	private Listener listener;
	private TablePanel tablePanel;
	private boolean isManager;
	
	private int selectedISBN, availableQuantity;
	
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
		selectedBookLabel = new JLabel("No selected Book !");
		quantityLabel = new JLabel("select quantity:");

		
		// text fields
		titleField = new JTextField(30);
		publisherNameField = new JTextField(20);
		lowerPrice = new JTextField(6);
		upperPrice = new JTextField(6);
		quantityField = new JTextField(2);
		
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
		modifyBtn = new JButton("Modify Book");
		addToCartBtn = new JButton("Add To Cart");
		
		// table
		tablePanel = new TablePanel();
		
		
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
		gc.gridx = 0;
		gc.gridy++;
		gc.gridwidth = 4;
		gc.ipady = 100;
		add(tablePanel, gc);
	
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
				
				gc.gridx = 0;
				gc.gridy++;
				add(selectedBookLabel, gc);
				gc.gridx = 2;
				gc.fill = GridBagConstraints.HORIZONTAL;
				gc.ipady = 30;
				gc.ipadx = 60;
				if (isManager) {
						add(modifyBtn, gc);
					}
					else {
						add(addToCartBtn, gc);
						gc.gridy++;
						gc.gridx = 0;
						add(quantityLabel, gc);
						gc.gridx = 1;
						gc.ipady = 20;
						gc.ipadx = 20;
						add(quantityField, gc);
					}
				
				} else {
					JOptionPane.showMessageDialog(null, errorMsg, "Invalid search input",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		modifyBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		addToCartBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// we need to check that the entered value is integer
				int quantity = Integer.parseInt(quantityField.getText());
				if (quantity <= availableQuantity) {
					listener.eventOccured(new AddToCartEvent(this, selectedISBN, quantity));
				} else {
					JOptionPane.showMessageDialog(null, "we cannot support this amount of books !", "Invalid Operation",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		tablePanel.getTable().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JTable table = tablePanel.getTable();
				TableModel tableModel = tablePanel.getTableModel();
				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);
				String bookTitle = (String) tableModel.getValueAt(row, 0);
				selectedISBN = (int) tableModel.getValueAt(row, 1);
				availableQuantity = (int) tableModel.getValueAt(row, 3);
				selectedBookLabel.setText(bookTitle + "  -- ISBN: " + selectedISBN);
				
			}
		});
		
		
	}
	
	public TablePanel getTablePanel() {
		return tablePanel;
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

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	
	
	
}
