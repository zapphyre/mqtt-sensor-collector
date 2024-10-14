package life.domacitempeh.mqttsensorcollector.repository;

import life.domacitempeh.mqttsensorcollector.entity.MeasurementLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<MeasurementLog, Long> {
}
