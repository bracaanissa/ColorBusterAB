package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    @Test
    void shouldAssignValidColor() {
        Tile tile = new Tile(0, 0);
        assertTrue(tile.getColor() >= 0 && tile.getColor() < Tile.colors.length, "Color should be within valid range.");
    }

    @Test
    void shouldLoadImageCorrectly() {
        Tile tile = new Tile(0, 0);
        assertNotNull(tile.getImg(), "Image should not be null after initialization.");
    }

    @Test
    void gettersShouldReturnCorrectValues() {
        Tile tile = new Tile(0, 0);
        assertEquals(0, tile.getRow(), "Row getter should return correct value.");
        assertEquals(0, tile.getCol(), "Column getter should return correct value.");
    }

    @Test
    void settersShouldSetValuesCorrectly() {
        Tile tile = new Tile(0, 0);
        tile.setRow(1);
        assertEquals(1, tile.getRow(), "Row setter should set value correctly.");
        tile.setCol(1);
        assertEquals(1, tile.getCol(), "Column setter should set value correctly.");
    }

    @Test
    void statusShouldBeSetAndGetCorrectly() {
        Tile tile = new Tile(0, 0);
        tile.setStatus(1); // Assuming 1 is a valid status for testing
        assertEquals(1, tile.getStatus(), "Status should be set and retrieved correctly.");
    }
}
