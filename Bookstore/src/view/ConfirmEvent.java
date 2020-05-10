package view;

import java.util.ArrayList;
import java.util.EventObject;

public class ConfirmEvent extends EventObject {

	private static final long serialVersionUID = 216887595747277843L;
	private ArrayList<Long> orders;

	public ConfirmEvent(Object source) {
		super(source);
	}

	public ConfirmEvent(Object source, ArrayList<Long> orders) {
		super(source);
		this.orders = orders;
	}

	ArrayList<Long> getOrders() {
		return orders;
	}

	void setOrders(ArrayList<Long> orders) {
		this.orders = orders;
	}

}
