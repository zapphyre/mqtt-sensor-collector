package life.domacitempeh.mqttsensorcollector.log.impl;

import life.domacitempeh.mqttsensorcollector.log.LogPresenter;
import life.domacitempeh.mqttsensorcollector.log.MonadLogger;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class LoggingMonad implements MonadLogger {
    private final List<LogPresenter> logConsumers = new LinkedList<>();

    public void log(byte[] string) {
        logConsumers.forEach(c -> c.write(string));
    }

    public void log(byte[] string, Function<byte[], byte[]> mapper) {
        Optional.ofNullable(string)
                .map(mapper)
                .ifPresent(this::log);
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
