package life.domacitempeh.mqttsensorcollector.component.impl;

import jakarta.transaction.Transactional;
import life.domacitempeh.mqttsensorcollector.component.ESPMessageHandler;
import life.domacitempeh.mqttsensorcollector.entity.Measurement;
import life.domacitempeh.mqttsensorcollector.entity.MeasurementLog;
import life.domacitempeh.mqttsensorcollector.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class SensorUpdateHandler implements ESPMessageHandler<String, MeasurementLog> {

    private final String[] sensors = {"multi", "odor", "voc", "aqi", "tvoc", "eco2"};
    private final MeasurementRepository measurementRepository;

    @Override
    public void processMessage(MeasurementLog msg) {
        MeasurementLog saved = measurementRepository.save(msg);
        System.out.println(saved);
    }

    @Override
    public MeasurementLog getMsgConverter(String payload) {
        int[] idx = {0};

        List<Measurement> meas = Arrays.stream(payload.split(";"))
                .map(Integer::parseInt)
                .map(v -> Measurement.builder()
                        .device(sensors[idx[0]++]).value(v)
                        .build())
                .collect(Collectors.toList());

        return MeasurementLog.builder().measurements(meas).build();
    }

    @Override
    public String getTopic() {
        return "measurement";
    }
}
