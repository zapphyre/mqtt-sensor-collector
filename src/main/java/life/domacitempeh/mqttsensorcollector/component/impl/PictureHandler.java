package life.domacitempeh.mqttsensorcollector.component.impl;

import life.domacitempeh.mqttsensorcollector.component.ESPMessageHandler;
import life.domacitempeh.mqttsensorcollector.log.impl.LoggingRegistryImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PictureHandler implements ESPMessageHandler<byte[]> {

    private final LoggingRegistryImpl logger;

    private static final Logger log = LoggerFactory.getLogger(PictureHandler.class);
    final String PREAMBLE = "data:image/jpeg;base64,";
    Function<String, String> without = s -> s.substring(PREAMBLE.length());


    @Override
    public byte[] getMsgConverter(String payload) {
        return without
                .andThen(PictureHandler::safeDecode)
                .apply(payload);
    }

    static byte[] safeDecode(String payload) {
        try {
            return Base64.getDecoder().decode(payload);
        } catch (IllegalArgumentException e) {
            return new byte[]{0};
        }
    }

    @Override
    public void processMessage(byte[] msg) {
        log.info("got message of length {}", msg.length);
        logger.log(msg);
    }

    @Override
    public String getTopic() {
        return "esp32img";
    }
}
