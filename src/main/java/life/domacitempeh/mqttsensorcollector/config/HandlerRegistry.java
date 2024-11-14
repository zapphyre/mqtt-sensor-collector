package life.domacitempeh.mqttsensorcollector.config;

import life.domacitempeh.mqttsensorcollector.function.MessageConsumer;

public interface HandlerRegistry {

    MessageConsumer forTopic(String topic);
}
