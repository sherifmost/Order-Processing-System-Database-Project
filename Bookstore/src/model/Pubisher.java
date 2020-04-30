package model;

public class Pubisher {
	// Data corresponding to the relation
	private String publisherName;
	private String telephone;
	private String address;

	// constructor
	public Pubisher(String publisherName, String telephone, String address) {
		setAddress(address);
		setPublisherName(publisherName);
		setTelephone(telephone);
	}

	// setters and getters
	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
