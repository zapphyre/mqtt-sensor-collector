package life.domacitempeh.mqttsensorcollector.ui;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import life.domacitempeh.mqttsensorcollector.log.LogPresenter;
import life.domacitempeh.mqttsensorcollector.log.impl.LoggingRegistryImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@UIScope
@PageTitle("Photo Frame")
@Route(value = "frame")
@SpringComponent
@RequiredArgsConstructor
public class PhotoMain extends VerticalLayout implements LogPresenter {

    private final LoggingRegistryImpl loggingRegistryImpl;
//    private final MqttClient mqttClient;

    public void setImg(byte[] data) {
        StreamResource streamResource = new StreamResource("vaadin-logo.png", () -> new ByteArrayInputStream(data));
        Image image = new Image(streamResource, "data");
        image.setWidth("100%");
        removeAll();
        add(image);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        loggingRegistryImpl.registerLogger(this);

        Button button = new Button("Shoot!");
        button.addClickListener(this::onComponentEvent);
        add(button);
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        loggingRegistryImpl.removeLogger(this);
    }

    @Override
    public void write(byte[] log) {
        getUI().flatMap(ui -> Optional.of(ui).filter(Component::isAttached)).ifPresent(u -> u.access((Command) () -> setImg(log)));
    }

    @SneakyThrows
    private void onComponentEvent(ClickEvent<Button> q) {
//        mqttClient.publish("cam", new MqttMessage("shoot".getBytes(StandardCharsets.UTF_8)));
    }
}
