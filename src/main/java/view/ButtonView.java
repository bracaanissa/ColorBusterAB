package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.*;

/**
 * Simple button view that contains a New Game button, a levelSelector combo box,
 * and Pause/Resume buttons.
 */
public class ButtonView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton newGameButton;
	private JButton pauseButton;  // Declare the pause button
	private JButton resumeButton; // Declare the resume button
	private JButton exitButton;
	private JComboBox<String> levelSelector; // Specify the type for JComboBox
	private JLabel levelLabel;


	public ButtonView(ActionListener gameButtonListener, ActionListener levelListener,
					  ActionListener pauseListener, ActionListener resumeListener, ActionListener exitListener) {

		levelLabel = new JLabel("Min Matches: ");
		add(levelLabel);

		levelSelector = new JComboBox<>();
		levelSelector.addItem("3");
		levelSelector.addItem("4");
		levelSelector.addItem("5");
		levelSelector.addActionListener(levelListener);
		add(levelSelector);

		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(gameButtonListener);
		add(newGameButton);

		// Initialize and add the Pause button
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(pauseListener);
		add(pauseButton);

		// Initialize and add the Resume button
		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(resumeListener);
		add(resumeButton);

		exitButton = new JButton("Exit");
		exitButton.addActionListener(exitListener);
		add(exitButton);
	}
}
