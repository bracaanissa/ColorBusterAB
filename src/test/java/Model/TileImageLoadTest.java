package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileImageLoadTest {

    @Test
    public void tileImageShouldLoad() {
        Tile tile = new Tile(0, 0);
        assertNotNull(tile.getImg(), "Image should be loaded for the tile");
    }
}
