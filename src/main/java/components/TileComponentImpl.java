package components;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import models.Tile;
import models.TileImpl;
import util.GameObjectColour;

public class TileComponentImpl extends StackPane implements TileComponent {

    protected final Tile tile;
    private final Circle ball;
    @SuppressWarnings("unused")
    @FXML
    public Line northernWall;
    @SuppressWarnings("unused")
    @FXML
    public Line southernWall;
    @SuppressWarnings("unused")
    @FXML
    public Line westernWall;
    @SuppressWarnings("unused")
    @FXML
    public Line easternWall;

    public TileComponentImpl(Tile tile) {
        FXMLLoader fxmlLoader = new FXMLLoader(
            getClass().getResource("/components/tile-component.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(TileComponentImpl.this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ball = new Circle(0, 0, BALL_RADIUS, Color.TRANSPARENT);
        getChildren().add(ball);
        this.tile = tile;

        initProperties();
    }

    public TileComponentImpl() {
        this(new TileImpl());
    }

    public boolean isNorth() {
        return tile.northernWallProperty().get();
    }

    @SuppressWarnings("unused")
    public void setNorth(boolean north) {
        tile.northernWallProperty().set(north);
    }

    public boolean isSouth() {
        return tile.southernWallProperty().get();
    }

    @SuppressWarnings("unused")
    public void setSouth(boolean south) {
        tile.southernWallProperty().set(south);
    }

    public boolean isWest() {
        return tile.westernWallProperty().get();
    }

    @SuppressWarnings("unused")
    public void setWest(boolean west) {
        tile.westernWallProperty().set(west);
    }

    public boolean isEast() {
        return tile.easternWallProperty().get();
    }

    @SuppressWarnings("unused")
    public void setEast(boolean east) {
        tile.easternWallProperty().set(east);
    }

    private void initProperties() {
        tile.northernWallProperty()
            .addListener((observableValue, aBoolean, t1) -> northernWall.setOpacity(
                observableValue.getValue() ? 1 : 0));

        tile.southernWallProperty()
            .addListener((observableValue, aBoolean, t1) -> southernWall.setOpacity(
                observableValue.getValue() ? 1 : 0));

        tile.westernWallProperty()
            .addListener((observableValue, aBoolean, t1) -> westernWall.setOpacity(
                observableValue.getValue() ? 1 : 0));

        tile.easternWallProperty()
            .addListener((observableValue, aBoolean, t1) -> easternWall.setOpacity(
                observableValue.getValue() ? 1 : 0));

        tile.ballColourProperty()
            .addListener((observableValue, gameObjectColour, t1) -> ball.setFill(
                observableValue.getValue() == GameObjectColour.BLUE ? Color.BLUE :
                    observableValue.getValue() == GameObjectColour.RED ? Color.RED :
                        Color.TRANSPARENT));
    }

    @Override
    public Tile getModel() {
        return tile;
    }
}
