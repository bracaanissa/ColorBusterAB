package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;

/**
 * A very simple score panel.
 * Call updateScore and pass in the number of tiles removed to update the display.
 */
public class ScoreView extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel scoreLabel;
    private int saveScore;

    public ScoreView() {
        saveScore = 0;
        String s = "Score: " + Integer.toString(saveScore);
        scoreLabel = new JLabel(s);
        add(scoreLabel);
    }

    /**
     * Updates the score based on the number of tiles removed.
     * Each tile is worth 100 points.
     *
     * @param tiles the number of tiles removed
     */
    public void updateScore(int tiles) {
        int pointsPerTile = 100;
        saveScore += tiles * pointsPerTile;
        scoreLabel.setText("Score: " + saveScore);
    }

    /**
     * Resets the score to zero and updates the display.
     */
    public void clearScore() {
        saveScore = 0;
        scoreLabel.setText("Score: " + saveScore);
    }
}
