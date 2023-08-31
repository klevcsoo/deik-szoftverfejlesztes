package models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.jetbrains.annotations.NotNull;
import util.BoardPosition;
import util.BoardPositionProperty;
import util.GameObjectColourProperty;

public class TileImpl implements Tile {

    private final BooleanProperty northernWall, southernWall, westernWall, easternWall;
    private final GameObjectColourProperty ballColour;
    private final BoardPositionProperty positionProperty;

    public TileImpl() {
        positionProperty = new BoardPositionProperty();
        northernWall = new SimpleBooleanProperty(false);
        southernWall = new SimpleBooleanProperty(false);
        westernWall = new SimpleBooleanProperty(false);
        easternWall = new SimpleBooleanProperty(false);
        ballColour = new GameObjectColourProperty();
    }

    @Override
    public BooleanProperty northernWallProperty() {
        return northernWall;
    }

    @Override
    public BooleanProperty southernWallProperty() {
        return southernWall;
    }

    @Override
    public BooleanProperty westernWallProperty() {
        return westernWall;
    }

    @Override
    public BooleanProperty easternWallProperty() {
        return easternWall;
    }

    @Override
    public GameObjectColourProperty ballColourProperty() {
        return ballColour;
    }

    @Override
    public BoardPositionProperty boardPositionProperty() {
        return positionProperty;
    }

    @Override
    public int compareTo(@NotNull Tile o) {
        BoardPosition thisPos = positionProperty.getValue();
        BoardPosition otherPos = o.boardPositionProperty().getValue();

        if (thisPos.getRow() != otherPos.getRow()) {
            return Integer.compare(thisPos.getRow(), otherPos.getRow());
        } else {
            return Integer.compare(thisPos.getCol(), otherPos.getCol());
        }
    }
}
