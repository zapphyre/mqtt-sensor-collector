package life.domacitempeh.mqttsensorcollector.component.impl;

import life.domacitempeh.mqttsensorcollector.component.ESPMessageHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InfoHandler implements ESPMessageHandler<String, String> {

    @Override
    public String getMsgConverter(String payload) {
        return payload;
    }

    @Override
    public void processMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public String getTopic() {
        return "info";
    }
}
