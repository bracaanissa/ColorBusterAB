package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.stream.IntStream;

public class BoardTest {
    private Board board;
    private final int rows = 8; // Assuming a fixed size for the board
    private final int cols = 8;
    private final int threshold = 3; // Assuming a fixed threshold for matching tiles

    @BeforeEach
    void setUp() {
        board = new Board(rows, cols, threshold);
    }

    @Test
    void boardShouldNotBeEmptyAfterInitialization() {
        IntStream.range(0, rows).forEach(row ->
            IntStream.range(0, cols).forEach(col ->
                assertNotNull(board.tileAt(row, col), "Tile should not be null after initialization.")
        ));
    }

    @Test
    void shouldProperlyDetectTileExistence() {
        assertTrue(board.hasTileAt(0, 0), "Tile existence check failed.");
        board.removeTileAt(0, 0);
        assertFalse(board.hasTileAt(0, 0), "Tile removal check failed.");
    }

    @Test
    void shouldProperlyRemoveMatchingTiles() {
        HashSet<Tile> matches = new HashSet<>();
        matches.add(board.tileAt(0, 0));
        matches.add(board.tileAt(1, 0));
        matches.add(board.tileAt(2, 0));
        board.removeMatchingTiles(matches);
        matches.forEach(tile -> assertNull(board.tileAt(tile.getRow(), tile.getCol()), "Matching tile was not removed."));
    }

    @Test
    void shouldProperlyCollapseColumn() {
        // Create a situation where a column collapse is needed
        board.removeTileAt(0, 0);
        board.removeTileAt(1, 0);
        board.removeTileAt(2, 0);
        board.collapseColumns();
        // Assume collapseColumns calls collapseColumn internally for each column
        IntStream.range(0, rows - 3).forEach(row -> 
            assertNotNull(board.tileAt(row, 0), "Tile should have collapsed into empty space.")
        );
    }

    @Test
    void shouldLocateNeighborsCorrectly() {
        // Manually create a matching condition
        int matchColor = Tile.colors.length - 1; // Example to set a specific color
        board.tileAt(0, 0).setColor(matchColor);
        board.tileAt(0, 1).setColor(matchColor);
        board.tileAt(0, 2).setColor(matchColor);

        HashSet<Tile> matches = new HashSet<>();
        board.locateNeighbors(0, 0, matchColor, matches);
        assertTrue(matches.size() >= threshold, "Should locate enough neighbors for a match.");
    }


    @Test
    void shouldDetectMoveAvailability() {
        assertTrue(board.isMoveAvailable(), "Board should have at least one available move.");
    }
}
