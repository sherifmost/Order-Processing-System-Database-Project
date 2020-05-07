package view;

import java.util.EventObject;

public class PromotionEvent extends EventObject {

	private String userName;

	public PromotionEvent(Object source) {
		super(source);
	}

	public PromotionEvent(Object source, String userName) {
		super(source);
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	private static final long serialVersionUID = 2258215018245595234L;

}
