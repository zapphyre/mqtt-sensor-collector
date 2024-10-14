package life.domacitempeh.mqttsensorcollector.component.impl;

import life.domacitempeh.mqttsensorcollector.component.ESPMessageHandler;
import life.domacitempeh.mqttsensorcollector.model.ERemoteCommand;
import life.domacitempeh.mqttsensorcollector.scene.Scene;
import life.domacitempeh.mqttsensorcollector.scene.impl.BaseScene;
import life.domacitempeh.mqttsensorcollector.xDoToolUtil;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static life.domacitempeh.mqttsensorcollector.xDoToolUtil.*;

@Slf4j
@Component
public class HotkeyHandler implements ESPMessageHandler<String, ERemoteCommand> {

    private final Map<Integer, Dimension> mousePositionMap = new TreeMap<>();
    private final AtomicInteger mousePosition = new AtomicInteger();
    private Scene currentScene;

    public HotkeyHandler() {
        log.info("HotkeyHandler started");
        currentScene = new BaseScene();
    }

    @Override
    public ERemoteCommand getMsgConverter(String payload) {
        try {
            return ERemoteCommand.valueOf(payload);
        } catch (Exception e) {
            log.warn("received unknown command: {}", payload);
            return ERemoteCommand.UNKNOWN;
        }
    }

    @Override
    public void processMessage(ERemoteCommand msg) {
        currentScene = switch (msg) {
            case YELLOW_CLICK -> currentScene.buttonYellow();
            case YELLOW_LONG -> currentScene.buttonYellowLong();
            case ROTARY_LONG -> currentScene.rotaryLongClick();
            case ROTARY_CLICK -> currentScene.rotaryClick();
            case ROTARY_CV -> currentScene.rotaryCv();
            case ROTARY_CCV -> currentScene.rotaryCCV();
            case GREEN_CLICK -> currentScene.buttonGreen();
            case GREEN_LONG -> currentScene.buttonGreenLong();
            default -> currentScene;
        };
    }

    @Override
    public String getTopic() {
        return "youtube";
    }
}
