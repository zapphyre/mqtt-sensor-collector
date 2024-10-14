package life.domacitempeh.mqttsensorcollector.component;

import org.springframework.messaging.Message;

public interface MessageReceiver {

     void acceptMessage(Message<?> message);
}
