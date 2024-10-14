package life.domacitempeh.mqttsensorcollector.log;

public interface MonadLogger {

    void registerLogger(LogPresenter logger);

    void removeLogger(LogPresenter logger);
}
