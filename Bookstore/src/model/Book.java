package model;

import utils.Category;

public class Book {
	// Data corresponding to the book relation
	private long ISBN;
	private String title;
	private String publisherName;
	private int publicationYear;
	private float price;
	private Category category;
	private int threshold;
	private int copies;

	// Constructor
	public Book() {

	}

	public Book(long ISBN, String title, String publisherName, int publicationYear, int price, Category category,
			int threshold, int copies) {
		setISBN(ISBN);
		setTitle(title);
		setPublisherName(publisherName);
		setPublicationYear(publicationYear);
		setPrice(price);
		setCategory(category);
		setThreshold(threshold);
		setCopies(copies);
	}

	// getters and setters
	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long iSBN) {
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
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
}
