package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import view.GameView;
import view.TileView;

/**
 * Controls the game logic and UI updates.
 */
public class GameController {
	private int score;
	private int gameStatus;
	private int rows = 8;
	private int cols = 9;
	private final int DEFAULT_THRESHOLD = 3;
	private int moveNumber = 0;
	private GameView gameView;
	private int threshold;

	/**
	 * Constructor that sets up the game controller with default settings.
	 */
	public GameController() {
		threshold = DEFAULT_THRESHOLD; // Default threshold value
	}

	/**
	 * Starts a new game by initializing and displaying the GameView.
	 */
	public void startNewGame() {
		gameView = new GameView("Color Buster", rows, cols, threshold,
				new NewGameListener(),
				new TileTouchedListener(),
				new LevelListener());
		gameView.setVisible(true);
	}

	// Listener used to process touches on TileView
	class TileTouchedListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (gameView.isPaused()) {
				System.out.println("Game is paused. Ignoring tile touch.");
				return;
			}

			TileView tv = (TileView) event.getSource();
			System.out.println("Tile touched..." + tv.toString());
			gameView.processTouchedTile(tv);

			if (!gameView.isMoveAvailable()) {
				JOptionPane.showMessageDialog(gameView, "No more moves...");
			}
		}
	}

	// Listener used to process click on New Game Button
	class NewGameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Starting new game...");
			gameView.newGame(); // Calls newGame on the existing GameView instance.
		}
	}

	// Listener for changing game levels
	class LevelListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			System.out.println("Changing level...");
			JComboBox<String> cb = (JComboBox<String>) event.getSource();
			String level = cb.getSelectedItem().toString();
			threshold = Integer.parseInt(level);
			gameView.updateLevel(threshold);
			System.out.println("Level Changed To: " + level);
		}
	}
}
