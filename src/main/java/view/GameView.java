/**
 * 
 */
package view;
import javax.swing.JFrame;
import java.awt.*;

//import view.ScoreView;
//import view.ButtonView;
import view.BoardView;
import java.awt.event.*;


public class GameView extends JFrame {
	// Create the HUD Panel
	// Create the Board
	
	
	private static final long serialVersionUID = 1L;
	private ScoreView scoreView;
	private ButtonView buttonView;
	private BoardView boardView;
	private int score;
	private int threshold;
	private boolean isPaused = false;
	
	int rows = 8, cols = 8;
	int width, height;
	

	ActionListener newGameListener;
	ActionListener tileTouchedListener;
	ActionListener levelListener;
	
	/**
	 * @param title
	 * @throws HeadlessException
	 */


	public GameView(String title, int rows, int cols, int threshold, ActionListener newGameListener, ActionListener tileTouchedListener, ActionListener levelListener) {
		super(title);

		this.rows = rows;
		this.cols = cols;
		this.threshold = threshold;

		this.newGameListener = newGameListener;
		this.tileTouchedListener = tileTouchedListener;
		this.levelListener = levelListener; // Use the parameter directly

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		scoreView = new ScoreView();
		add(scoreView, BorderLayout.NORTH);

		buttonView = new ButtonView(e -> newGame(), levelListener, e -> pauseGame(), e -> resumeGame(),e -> exitGame());
		add(buttonView, BorderLayout.SOUTH);

		boardView = new BoardView(rows, cols, threshold, tileTouchedListener);  // Updated to include threshold
		add(boardView, BorderLayout.CENTER);

		updateLevel(threshold);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// Delegate to boardView
	public boolean isMoveAvailable() {
		// TODO you need to delegate this behavior to the boardView
		if (boardView.isMoveAvailable()){
			return true;
		}
		else {
			return false;
		}
	}
	
	// Call this method to start a new Game

	public void newGame() {
		// Ensure the current boardView and its tiles are removed from the container
		Container c = getContentPane();
		if (boardView != null) {
			c.remove(boardView);
		}
		scoreView.clearScore();

		// Create a new boardView for a new game
		boardView = new BoardView(rows, cols, threshold, tileTouchedListener);

		// Add the new boardView to the container
		c.add(boardView, BorderLayout.CENTER);

		// Validate the container and all of its subcomponents
		c.validate();

		// Repaint the container so that the new boardView is displayed
		c.repaint();

		System.out.println("New game started."); // For debugging
	}

	private void pauseGame() {
		isPaused = true;
		boardView.setPaused(true); // Explicitly pause the game
		System.out.println("Game paused");
	}

	private void resumeGame() {
		isPaused = false;
		boardView.setPaused(false); // Explicitly resume the game
		System.out.println("Game resumed");
	}

	private void exitGame() {
		// Hide/ dispose of the current game window
		this.dispose(); // Or this.setVisible(false); if you want to keep the state

		// Show the main menu again
		MainMenu mainMenu = new MainMenu();
		mainMenu.setVisible(true);
	}
	public void processTouchedTile(TileView tv) {
		// TODO
		// You need to implement this method. It is called when a tileview is touched
		System.out.println("GameView == processing tile touch");

		int x = boardView.processTouchedTile(tv);
		scoreView.updateScore(x);
		System.out.println(boardView); // debug
	}

	public void updateLevel(int threshold) {
		this.threshold = threshold;
		boardView.setThreshold(threshold);
		System.out.println("Threshold set to: " + threshold);
	}
	public boolean isPaused() {
		return boardView.isPaused();  // Ensure BoardView has a method that returns the current pause state.
	}
}
