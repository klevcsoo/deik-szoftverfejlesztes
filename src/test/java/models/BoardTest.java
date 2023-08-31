package models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.BoardPosition;

class BoardTest {

    private static final String[][] wallMatrix = new String[][]{
        {"NW", "N", "NSW", "N", "NE"},
        {"W", "", "", "", "SE"},
        {"SW", "", "", "", "E"},
        {"W", "", "", "", "E"},
        {"SW", "S", "NSW", "S", "SE"}
    };
    private static List<Tile> tiles;

    private Board board;
    private Field bluePositionField, redPositionField;

    @BeforeAll
    static void beforeAll() {
        tiles = new ArrayList<>();
        for (int row = 0; row < wallMatrix.length; row++) {
            for (int col = 0; col < wallMatrix[row].length; col++) {
                Tile t = new TileImpl();
                t.boardPositionProperty().setValue(new BoardPosition(col, row));

                String[] walls = wallMatrix[row][col].split("");
                for (var wall : walls) {
                    if (wall.equals("N")) {
                        t.northernWallProperty().setValue(true);
                    }
                    if (wall.equals("S")) {
                        t.southernWallProperty().setValue(true);
                    }
                    if (wall.equals("W")) {
                        t.westernWallProperty().setValue(true);
                    }
                    if (wall.equals("E")) {
                        t.easternWallProperty().setValue(true);
                    }
                }

                tiles.add(t);
            }
        }
    }

    @SneakyThrows
    @BeforeEach
    void setUp() {
        board = new BoardImpl(tiles);

        bluePositionField = BoardImpl.class.getDeclaredField("bluePosition");
        bluePositionField.setAccessible(true);
        redPositionField = BoardImpl.class.getDeclaredField("redPosition");
        redPositionField.setAccessible(true);
    }

    @SneakyThrows
    @Test
    void moveNorth() {
        board.moveNorth();
        var bluePos = (BoardPosition) bluePositionField.get(board);
        var redPos = (BoardPosition) redPositionField.get(board);

        assertEquals(0, bluePos.getRow());
        assertEquals(2, redPos.getRow());
    }

    @SneakyThrows
    @Test
    void moveSouth() {
        board.moveSouth();
        var bluePos = (BoardPosition) bluePositionField.get(board);
        var redPos = (BoardPosition) redPositionField.get(board);

        assertEquals(1, bluePos.getRow());
        assertEquals(4, redPos.getRow());
    }

    @SneakyThrows
    @Test
    void moveWest() {
        board.moveWest();
        var bluePos = (BoardPosition) bluePositionField.get(board);
        var redPos = (BoardPosition) redPositionField.get(board);

        assertEquals(0, bluePos.getCol());
        assertEquals(0, redPos.getCol());
    }

    @SneakyThrows
    @Test
    void moveEast() {
        board.moveEast();
        var bluePos = (BoardPosition) bluePositionField.get(board);
        var redPos = (BoardPosition) redPositionField.get(board);

        assertEquals(4, bluePos.getCol());
        assertEquals(4, redPos.getCol());
    }

    @Test
    void isPuzzleCompleted() {
        for (int i = 0; i < 3; i++) {
            board.moveSouth();
            board.moveWest();
            board.moveNorth();
            board.moveEast();
        }

        assertTimeout(Duration.ofSeconds(2),
            () -> board.setPuzzleCompletedHandler(() -> assertTrue(true)));
    }
}
