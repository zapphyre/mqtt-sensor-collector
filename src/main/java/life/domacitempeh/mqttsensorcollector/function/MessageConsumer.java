package life.domacitempeh.mqttsensorcollector.function;

import org.springframework.messaging.Message;

@FunctionalInterface
public interface MessageConsumer {

    void processMessage(Message<?> message);
}
