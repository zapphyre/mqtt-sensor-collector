package life.domacitempeh.mqttsensorcollector.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measurement implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private final String device;

    private final int value;
}

