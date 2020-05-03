package view;

import java.util.EventObject;

import utils.Category;

public class SearchEvent extends EventObject{
	
	private static final long serialVersionUID = 1L;
	private String bookTitle, publisherName;
	private Category category;
	private int lowerPrice, upperPrice, fromYear, toYear;
	
	public SearchEvent(Object source) {
		super(source);
	}
	
	public SearchEvent(Object source, String bookTitle, Category category, String publisherName,
			int lowerPrice, int upperPrice, int fromYear, int toYear) {
		super(source);
		this.setBookTitle(bookTitle);
		this.setCategory(category);
		this.setPublisherName(publisherName);
		this.setLowerPrice(lowerPrice);
		this.setUpperPrice(upperPrice);
		this.setFromYear(fromYear);
		this.setToYear(toYear);
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getFromYear() {
		return fromYear;
	}

	public void setFromYear(int fromYear) {
		this.fromYear = fromYear;
	}

	public int getToYear() {
		return toYear;
	}

	public void setToYear(int toYear) {
		this.toYear = toYear;
	}

	public int getUpperPrice() {
		return upperPrice;
	}

	public void setUpperPrice(int upperPrice) {
		this.upperPrice = upperPrice;
	}

	public int getLowerPrice() {
		return lowerPrice;
	}

	public void setLowerPrice(int lowerPrice) {
		this.lowerPrice = lowerPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}