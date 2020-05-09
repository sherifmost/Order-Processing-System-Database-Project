package view;

import java.util.EventObject;
import utils.Category;

public class BookEvent extends EventObject {
	private int ISBN, price, threshold, copies, publicationYear;
	private Category category;
	private String title;
	private String publisherName;
	private int quantity;
	private static final long serialVersionUID = -8246807540556498220L;

	public BookEvent(Object source) {
		super(source);
	}

	public BookEvent(Object source, int ISBN, String title, String publisherName, int publicationYear, int price,
			Category category, int threshold, int copies) {
		super(source);
		this.ISBN = ISBN;
		this.title = title;
		this.publisherName = publisherName;
		this.publicationYear = publicationYear;
		this.price = price;
		this.category = category;
		this.threshold = threshold;
		this.copies = copies;
	}

	public BookEvent(Object source, int ISBN, String title, String publisherName, int price, Category category,
			int copies, int quantity) {
		super(source);
		this.ISBN = ISBN;
		this.title = title;
		this.publisherName = publisherName;
		this.price = price;
		this.category = category;
		this.copies = copies;
		this.setQuantity(quantity);
	}

	// getters and setters
	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
