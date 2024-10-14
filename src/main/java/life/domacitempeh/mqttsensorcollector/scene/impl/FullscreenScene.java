package life.domacitempeh.mqttsensorcollector.scene.impl;

import life.domacitempeh.mqttsensorcollector.model.EFullscreenRotaryFunction;
import life.domacitempeh.mqttsensorcollector.scene.Scene;
import lombok.extern.slf4j.Slf4j;

import static life.domacitempeh.mqttsensorcollector.xDoToolUtil.*;

@Slf4j
public class FullscreenScene extends BaseScene {
    private EFullscreenRotaryFunction rotaryFunction = EFullscreenRotaryFunction.SEEK;

    @Override
    public Scene rotaryClick() {
        rotaryFunction = rotaryFunction.next();

        return this;
    }

    @Override
    public Scene rotaryCv() {
        switch (rotaryFunction) {
            case VOL -> keyUp();
            case SEEK -> keyRight();
            default -> keyUp();
        }

        return this;
    }

    @Override
    public Scene rotaryCCV() {
        switch (rotaryFunction) {
            case VOL -> keyDown();
            case SEEK -> keyLeft();
            default -> keyDown();
        }

        return this;
    }
}
