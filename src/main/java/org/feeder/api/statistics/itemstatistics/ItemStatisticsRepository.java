package org.feeder.api.statistics.itemstatistics;

import java.util.UUID;
import org.feeder.api.statistics.itemstatistics.entity.ItemStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemStatisticsRepository extends JpaRepository<ItemStatistics, UUID> {

}
