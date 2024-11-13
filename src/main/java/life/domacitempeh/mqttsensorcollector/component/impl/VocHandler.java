package life.domacitempeh.mqttsensorcollector.component.impl;

import life.domacitempeh.mqttsensorcollector.component.ESPMessageHandler;
import life.domacitempeh.mqttsensorcollector.entity.MeasurementLog;
import life.domacitempeh.mqttsensorcollector.entity.VocReadingEntity;
import life.domacitempeh.mqttsensorcollector.repository.VocReadingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VocHandler implements ESPMessageHandler<Integer> {

    private final VocReadingRepository vocReadingRepository;

    @Override
    public Integer getMsgConverter(String payload) {
        return Integer.parseInt(payload);
    }

    @Override
    public void processMessage(Integer msg) {
        VocReadingEntity saved = vocReadingRepository.save(VocReadingEntity.builder()
                .value(msg)
                .build());

        log.info("voc reading saved: {}", saved);
    }

    @Override
    public String getTopic() {
        return "voc";
    }
}
