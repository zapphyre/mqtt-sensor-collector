package life.domacitempeh.mqttsensorcollector.model;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class Dimension {
    Integer x;
    Integer y;
}
