package util;

import javafx.beans.property.SimpleObjectProperty;

/**
 * The BoardPositionProperty class provides an implementation of the {@link BoardPosition} class as
 * a {@link javafx.beans.property.ObjectProperty}.
 *
 * @see BoardPosition
 */
public class BoardPositionProperty extends SimpleObjectProperty<BoardPosition> {

    /**
     * Constructs a new BoardPositionProperty with an initial value.
     *
     * @param initialValue The initial value.
     */
    public BoardPositionProperty(BoardPosition initialValue) {
        super(initialValue);
    }

    /**
     * Constructs a new BoardPositionProperty with the default value.
     */
    public BoardPositionProperty() {
        this(new BoardPosition());
    }
}
