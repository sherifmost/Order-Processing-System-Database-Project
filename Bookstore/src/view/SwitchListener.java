package view;

import java.util.EventListener;

public interface SwitchListener extends EventListener {
	public void switchEventOccurred(SwitchEvent e);
}
