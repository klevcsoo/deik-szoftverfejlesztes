package models;

import util.GameObjectColourProperty;

public class GateTileImpl extends TileImpl implements GateTile {

    private final GameObjectColourProperty colour;

    public GateTileImpl() {
        super();
        this.colour = new GameObjectColourProperty();
    }

    @Override
    public GameObjectColourProperty colourProperty() {
        return colour;
    }
}
