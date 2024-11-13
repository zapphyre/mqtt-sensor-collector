package life.domacitempeh.mqttsensorcollector.log;

public interface LoggingRegistry {

    void registerLogger(LogPresenter logger);

    void removeLogger(LogPresenter logger);
}
