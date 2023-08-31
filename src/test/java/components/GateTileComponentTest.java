package components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.GameObjectColour;

class GateTileComponentTest {

    private GateTileComponent gateTileComponent;

    @BeforeEach
    void setUp() {
        gateTileComponent = new GateTileComponentImpl();
    }

    @Test
    void getColourNoColour() {
        assertNull(gateTileComponent.getModel().colourProperty().getValue());
    }

    @Test
    void getColourBlue() {
        gateTileComponent.getModel().colourProperty().setValue(GameObjectColour.BLUE);
        assertEquals(GameObjectColour.BLUE,
            gateTileComponent.getModel().colourProperty().getValue());
    }

    @Test
    void getColourRed() {
        gateTileComponent.getModel().colourProperty().setValue(GameObjectColour.RED);
        assertEquals(GameObjectColour.RED,
            gateTileComponent.getModel().colourProperty().getValue());
    }
}
