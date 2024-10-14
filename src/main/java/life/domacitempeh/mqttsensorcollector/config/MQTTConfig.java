package life.domacitempeh.mqttsensorcollector.config;

import life.domacitempeh.mqttsensorcollector.component.ESPMessageHandler;
import life.domacitempeh.mqttsensorcollector.component.TopicalMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Configuration
public class MQTTConfig {

    public static final String BROKER_URL = "tcp://192.168.0.107:1883";
    public static final String CLIENT_ID = "mqtt-sensor-collector";

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{BROKER_URL});
        factory.setConnectionOptions(options);

        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound(List<TopicalMessageHandler> handlers) {
        String[] topics = handlers.stream()
                .map(TopicalMessageHandler::getTopic)
                .toArray(String[]::new);
        MessageChannel messageChannel = mqttInputChannel();
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(CLIENT_ID,
                mqttClientFactory(), topics);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(messageChannel);

        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler(Handler handler) {
        return message -> {
            Optional.of(message)
                    .filter(m -> Objects.nonNull(m.getHeaders().get(MqttHeaders.RECEIVED_TOPIC)))
                    .map(q -> q.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString())
                    .map(handler::forTopic)
                    .ifPresent(q -> q.accept(message));
        };
    }

    @Bean
    MqttClient mqttClient() throws MqttException {
        MqttClient mqttClient = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());

        mqttClient.connect(new MqttConnectOptions());

        return mqttClient;
    }
}
