package life.domacitempeh.mqttsensorcollector.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeasurementLog implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Measurement> measurements;

    @Builder.Default
    private LocalDateTime tstp = LocalDateTime.now();
}
