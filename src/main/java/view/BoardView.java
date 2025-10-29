/**
 *
 */
package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

import Model.Board;
import view.TileView;
import Model.Tile;

public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;
	private Board b;
	private int rows;
	private int cols;
	private int tileSize;
	private ActionListener listener;
	private int spacer;
	private boolean isPaused = false;

	private TileView [][] tileGrid;
	private int threshold;

	private ActionListener tileTouchedListener;
	// Create  BoardView with rows and cols and lis is the actionListener for all the TileViews

	public BoardView(int rows, int cols, int threshold, ActionListener lis) {
	    spacer = 4;
		listener = lis;
		Dimension s = getPreferredSize();
		System.out.println("BoardView: " + s);
		this.rows = rows;
		this.cols = cols;
		this.tileTouchedListener = lis;


		int totalSpace = (cols+1) * spacer;
		System.out.println("totalspace : " + totalSpace);

		tileSize = (s.width-totalSpace) / cols;
		System.out.println("TileSize : " + tileSize);

		// Set a default tile size if preferred size is not set
		tileSize = 50; // example default size
		setPreferredSize(new Dimension(tileSize * cols, tileSize * rows));

		// I'm not using a layout manager here since I'm using XY to layout the TileViews
		// Quick and simple

		setLayout(null);
		tileGrid = new TileView[rows][cols];
		b = new Board(rows, cols, threshold);

		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (isPaused) return;  // Only process clicks if BoardView is enabled
				super.mouseClicked(e);
			}
		};

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				TileView tv = new TileView(b.tileAt(row, col));
				tv.addMouseListener(mouseAdapter);  // Use the same listener for all tiles
				tileGrid[row][col] = tv;
				setupTileView(tv, row, col); // Setup position and size
				add(tv);
			}
		}
	}
	private void setupTileView(TileView tv, int row, int col) {
		int tileX = tileSize * col + spacer;
		int tileY = tileSize * ((rows - 1) - row) + spacer;
		tv.setBounds(tileX, tileY, tileSize, tileSize);
		tv.addListener(listener);
	}

	public void setPaused(boolean paused) {
		this.isPaused = paused;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				TileView tv = tileGrid[row][col];
				if (tv != null) {
					tv.setEnabled(!paused); // Properly enable/disable based on pause state
				}
			}
		}
		System.out.println(paused ? "Game paused" : "Game resumed");
	}



// Call this method whenever you want to update the boardView on the display from the current status of the board

	public void updateBoardViewFromBoard() {
		System.out.println("In updateBoardViewFromBoard...");
		TileView tv;
		removeAll();
		tileGrid = new TileView[rows][cols];
		for (int row=0; row<rows; row++) {
			for (int col=0; col<cols; col++) {
				if (b.hasTileAt(row,col)) {
					 tv = new TileView(b.tileAt(row, col));
				}
				else  {
					tv = new TileView(row,col);

				}
				add(tv);
				tileGrid[row][col] = tv;


				tv.setBounds((tileSize * col + spacer), (tileSize * ((rows-1)-row) + spacer),
							             tileSize, tileSize);
				tv.setPosition(tileSize * col + spacer, (tileSize * ((rows-1)-row) + spacer));

				tv.addListener(listener);


			}
		}


	}


	// returns the score which would be however you decide to score matched tiles.
	// You don't have to handle the score this way -- do it however you decide works best for you


	public int processTouchedTile(TileView tv) {
		HashSet<Tile> matches = new HashSet<Tile>();
		b.locateNeighbors(tv.getRow(), tv.getCol(), tv.getColor(), matches);
		//if (matches.size() >= 3){
		if (matches.size() >= threshold){
			b.removeMatchingTiles(matches);
			b.collapseColumns();

			// Don't forget to call updateBoardViewFromBoard() at the end of this method
			updateBoardViewFromBoard();
			System.out.println(b);

			// return 100; // you figure out the score
			return matches.size();
		}
		else {
			return 0;
		}
	}


	public String toString() {
		return b.toString();

		// Just call b.toString().
		// The commented code below you can use to verify that the BoardView is actually synchronized with the Board

//		String v = "";
//		for (int row=rows-1;row>=0;row--) {
//			for (int col=0;col<cols;col++)  {
//				if (tileGrid[row][col] != null)
//					v += tileGrid[row][col].toString();
//				else
//					v += "[-----]";
//			}
//			v += "\n";
//		}	return v + b.toString();
	}


	public boolean isMoveAvailable() {
		return b.isMoveAvailable();
	}

	public Dimension getPreferredSize() {
		return new Dimension(400,400);
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public boolean isPaused() {
		return this.isPaused;  // Assuming there is an isPaused field that tracks the pause state.
	}

}
