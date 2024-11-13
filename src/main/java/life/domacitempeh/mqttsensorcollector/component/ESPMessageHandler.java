package life.domacitempeh.mqttsensorcollector.component;

import org.springframework.messaging.Message;

import java.util.Optional;

public interface ESPMessageHandler<R> extends TopicalMessageHandler {

    @Override
    default void acceptMessage(Message<?> message) {
        Optional.of(message)
                .map(Message::getPayload)
                .map(Object::toString)
                .map(this::getMsgConverter)
                .ifPresent(this::processMessage);
    }

    R getMsgConverter(String payload);

    void processMessage(R msg);
}
