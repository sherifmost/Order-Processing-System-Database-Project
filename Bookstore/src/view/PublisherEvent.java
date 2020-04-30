package view;

import java.util.EventObject;

public class PublisherEvent extends EventObject {

	private String pubisherName, publisherAddress, publihserTelephone;

	public PublisherEvent(Object source) {
		super(source);
	}

	public PublisherEvent(Object source, String publisherName, String publisherAddress, String publisherTelephone) {
		super(source);
		setPubisherName(publisherName);
		setPublihserTelephone(publisherTelephone);
		setPublisherAddress(publisherAddress);
	}

	public String getPubisherName() {
		return pubisherName;
	}

	public void setPubisherName(String pubisherName) {
		this.pubisherName = pubisherName;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

	public String getPublihserTelephone() {
		return publihserTelephone;
	}

	public void setPublihserTelephone(String publihserTelephone) {
		this.publihserTelephone = publihserTelephone;
	}

	// Serial ID
	private static final long serialVersionUID = -7349500025124209641L;
}
