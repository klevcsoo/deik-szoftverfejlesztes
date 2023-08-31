package models;

import util.GameObjectColourProperty;

/**
 * The GateTile interface represents a tile on the game board that has one of the gates on it. Every
 * gate has a colour that can be one of the values defined by {@link util.GameObjectColour}.
 *
 * @see Tile
 */
public interface GateTile extends Tile {

    /**
     * @return a {@link GameObjectColourProperty} object that contains the colour of the gate
     */
    GameObjectColourProperty colourProperty();
}
