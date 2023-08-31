package util;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import lombok.AllArgsConstructor;
import models.Board;

/**
 * The BoardKeyEventHandler is a custom {@link EventHandler} that is used to
 * handle the input from the main application window and control the game board.
 *
 * @see Board
 */
@AllArgsConstructor
public class BoardKeyEventHandler implements EventHandler<KeyEvent> {

    private final Board board;

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> board.moveNorth();
            case DOWN -> board.moveSouth();
            case LEFT -> board.moveWest();
            case RIGHT -> board.moveEast();
            default -> throw new IllegalArgumentException("Unexpected value: " + keyEvent.getCode());
        }
    }
}
