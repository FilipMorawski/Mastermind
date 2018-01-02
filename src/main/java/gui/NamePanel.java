package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NamePanel extends JPanel {

	private final String DEFAULTNAME = "Unnamed Player";
	private JPanel mainPanel;
	private JTextField nameField;
	private JLabel label;
	private String name;

	public NamePanel() {
		mainPanel = new JPanel();
		label = new JLabel("Enter your name: ");
		nameField = new JTextField(15);
		nameField.setCaretPosition(0);
		mainPanel.add(label);
		mainPanel.add(nameField);
		this.add(mainPanel);
	}

	public String getName() {
		if (nameField.getText() != null) {
			name = nameField.getText();
		} else {
			name = DEFAULTNAME;
		}
		return name;
	}
}
