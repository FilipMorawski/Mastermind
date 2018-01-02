package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import repositories.AvailableColours;

@SuppressWarnings("serial")
public class ColorPickerFrame extends JOptionPane {

	private JPanel mainPanel;
	private JLabel pick1Label, pick2Label, pick3Label, pick4Label;
	private JComboBox pick1, pick2, pick3, pick4;
	private String[] colorsToPicks;
	private String message;
	private List<String> coloursList;
	private List<JComboBox> boxList;

	public ColorPickerFrame() {
		mainPanel = new JPanel();
		coloursList = new ArrayList<String>();
		boxList = new ArrayList<JComboBox>();
		message = "Choose your four colors";

		pick1Label = new JLabel("Pick 1");
		pick2Label = new JLabel("Pick 2");
		pick3Label = new JLabel("Pick 3");
		pick4Label = new JLabel("Pick 4");

		List<String> availableColours = new AvailableColours().getList();
		colorsToPicks = new String[6];
		for (int i = 0; i < availableColours.size(); i++) {
			String colour = availableColours.get(i);
			colorsToPicks[i] = colour;
		}

		pick1 = new JComboBox(colorsToPicks);
		pick2 = new JComboBox(colorsToPicks);
		pick3 = new JComboBox(colorsToPicks);
		pick4 = new JComboBox(colorsToPicks);

		boxList.add(pick1);
		boxList.add(pick2);
		boxList.add(pick3);
		boxList.add(pick4);

		Box hBox = Box.createHorizontalBox();

		hBox.add(pick1Label, BorderLayout.CENTER);
		hBox.add(pick1, BorderLayout.CENTER);
		hBox.add(pick2Label, BorderLayout.CENTER);
		hBox.add(pick2, BorderLayout.CENTER);
		hBox.add(pick3Label, BorderLayout.CENTER);
		hBox.add(pick3, BorderLayout.CENTER);
		hBox.add(pick4Label, BorderLayout.CENTER);
		hBox.add(pick4, BorderLayout.CENTER);

		mainPanel.add(hBox);
		this.add(mainPanel);

		JOptionPane.showMessageDialog(null, mainPanel, "Pick colors to make code/guess", JOptionPane.QUESTION_MESSAGE);

	}

	public List<String> getColoursList() {
		for (JComboBox box : boxList) {
			String colour = (String) box.getSelectedItem();
			coloursList.add(colour);
		}
		return coloursList;
	}

}
