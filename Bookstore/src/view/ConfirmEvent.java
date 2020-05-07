package view;

import java.util.ArrayList;
import java.util.EventObject;

public class ConfirmEvent extends EventObject {
	
	private static final long serialVersionUID = 216887595747277843L;
	private ArrayList<Integer> orders;
	
	public ConfirmEvent(Object source) {
		super(source);
	}
	
	public ConfirmEvent(Object source, ArrayList<Integer> orders) {
		super(source);
		this.orders = orders;
	}
	
	ArrayList<Integer> getOrders() {
		return orders;
	}
	
	void setOrders(ArrayList<Integer> orders) {
		this.orders = orders;
	}

}
