package life.domacitempeh.mqttsensorcollector.component.impl;

import org.junit.jupiter.api.Test;

public class HotkeyHandlerTest {

    @Test
    void testHotkey() {
        HotkeyHandler hotkeyHandler = new HotkeyHandler();
        HotkeyHandler.Dimension dimension = hotkeyHandler.togglePointerLocation();
        dimension = hotkeyHandler.togglePointerLocation();
        dimension = hotkeyHandler.togglePointerLocation();
        dimension = hotkeyHandler.togglePointerLocation();
        dimension = hotkeyHandler.togglePointerLocation();
        dimension = hotkeyHandler.togglePointerLocation();
        dimension = hotkeyHandler.togglePointerLocation();
        dimension = hotkeyHandler.togglePointerLocation();
    }
}