package view;

import java.util.EventObject;

public class SwitchEvent extends EventObject {
	private String nextFrame;
	
	public SwitchEvent(Object source) {
		super(source);
	}
	
	public SwitchEvent(Object source, String nextFrame) {
		super(source);
		this.nextFrame = nextFrame;
	}
	
	public String getNextFrame() {
		return nextFrame;
	}

	private static final long serialVersionUID = 5000424829264647933L;
	
}
