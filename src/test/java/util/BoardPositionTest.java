package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BoardPositionTest {

    @Test
    void copyFrom() {
        BoardPosition from = new BoardPosition(2, 4);
        BoardPosition to = new BoardPosition(0, 3);

        assertEquals(0, to.getCol());
        assertEquals(3, to.getRow());

        to.copyFrom(from);

        assertEquals(2, to.getCol());
        assertEquals(4, to.getRow());
    }
}
