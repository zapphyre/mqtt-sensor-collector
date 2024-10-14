package life.domacitempeh.mqttsensorcollector.component;

public interface TopicalMessageHandler extends MessageReceiver {

    String getTopic();
}
