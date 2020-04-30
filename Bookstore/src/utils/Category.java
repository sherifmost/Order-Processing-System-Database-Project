package utils;

public enum Category {
	SCIENCE("Science"), ART("Art"), RELIGION("Religion"), HISTORY("History"), GEOGRAPHY("Geography");
	private Category(String categoryName) {
		this.categoryName = categoryName;
	}

	private String categoryName;

	@Override
	public String toString() {
		return this.categoryName;

	}
}
