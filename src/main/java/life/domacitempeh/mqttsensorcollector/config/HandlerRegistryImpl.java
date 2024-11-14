package life.domacitempeh.mqttsensorcollector.config;

import life.domacitempeh.mqttsensorcollector.component.MessageReceiver;
import life.domacitempeh.mqttsensorcollector.component.TopicalMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class HandlerRegistryImpl implements HandlerRegistry {
    private final Map<String, MessageReceiver> handlerMap;

    public HandlerRegistryImpl(List<TopicalMessageHandler> handlers) {
        this.handlerMap = handlers.stream()
                .collect(Collectors.toMap(TopicalMessageHandler::getTopic, Function.identity(), (p, q) -> p));
    }

    @Override
    public Consumer<Message<?>> forTopic(String topic) {
        return handlerMap.get(topic)::acceptMessage;
    }
}
