package life.domacitempeh.mqttsensorcollector.scene.impl;

import life.domacitempeh.mqttsensorcollector.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static life.domacitempeh.mqttsensorcollector.xDoToolUtil.*;

public class BaseScene implements Scene {
    private static final Logger log = LoggerFactory.getLogger(BaseScene.class);

    @Override
    public Scene rotaryCv() {
        pageUp();

        return this;
    }

    @Override
    public Scene rotaryCCV() {
        pageDown();

        return this;
    }

    @Override
    public Scene rotaryClick() {
        click();

        return this;
    }

    @Override
    public Scene rotaryLongClick() {
        tabSwitchOn();

        return new TabSwitchScene();
    }

    @Override
    public Scene buttonRed() {
        return this;
    }

    @Override
    public Scene buttonRedLong() {
        return this;
    }

    @Override
    public Scene buttonGreen() {
        log.debug("base green click");

        pressCtrlT();

        return new NewTabScene();
    }

    @Override
    public Scene buttonGreenLong() {
        log.debug("base green long click");

        pressCtrlW();

        return new NewTabScene();
    }

    @Override
    public Scene buttonYellow() {
        log.debug("base yellow click");

        togglePlayYoutube();

        return this;
    }

    @Override
    public Scene buttonYellowLong() {
        pressF();

        return new FullscreenScene();
    }
}
