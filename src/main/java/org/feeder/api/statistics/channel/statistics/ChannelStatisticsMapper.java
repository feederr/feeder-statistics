package org.feeder.api.statistics.channel.statistics;

import org.feeder.api.statistics.channel.statistics.entity.ChannelStatistics;
import org.feeder.api.statistics.channel.statistics.vo.ChannelStatisticsResponseVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ChannelStatisticsMapper {

  private final ModelMapper mapper = new ModelMapper();

  public ChannelStatisticsMapper() {
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  public ChannelStatisticsResponseVO toResponseVO(ChannelStatistics entity) {
    return mapper.map(entity, ChannelStatisticsResponseVO.class);
  }
}
