package life.domacitempeh.mqttsensorcollector.config;

import org.springframework.messaging.Message;

import java.util.function.Consumer;

public interface Handler {

    Consumer<Message<?>> forTopic(String topic);
}
