package util;

import javafx.beans.property.SimpleObjectProperty;

/**
 * The GameObjectColourProperty class provides an implementation of the {@link GameObjectColour}
 * enum as a {@link javafx.beans.property.ObjectProperty}.
 *
 * @see GameObjectColour
 */
public class GameObjectColourProperty extends SimpleObjectProperty<GameObjectColour> {

    /**
     * Constructs a new {@code GameObjectColourProperty} instance with the given initial value.
     *
     * @param initialValue the initial value
     */
    public GameObjectColourProperty(GameObjectColour initialValue) {
        super(initialValue);
    }

    /**
     * Constructs a new {@code GameObjectColourProperty} with null as the initial value.
     */
    public GameObjectColourProperty() {
        this(null);
    }
}
