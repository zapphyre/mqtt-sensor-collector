package life.domacitempeh.mqttsensorcollector.component.impl;

import life.domacitempeh.mqttsensorcollector.component.ESPMessageHandler;
import life.domacitempeh.mqttsensorcollector.xDoToolUtil;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.KeyEvent;

import static life.domacitempeh.mqttsensorcollector.xDoToolUtil.*;
import static life.domacitempeh.mqttsensorcollector.xDoToolUtil.pageUp;

@Component
public class KeyboardHandler implements ESPMessageHandler<String, String> {

    public KeyboardHandler() throws AWTException {
    }

    @Override
    public String getMsgConverter(String payload) {
        return payload;
    }

    @Override
    public void processMessage(String msg) {
        switch (msg.toLowerCase()) {
            case "down": pageDown();
                break;
            case "up": pageUp();
                break;
        }
    }

    @Override
    public String getTopic() {
        return "keyboard";
    }
}
