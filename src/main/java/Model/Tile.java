package Model;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.util.Random;



/**
 * Represents a tile in the ColorBuster game, including its color, position, and associated image.
 */
public class Tile { 
    public static String[] colors = {
            "Images/Green.jpg",
            "Images/Blue.jpg",
            "Images/Cyan.jpg",
            "Images/Red.jpg",
            "Images/Yellow.jpg"
    };
    
    private int row;
    private int col;
    private Image img;
    private int color;
    private int status; // This is going to be used to track tile state, e.g., normal, selected, etc.

    /**
     * Creates a Tile with specified row and column positions.
     * Randomly assigns a color and loads the corresponding image.
     * 
     * @param row The row position of the tile.
     * @param col The column position of the tile.
     */
    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.color = randInt(0, colors.length - 1);
        this.status = 0; // Default status
        
        loadImage();
    }

    // Method to set the tile's color
    public void setColor(int color) {
        this.color = color;
        // Update the image associated with the new color
        loadImage(); // Assuming loadImage uses the this.color field
    }

    // Error handling for uploading image of tiles.
    private void loadImage() {
        try {
            img = ImageIO.read(new FileImageInputStream(new File(colors[this.color])));
        } catch (IOException ex) {
            System.err.println("Error loading image for tile: " + ex.getMessage());
            img = null; 
        }
    }

    // Getters and Setters
    public int getRow() { 
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Image getImg() {
        return img;
    }

    public int getColor() {
        return color;
    }

    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        // Method to return tile information and its color abbrev.
        String[] colorAbbr = {"G", "B", "C", "R", "Y"};
        return String.format("[%d,%d,%s]", row, col, colorAbbr[color]);
    }
    
    /**
     * Generating a random int between the min and max int.
     * 
     * @param min min value.
     * @param max max value.
     * @return Random int between the min and the max.
     */
    private static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
