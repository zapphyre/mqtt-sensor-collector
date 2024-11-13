package life.domacitempeh.mqttsensorcollector.repository;

import life.domacitempeh.mqttsensorcollector.entity.VocReadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocReadingRepository extends JpaRepository<VocReadingEntity, Long> {
}
