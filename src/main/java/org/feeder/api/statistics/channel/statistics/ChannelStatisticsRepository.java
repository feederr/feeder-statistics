package org.feeder.api.statistics.channel.statistics;

import java.util.UUID;
import org.feeder.api.statistics.channel.statistics.entity.ChannelStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelStatisticsRepository extends JpaRepository<ChannelStatistics, UUID> {

}
