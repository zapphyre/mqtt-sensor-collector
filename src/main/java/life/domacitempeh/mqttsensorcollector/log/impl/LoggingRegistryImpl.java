package life.domacitempeh.mqttsensorcollector.log.impl;

import life.domacitempeh.mqttsensorcollector.log.LogPresenter;
import life.domacitempeh.mqttsensorcollector.log.LoggingRegistry;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class LoggingRegistryImpl implements LoggingRegistry {
    private final List<LogPresenter> logConsumers = new LinkedList<>();

    public void log(byte[] bytea) {
        logConsumers.forEach(c -> c.write(bytea));
    }

    @Override
    public void registerLogger(LogPresenter logger) {
        logConsumers.add(logger);
    }

    @Override
    public void removeLogger(LogPresenter logger) {
        logConsumers.remove(logger);
    }
}
