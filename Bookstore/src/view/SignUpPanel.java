package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignUpPanel extends JPanel {
	JLabel registrationFormLabel;

	public SignUpPanel() {
		 registrationFormLabel = new JLabel("Registration Form");
		 this.add(registrationFormLabel);
	}
	
	private static final long serialVersionUID = 7298764152390830707L;
}
