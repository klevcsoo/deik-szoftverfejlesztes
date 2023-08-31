package components;

import models.GateTile;

/**
 * The GateTileComponent interface represents the JavaFX component of a tile that has a gate on it.
 *
 * @see TileComponent
 * @see models.Tile
 */
@SuppressWarnings("unused")
public interface GateTileComponent extends TileComponent {

    /**
     * The width and height of the gate rectangle in pixels.
     */
    int GATE_RECT_SIZE = 60;

    /**
     * @return The {@link GateTile} model of the component.
     */
    @Override
    GateTile getModel();
}
