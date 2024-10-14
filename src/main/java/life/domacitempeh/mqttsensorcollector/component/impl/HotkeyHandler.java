package life.domacitempeh.mqttsensorcollector.component.impl;

import life.domacitempeh.mqttsensorcollector.component.ESPMessageHandler;
import life.domacitempeh.mqttsensorcollector.xDoToolUtil;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static life.domacitempeh.mqttsensorcollector.xDoToolUtil.*;

@Slf4j
@Component
public class HotkeyHandler implements ESPMessageHandler<String, String> {

    private final Map<Integer, Dimension> mousePositionMap = new TreeMap<>();
    private final AtomicInteger mousePosition = new AtomicInteger();
    private boolean switching;
    private boolean fullscreen;
    private boolean newtab;
    private boolean fullscreenRotation;

    public HotkeyHandler() {
        log.info("HotkeyHandler started");

        mousePositionMap.put(0, new Dimension(562, 853));
        mousePositionMap.put(1, new Dimension(1506, 853));
    }


    @Override
    public String getMsgConverter(String payload) {
        return payload;
    }

    @Override
    public void processMessage(String msg) {
        switch (msg) {
            case "playback":
                playPressed();
                break;
            case "fullscreen":
                toggleFullscreen();
                break;
//            case "location":
//                Optional.of(togglePointerLocation()).ifPresent(xDoToolUtil::setPointerLocation);
//                break;
            case "longRotary":
                tabSwitch();
                break;
            case "clickRotary":
                if (fullscreen) fullscreenRotation = !fullscreenRotation;
                setTab();
                break;
            case "next":
                rotaryCV();
                break;
            case "prev":
                rotaryCCV();
                break;
//            case "setTab":
//                setTab();
//                break;
            case "newTab":
                newTab();
                break;
            case "closeTab":
                closeTab();
                break;
        }
    }

    private void closeTab() {
        pressCtrlW();
    }

    void newTab() {
        pressCtrlT();
        newtab = true;
    }

    void toggleFullscreen() {
        fullscreen = !fullscreen;
        pressF();
    }

    void setTab() {
        if (newtab) {
            click();
            newtab = false;
            return;
        }

        pressSpace();
        tabSwitchOff();
    }

    void rotaryCV() {
        if (switching)
            keyUp();
        else if (fullscreen)
            if (fullscreenRotation)
                keyUp();
            else
                keyRight();
        else if (newtab) {
            Optional.of(togglePointerLocation()).ifPresent(xDoToolUtil::setPointerLocation);
            log.info("mousePosition: {}", mousePosition);
            if (mousePosition.get() == 1) {
                pageDown();
            }

        } else {
        }
    }

    void rotaryCCV() {
        if (switching)
            keyDown();
        else if (fullscreen)
            if (fullscreenRotation)
                keyDown();
            else
                keyLeft();
        else if (newtab) {
            Optional.of(togglePointerLocation()).ifPresent(xDoToolUtil::setPointerLocation);
            if (mousePosition.get() == mousePositionMap.size() - 1) {
                pageUp();
            }
        } else {
        }
    }

    void playPressed() {
        switching = false;
        log.info("tabSwitch {}", switching);
        togglePlayYoutube();
    }

    void tabSwitch() {
        switching = true;
        log.info("tabSwitch {}", switching);
        tabSwitchOn();
    }

    Function<Integer, Integer> round = curr -> curr > mousePositionMap.size() - 1 ? 0 : curr;

    Dimension togglePointerLocation() {
        return round.andThen(q -> {
                    mousePosition.set(q);
                    return mousePosition;
                })
                .andThen(AtomicInteger::getAndIncrement)
                .andThen(mousePositionMap::get)
                .apply(mousePosition.get());
    }

    @Override
    public String getTopic() {
        return "youtube";
    }

    @Value
    @Builder
    @ToString
    public static class Dimension {
        Integer x;
        Integer y;
    }
}
