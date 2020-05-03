package model;

import utils.Category;

public class SearchQuery {
	
	private String bookTitle, publisherName;
	private Category category;
	private int fromYear, toYear, lowerPrice, upperPrice;
	
	public SearchQuery() {
		
	}
	
	public SearchQuery(String bookTitle, String publisherName, Category category, 
			int fromYear, int toYear, int lowerPrice, int upperPrice) {
		this.setBookTitle(bookTitle);
		this.setPublisherName(publisherName);
		this.setCategory(category);
		this.setFromYear(fromYear);
		this.setToYear(toYear);
		this.setLowerPrice(lowerPrice);
		this.setUpperPrice(upperPrice);
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getUpperPrice() {
		return upperPrice;
	}

	public void setUpperPrice(int upperPrice) {
		this.upperPrice = upperPrice;
	}

	public int getToYear() {
		return toYear;
	}

	public void setToYear(int toYear) {
		this.toYear = toYear;
	}

	public int getLowerPrice() {
		return lowerPrice;
	}

	public void setLowerPrice(int lowerPrice) {
		this.lowerPrice = lowerPrice;
	}

	public int getFromYear() {
		return fromYear;
	}

	public void setFromYear(int fromYear) {
		this.fromYear = fromYear;
	}
	
	
}
