package models;

import javafx.beans.property.BooleanProperty;
import util.BoardPositionProperty;
import util.GameObjectColourProperty;

/**
 * The Tile interface represents a tile on a game board. A tile can be bounded by walls from the
 * north, south, west, and east.
 *
 * @see Board
 * @see GateTile
 */
public interface Tile extends Comparable<Tile> {

    /**
     * @return The {@link BooleanProperty} that controls whether the tile is bound by a wall on its
     * northern side.
     */
    BooleanProperty northernWallProperty();

    /**
     * @return The {@link BooleanProperty} that controls whether the tile is bound by a wall on its
     * southern side.
     */
    BooleanProperty southernWallProperty();

    /**
     * @return The {@link BooleanProperty} that controls whether the tile is bound by a wall on its
     * western side.
     */
    BooleanProperty westernWallProperty();

    /**
     * @return The {@link BooleanProperty} that controls whether the tile is bound by a wall on its
     * eastern side.
     */
    BooleanProperty easternWallProperty();

    /**
     * @return The {@link GameObjectColourProperty} that controls the colour of the ball on this
     * tile, if the ball is not on this tile, expect the value to be {@code null}.
     */
    GameObjectColourProperty ballColourProperty();

    /**
     * @return The {@link BoardPositionProperty} that controls the position of the tile on the
     * board.
     */
    BoardPositionProperty boardPositionProperty();
}
