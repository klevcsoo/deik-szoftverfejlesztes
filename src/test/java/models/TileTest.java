package models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.BoardPosition;

class TileTest {

    private static Tile thisTile;

    @BeforeAll
    static void beforeAll() {
        thisTile = new TileImpl();
        thisTile.boardPositionProperty().setValue(new BoardPosition(2, 3));
    }

    @Test
    void compareToSmaller() {
        Tile otherTile = new TileImpl();
        otherTile.boardPositionProperty().setValue(new BoardPosition(0, 2));

        assertEquals(1, thisTile.compareTo(otherTile));
    }

    @Test
    void compareToLarger() {
        Tile otherTile = new TileImpl();
        otherTile.boardPositionProperty().setValue(new BoardPosition(4, 4));

        assertEquals(-1, thisTile.compareTo(otherTile));
    }

    @Test
    void compareToEqual() {
        Tile otherTile = new TileImpl();
        otherTile.boardPositionProperty().setValue(new BoardPosition(2, 3));

        assertEquals(0, thisTile.compareTo(otherTile));
    }
}
