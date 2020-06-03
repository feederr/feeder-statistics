package org.feeder.api.statistics.item.statistics;

import java.util.UUID;
import org.feeder.api.statistics.item.statistics.entity.ItemStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemStatisticsRepository extends JpaRepository<ItemStatistics, UUID> {

}
