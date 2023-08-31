package components;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TileComponentImplTest {

    private TileComponentImpl tileComponent;

    @BeforeEach
    void setUp() {
        tileComponent = new TileComponentImpl();
    }

    @Test
    void noWallsLinesOpacity() {
        double[] expected = new double[]{
            0.0, 0.0, 0.0, 0.0
        };
        double[] actual = new double[]{
            tileComponent.northernWall.opacityProperty().getValue(),
            tileComponent.southernWall.opacityProperty().getValue(),
            tileComponent.westernWall.opacityProperty().getValue(),
            tileComponent.easternWall.opacityProperty().getValue()
        };

        assertArrayEquals(expected, actual);
    }

    @Test
    void oneWallLinesOpacity() {
        tileComponent.setWest(true);

        assertEquals(1.0, tileComponent.westernWall.opacityProperty().getValue());
    }

    @Test
    void allWallsLinesOpacity() {
        tileComponent.setNorth(true);
        tileComponent.setSouth(true);
        tileComponent.setWest(true);
        tileComponent.setEast(true);

        double[] expected = new double[]{
            1.0, 1.0, 1.0, 1.0
        };
        double[] actual = new double[]{
            tileComponent.northernWall.opacityProperty().getValue(),
            tileComponent.southernWall.opacityProperty().getValue(),
            tileComponent.westernWall.opacityProperty().getValue(),
            tileComponent.easternWall.opacityProperty().getValue()
        };

        assertArrayEquals(expected, actual);
    }
}
