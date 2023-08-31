package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.GateTile;
import models.GateTileImpl;
import util.GameObjectColour;

public class GateTileComponentImpl extends TileComponentImpl implements GateTileComponent {

    private final Rectangle gate;
    private GameObjectColour colour;
    private Color originalFillColour;

    public GateTileComponentImpl() {
        super(new GateTileImpl());

        gate = new Rectangle();
        gate.setWidth(GATE_RECT_SIZE);
        gate.setHeight(GATE_RECT_SIZE);
        gate.setFill(Color.TRANSPARENT);
        getChildren().add(gate);
        gate.toBack();

        tile.ballColourProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == ((GateTile) tile).colourProperty().getValue()) {
                gate.setFill(Color.GREEN);
            } else {
                gate.setFill(originalFillColour);
            }
        });
    }

    @Override
    public GateTile getModel() {
        return (GateTile) tile;
    }

    @SuppressWarnings("unused")
    public GameObjectColour getColour() {
        return colour;
    }

    @SuppressWarnings("unused")
    public void setColour(GameObjectColour colour) {
        this.colour = colour;
        ((GateTileImpl) tile).colourProperty().setValue(colour);

        gate.setFill(colour == GameObjectColour.BLUE ? Color.BLUE :
            colour == GameObjectColour.RED ? Color.RED : Color.TRANSPARENT);

        if (originalFillColour == null) {
            originalFillColour = (Color) gate.getFill();
        }
    }
}
