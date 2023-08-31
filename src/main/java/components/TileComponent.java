package components;

import models.Tile;

/**
 * The TileComponent interface represents the JavaFX component of a game tile.
 *
 * @see Tile
 */
public interface TileComponent {

    /**
     * The radius of the ball circle in pixels.
     */
    int BALL_RADIUS = 25;

    /**
     * @return The {@link Tile} model of the component.
     */
    Tile getModel();
}
