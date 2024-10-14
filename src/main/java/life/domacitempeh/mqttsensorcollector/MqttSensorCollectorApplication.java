package life.domacitempeh.mqttsensorcollector;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableVaadin
@SpringBootApplication
public class MqttSensorCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqttSensorCollectorApplication.class, args);
    }

}
