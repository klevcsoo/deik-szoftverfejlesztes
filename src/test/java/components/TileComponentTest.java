package components;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class TileComponentTest {

    @Test
    void getModel() {
        TileComponent tileComponent = new TileComponentImpl();
        assertNotEquals(null, tileComponent.getModel());
    }
}
