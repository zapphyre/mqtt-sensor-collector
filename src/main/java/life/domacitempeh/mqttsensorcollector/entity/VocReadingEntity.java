package life.domacitempeh.mqttsensorcollector.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VocReadingEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Integer value;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
